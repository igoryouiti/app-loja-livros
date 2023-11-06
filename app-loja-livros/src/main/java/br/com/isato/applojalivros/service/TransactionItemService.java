package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.TransactionItem;
import br.com.isato.applojalivros.repository.TransactionItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionItemService {

    @Autowired
    private TransactionItemRepository transactionItemRepository;

    public List<TransactionItem> findAll(){
        return transactionItemRepository.findAll();
    }

    public Optional<TransactionItem> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return transactionItemRepository.findById(id);
    }

    public Optional<TransactionItem> create(@Valid TransactionItem transactionItem){
        return Optional.of(transactionItemRepository.save(transactionItem));
    }

    public Optional<TransactionItem> update(@Valid TransactionItem transactionItem){
        return Optional.of(transactionItemRepository.save(transactionItem));
    }

    public void deleteById(Long id){
        transactionItemRepository.deleteById(id);
    }
}
