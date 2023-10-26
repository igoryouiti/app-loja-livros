package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.model.Item;
import br.com.isato.applojalivros.repository.BookStockRepository;
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
    private BookStockRepository bookStockRepository;

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

}
