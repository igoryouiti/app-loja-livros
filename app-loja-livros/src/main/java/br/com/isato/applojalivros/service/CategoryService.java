package br.com.isato.applojalivros.service;

//import br.com.isato.applojalivros.DTO.categoryDTO.CategoryDTO;
//import br.com.isato.applojalivros.model.Book;
import br.com.isato.applojalivros.model.Category;
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
    private BookRepository customerRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

//    public Optional<CategoryDTO> searchByBook(Long id){
//        if(id == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Deve ser passado um id válido (Long id)!", null);
//        }
//
//        Optional<Book> optBook = customerRepository.findById(id);
//
//        if(optBook.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "Deve ser passado o id cliente válido (Long id)!", null);
//
//        CategoryDTO categoryDTO = new CategoryDTO(categoryRepository.searchByBook(id));
//        return Optional.of(categoryDTO);
//    }

    public Optional<Category> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return categoryRepository.findById(id);
    }

    public Optional<Category> create(@Valid Category category){
        if(category.getBook().getId() == null || category.getBook().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Book");


        return Optional.of(categoryRepository.save(category));
    }

    public Optional<Category> update(Category category){

        Optional<Category> optCategory = findById(category.getId());

        if(optCategory.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve conter id de um livro válido");

        category.setBook(optCategory.get().getBook());

        if(category.getBook().getId() == null || category.getBook().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Book");


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
