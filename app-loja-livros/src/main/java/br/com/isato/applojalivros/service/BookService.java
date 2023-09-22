package br.com.isato.applojalivros.service;

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

        Optional<Book> optCreatedBook = Optional.of(bookRepository.save(book));

        book.getCategories().forEach(category -> {
            category.setBook(optCreatedBook.get());
        });

        List<Optional<Category>> createdCategories = book.getCategories().stream()
                .map(category -> categoryService.create(category))
                .collect(Collectors.toList());

        for (Optional<Category> optionalCategory : createdCategories) {
            if (optionalCategory.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Erro na criação de categoria");
        }

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

    public Optional<Book> update(Book book){

        Optional<Book> optBook = findById(book.getId());

        if(optBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id de um livro válido");


        return Optional.of(bookRepository.save(book));
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<Book> inactivateBook(ReasonActivationChange reason){

        Optional<ReasonActivationChange> optReason = reasonActivationChangeService.findById(reason.getId());
        if(optReason.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Justificativa não encontrada", null);

        Optional<Book> optBook = bookRepository.findById(reason.getBook().getId());

        if(optBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Livro não encontrado", null);

        optBook.get().setActive(false);

        Optional<Book> optUpdatedBook = update(optBook.get());

        if(optUpdatedBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro no update do livro", null);

        Optional<ReasonActivationChange> optUpdatedReason = reasonActivationChangeService.update(reason);

        if(optUpdatedReason.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro no update da Justificativa", null);

        optBook.get().setReasonActivationChange(optUpdatedReason.get());

        return optBook;
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<Book> activateBook(ReasonActivationChange reason){

        Optional<ReasonActivationChange> optReason = reasonActivationChangeService.findById(reason.getId());
        if(optReason.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Justificativa não encontrada", null);

        Optional<Book> optBook = bookRepository.findById(reason.getBook().getId());

        if(optBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Livro não encontrado", null);

        optBook.get().setActive(true);

        Optional<Book> optUpdatedBook = update(optBook.get());

        if(optUpdatedBook.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro no update do livro", null);

        Optional<ReasonActivationChange> optUpdatedReason = reasonActivationChangeService.update(reason);

        if(optUpdatedReason.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro no update da Justificativa", null);

        optBook.get().setReasonActivationChange(optUpdatedReason.get());

        return optBook;
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
