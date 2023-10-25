package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.BookStockTemp;
import br.com.isato.applojalivros.repository.BookStockTempRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookStockTempService {
    @Autowired
    private BookStockTempRepository bookStockTempRepository;

    public List<BookStockTemp> findAll(){
        return bookStockTempRepository.findAll();
    }

    public Optional<BookStockTemp> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return bookStockTempRepository.findById(id);
    }

    public Optional<BookStockTemp> create(@Valid BookStockTemp bookStockTemp){
        return Optional.of(bookStockTempRepository.save(bookStockTemp));
    }

    public Optional<BookStockTemp> update(@Valid BookStockTemp bookStockTemp){
        return Optional.of(bookStockTempRepository.save(bookStockTemp));
    }

    public void deleteById(Long id){
        bookStockTempRepository.deleteById(id);
    }
}
