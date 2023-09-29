package br.com.isato.applojalivros.service;

//import br.com.isato.applojalivros.DTO.categoryDTO.Category;
//import br.com.isato.applojalivros.model.Book;
import br.com.isato.applojalivros.DTO.creditCardDTO.CreditCardDTO;
import br.com.isato.applojalivros.model.Book;
import br.com.isato.applojalivros.model.Category;
import br.com.isato.applojalivros.projection.CategoryProjection;
import br.com.isato.applojalivros.projection.CreditCardProjection;
import br.com.isato.applojalivros.repository.BookRepository;
import br.com.isato.applojalivros.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public List<Category> searchAllByBookId(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        Optional<Book> optBook = bookRepository.findById(id);

        if(optBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);


        List<CategoryProjection> categories = categoryRepository.searchAllByBook(id);
        return categories.stream().map(Category::new).toList();
    }

    public Optional<Category> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return categoryRepository.findById(id);
    }

    public Optional<Category> create(@Valid Category category){

        return Optional.of(categoryRepository.save(category));
    }

    public Optional<Category> update(Category category){

        Optional<Category> optCategory = findById(category.getId());

        if(optCategory.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve conter id de um livro válido");

        return Optional.of(categoryRepository.save(category));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Telefone não encontrado", null);

        categoryRepository.deleteById(id);
    }
}
