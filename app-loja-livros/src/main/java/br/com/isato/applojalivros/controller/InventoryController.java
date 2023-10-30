package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.DTO.bookStockDTO.BookStockQuantityDTO;
import br.com.isato.applojalivros.model.Book;
import br.com.isato.applojalivros.model.BookStock;
import br.com.isato.applojalivros.model.Item;
import br.com.isato.applojalivros.service.BookStockService;
import br.com.isato.applojalivros.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InventoryController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private BookStockService bookStockService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(){
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        return itemService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/items")
    public ResponseEntity<Item> postItem(@RequestBody Item item){
        return itemService.create(item)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/items")
    public ResponseEntity<Item> updateItem(@RequestBody Item item){
        return itemService.update(item)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/items/sell-price")
    public ResponseEntity<Item> updateSellPriceItem(@RequestBody Item item){
        return itemService.updateSellPrice(item)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id){
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stock")
    public ResponseEntity<List<BookStock>> getAllBookStock(){
        return ResponseEntity.ok(bookStockService.findAll());
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<BookStock> getStockById(@PathVariable Long id){
        return bookStockService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/stock")
    public ResponseEntity<BookStock> postStock(@RequestBody BookStock bookStock){
        return bookStockService.create(bookStock)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

//    @PutMapping("/stock")
//    public ResponseEntity<BookStock> updateStock(@RequestBody BookStock bookStock){
//        return bookStockService.update(bookStock)
//                .map(response -> ResponseEntity.ok(response))
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

    @PutMapping("/stock/quantity")
    public ResponseEntity<BookStock> updateQuantityStock(@RequestBody BookStockQuantityDTO bookStockQuantityDTO){
        return bookStockService.updateQuantity(bookStockQuantityDTO)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/stock/{id}")
    public ResponseEntity deleteStock(@PathVariable Long id){
        bookStockService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
