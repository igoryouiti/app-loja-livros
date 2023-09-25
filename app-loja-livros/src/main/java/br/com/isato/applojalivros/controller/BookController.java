package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.DTO.bookDTO.BookMinDTO;
import br.com.isato.applojalivros.model.*;
import br.com.isato.applojalivros.service.BookService;
import br.com.isato.applojalivros.service.CategoryService;
import br.com.isato.applojalivros.service.DimensionService;
import br.com.isato.applojalivros.service.ReasonActivationChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DimensionService dimensionService;
    @Autowired
    private ReasonActivationChangeService reasonActivationChangeService;


    @GetMapping
    public ResponseEntity<List<Book>> getAll(){
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        return bookService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Book> post (@RequestBody Book book){
        return bookService.create(book)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<Book> updateBook (@RequestBody BookMinDTO bookMinDTO){
        return bookService.update(bookMinDTO)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/inactivate")
    public ResponseEntity<Book> inactivateBook (@RequestBody ReasonActivationChange reasonActivationChange){
        return bookService.inactivateBook(reasonActivationChange)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/activate")
    public ResponseEntity<Book> activateBook (@RequestBody ReasonActivationChange reasonActivationChange){
        return bookService.activateBook(reasonActivationChange)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook (@PathVariable Long id){
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return categoryService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/categories")
    public ResponseEntity<List<Category>> getAllCategoryByBookId(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.searchAllByBookId(id));
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> postCategory(@RequestBody Category category){
        return categoryService.create(category)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/categories")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        return categoryService.update(category)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteCategory (@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dimension/{id}")
    public ResponseEntity<Dimension> getDimensionById(@PathVariable Long id){
        return dimensionService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/dimension")
    public ResponseEntity<Dimension> getDimensionByCustomerId(@PathVariable Long id){
        return dimensionService.searchByBook(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/dimension")
    public ResponseEntity<Dimension> postDimension(@RequestBody Dimension dimension){
        return dimensionService.create(dimension)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping("/dimension")
    public ResponseEntity<Dimension> updateDimension(@RequestBody Dimension dimension){
        return dimensionService.update(dimension)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/dimension/{id}")
    public ResponseEntity deleteDimension(@PathVariable Long id) {
        dimensionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
