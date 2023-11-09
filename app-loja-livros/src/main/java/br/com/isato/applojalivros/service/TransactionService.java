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

        List<TransactionItem> transactionItems = transaction.getTransactionItems();

        List<Optional<TransactionItem>> items = new ArrayList<>();
        transactionItems.forEach(item -> items.add(transactionItemService.create(item)));

        items.forEach(item -> {
            if(item.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Erro ao criar um transactionItem");

            item.get().setItem(itemService.findById(item.get().getItem().getId()).get());

            if(Optional.of(item.get().getItem()).isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Deve-se passar um id de item válido");
        });



        BigDecimal totalPrice = BigDecimal.valueOf(0);

        transactionItems.forEach(item -> {
            totalPrice.add(item.getItem().getSellPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        });

//        transaction.getPaymentMethod().
//
//        PaymentMethod paymentMethod = paymentMethodService.create(transaction.getPaymentMethod()).get();

        transaction.setTotalPrice(totalPrice);
        transaction.setTransactionStatus(TransactionStatus.EM_PROCESSAMENTO);
        transaction.setDateTime(LocalDateTime.now());


        return Optional.of(transactionRepository.save(transaction));
    }

    public Optional<Transaction> update(@Valid Transaction transaction){
        return Optional.of(transactionRepository.save(transaction));
    }

    public void deleteById(Long id){
        transactionRepository.deleteById(id);
    }
}
