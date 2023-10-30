package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.bookStockDTO.BookStockQuantityDTO;
import br.com.isato.applojalivros.model.BookStock;
import br.com.isato.applojalivros.model.Item;
import br.com.isato.applojalivros.repository.BookStockRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    private ItemService itemService;

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

        Optional<Item> optItem = itemService.findById(bookStock.getItem().getId());

        if(optItem.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Deve ser passado um id válido para item, que não esteja em outro estoque(Long id)!", null);

        bookStock.setItem(optItem.get());

        Optional<BookStock> optBookStock = Optional.of(bookStockRepository.save(bookStock));

        if(optBookStock.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação do stock");


        return optBookStock;
    }

//    public Optional<BookStock> update(@Valid BookStock bookStock){
//
//        Optional<BookStock> optBookStock = bookStockRepository.findById(bookStock.getId());
//
//        if(optBookStock.isEmpty())
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "O stock deve possuir um id válido");
//
////        Optional<Item> optItem = itemService.findById(bookStock.getItem().getId());
////
////        if(optItem.isEmpty())
////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
////                    "O item deve possuir um id válido, que não esteja vinculado a outro stock");
//
//        optBookStock.get().setQuantity(bookStock.getQuantity());
////        optBookStock.get().setItem(optItem.get());
//        optBookStock.get().setInsertDate(bookStock.getInsertDate());
//
//        return Optional.of(bookStockRepository.save(optBookStock.get()));
//    }

    public Optional<BookStock> updateQuantity(@Valid BookStockQuantityDTO bookStockQuantityDTO){

        Optional<BookStock> optBookStock = bookStockRepository.findById(bookStockQuantityDTO.getId());

        if(optBookStock.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O stock deve possuir um id válido");

        optBookStock.get().setQuantity(optBookStock.get().getQuantity() + bookStockQuantityDTO.getQuantity());

        return Optional.of(bookStockRepository.save(optBookStock.get()));
    }

    public void deleteById(Long id){
        bookStockRepository.deleteById(id);
    }


}
