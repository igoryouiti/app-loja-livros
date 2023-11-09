package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.shippingAddressDTO.ShippingAddressDTO;
import br.com.isato.applojalivros.DTO.transactionDTO.TransactionDTO;
import br.com.isato.applojalivros.model.*;
import br.com.isato.applojalivros.projection.ShippingAddressProjection;
import br.com.isato.applojalivros.projection.TransactionProjection;
import br.com.isato.applojalivros.repository.CustomerRepository;
import br.com.isato.applojalivros.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionItemService transactionItemService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return transactionRepository.findById(id);
    }

    public List<TransactionDTO> searchAllByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        if(customerService.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido de cliente (Long id)!", null);

        List<TransactionProjection> transactions = transactionRepository.searchAllByCustomer(id);
        return transactions.stream().map(TransactionDTO::new).toList();
    }
    @Transactional(rollbackOn = Exception.class)
    public Optional<Transaction> create(@Valid Transaction transaction){

        Optional<Customer> optCustomer = customerService.findById(transaction.getCustomer().getId());

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O id do usuário deve ser válido");

        Optional<Transaction> optTransaction = Optional.of(transactionRepository.save(transaction));


        if (optTransaction.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Erro ao criar uma transação");


        transaction.getTransactionItems().forEach(item -> {

            item.setTransaction(optTransaction.get());
            Optional<TransactionItem> optTransactionItem = transactionItemService.create(item);

            if(optTransactionItem.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Erro ao criar um transactionItem");

        });

        BigDecimal totalValue;

        totalValue = transaction.getTransactionItems().stream().map(transactionItem -> {
            Optional<Item> optItem = itemService.findById(transactionItem.getItem().getId());
            if(optItem.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Item não achado");

            System.out.println(optItem.get().toString());

            return optItem.get().getSellPrice().multiply(BigDecimal.valueOf(transactionItem.getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        Optional<PaymentMethod> paymentMethod = paymentMethodService.create(transaction.getPaymentMethod());
        if(paymentMethod.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro ao criar payment method");

        transaction.setTotalPrice(totalValue);
        transaction.setTransactionStatus(TransactionStatus.EM_PROCESSAMENTO);
        transaction.setDateTime(LocalDateTime.now());


//        return Optional.of(transactionRepository.save(transaction));
        return findById(optTransaction.get().getId());
    }

    public Optional<Transaction> update(@Valid Transaction transaction){
        return Optional.of(transactionRepository.save(transaction));
    }

    public void deleteById(Long id){
        transactionRepository.deleteById(id);
    }
}
