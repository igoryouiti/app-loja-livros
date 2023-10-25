package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.Transaction;
import br.com.isato.applojalivros.repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

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

    public Optional<Transaction> create(@Valid Transaction transaction){
        return Optional.of(transactionRepository.save(transaction));
    }

    public Optional<Transaction> update(@Valid Transaction transaction){
        return Optional.of(transactionRepository.save(transaction));
    }

    public void deleteById(Long id){
        transactionRepository.deleteById(id);
    }
}
