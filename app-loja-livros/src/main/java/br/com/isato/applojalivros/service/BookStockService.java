package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.BookStock;
import br.com.isato.applojalivros.repository.BookStockRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookStockService {

    @Autowired
    private BookStockRepository bookStockRepository;

    public List<BookStock> findAll(){
        return bookStockRepository.findAll();
    }

    public Optional<BookStock> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return bookStockRepository.findById(id);
    }

    public Optional<BookStock> create(@Valid BookStock bookStock){
        return Optional.of(bookStockRepository.save(bookStock));
    }

    public Optional<BookStock> update(@Valid BookStock bookStock){
        return Optional.of(bookStockRepository.save(bookStock));
    }

    public void deleteById(Long id){
        bookStockRepository.deleteById(id);
    }


}
