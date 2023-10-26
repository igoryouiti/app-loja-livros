package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.Book;
import br.com.isato.applojalivros.model.Item;
import br.com.isato.applojalivros.repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BookService bookService;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return itemRepository.findById(id);
    }

    public Optional<Item> create(@Valid Item item){

        List<Book> books = item.getBooks().stream().map(Book::new).toList();

        item.setRawPrice(new BigDecimal(0));
        item.setSellPrice(new BigDecimal(0));

        books.forEach(book -> {

            Optional<Book> optBook = bookService.findById(book.getId());
            if (optBook.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Deve ser passado o id de um livro válido (Long id)!", null);
            }

            item.setRawPrice(item.getRawPrice().add(optBook.get().getRawPrice()));
            item.setSellPrice(item.getSellPrice().add(optBook.get().getSellPrice()));

        });

        return Optional.of(itemRepository.save(item));
    }

    public Optional<Item> update(@Valid Item item){

        if(!itemRepository.existsById(item.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O item devepossuir um id válido");

        List<Book> books = item.getBooks().stream().map(Book::new).toList();

        item.setRawPrice(new BigDecimal(0));
        item.setSellPrice(new BigDecimal(0));

        books.forEach(book -> {
            Optional<Book> optBook = bookService.findById(book.getId());
            if (optBook.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Deve ser passado o id de um livro válido (Long id)!", null);
            }

            item.setRawPrice(item.getRawPrice().add(optBook.get().getRawPrice()));
            item.setSellPrice(item.getSellPrice().add(optBook.get().getSellPrice()));

        });

        return Optional.of(itemRepository.save(item));
    }

    public Optional<Item> updateSellPrice(@Valid Item item){

        Optional<Item> optItem = findById(item.getId());

        if(optItem.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O item deve possuir um id válido");

        optItem.get().setSellPrice(item.getSellPrice());

        return Optional.of(itemRepository.save(optItem.get()));
    }

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }
}
