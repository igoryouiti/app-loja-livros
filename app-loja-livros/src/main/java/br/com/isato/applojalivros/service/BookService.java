package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.bookDTO.BookMinDTO;
import br.com.isato.applojalivros.DTO.reasonActivationChangeDTO.ReasonActivationChangeUpdateDTO;
import br.com.isato.applojalivros.model.Book;
import br.com.isato.applojalivros.model.Category;
import br.com.isato.applojalivros.model.ReasonActivationChange;
import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DimensionService dimensionService;
    @Autowired
    private ReasonActivationChangeService reasonActivationChangeService;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return bookRepository.findById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<Book> create(Book book){

        if(book.getCategories().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Categorias não pode ser vazia ou nula");

        book.getCategories().forEach(category -> {
            if(category.getId() == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "As categorias devem possuir um id válido");
        });

        book.getCategories().forEach(category -> {
            if(categoryService.findById(category.getId()).isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "As categorias devem possuir um id válido");
        });

        Optional<Book> optCreatedBook = Optional.of(bookRepository.save(book));


        book.getDimension().setBook(optCreatedBook.get());
        if(dimensionService.create(book.getDimension()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de dimensões do livro");



        book.getReasonActivationChange().setBook(optCreatedBook.get());
        if(reasonActivationChangeService.create(book.getReasonActivationChange()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de dimensões do livro");


        return optCreatedBook;
    }

    public Optional<Book> update(BookMinDTO bookMinDTO){

        Optional<Book> optBook = findById(bookMinDTO.getId());

        if(optBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id de um livro válido");

        Book book = new Book(bookMinDTO);

        return Optional.of(bookRepository.save(book));
    }


    @Transactional(rollbackOn = Exception.class)
    public Optional<Book> inactivateBook(ReasonActivationChangeUpdateDTO reasonActivationChangeDTO ){

        Optional<Book> optBook = bookRepository.findById(reasonActivationChangeDTO.getBook().getId());

        if(optBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Livro não encontrado", null);

        optBook.get().setActive(false);

        BookMinDTO bookMinDTO = new BookMinDTO(optBook.get());

        Optional<Book> optUpdatedBook = update(bookMinDTO);

        if(optUpdatedBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro no update do livro", null);

        ReasonActivationChange reason = new ReasonActivationChange(reasonActivationChangeDTO);

        Optional<ReasonActivationChange> optUpdatedReason = reasonActivationChangeService.update(reason);

        if(optUpdatedReason.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro no update da Justificativa", null);

        optUpdatedBook.get().setReasonActivationChange(optUpdatedReason.get());

        return optUpdatedBook;
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<Book> activateBook(ReasonActivationChangeUpdateDTO reasonActivationChangeDTO ){

        Optional<Book> optBook = bookRepository.findById(reasonActivationChangeDTO.getBook().getId());

        if(optBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Livro não encontrado", null);

        optBook.get().setActive(true);

        BookMinDTO bookMinDTO = new BookMinDTO(optBook.get());

        Optional<Book> optUpdatedBook = update(bookMinDTO);

        if(optUpdatedBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro no update do livro", null);

        ReasonActivationChange reason = new ReasonActivationChange(reasonActivationChangeDTO);

        Optional<ReasonActivationChange> optUpdatedReason = reasonActivationChangeService.update(reason);

        if(optUpdatedReason.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro no update da Justificativa", null);

        optUpdatedBook.get().setReasonActivationChange(optUpdatedReason.get());

        return optUpdatedBook;
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<Book> optResponseBook = bookRepository.findById(id);

        if(optResponseBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Livro não encontrado", null);

        bookRepository.deleteById(id);
    }


}
