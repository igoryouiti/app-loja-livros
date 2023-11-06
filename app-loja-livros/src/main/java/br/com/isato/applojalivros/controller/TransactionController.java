package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.DTO.addressDTO.AddressDTO;
import br.com.isato.applojalivros.DTO.bookDTO.BookMinDTO;
import br.com.isato.applojalivros.DTO.transactionDTO.TransactionDTO;
import br.com.isato.applojalivros.model.*;
import br.com.isato.applojalivros.service.TransactionItemService;
import br.com.isato.applojalivros.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionItemService transactionItemService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        return transactionService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.searchAllByCustomer(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> postTransaction(@RequestBody Transaction transaction){
        return transactionService.create(transaction)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<Transaction> updateTransaction (@RequestBody Transaction transaction){
        return transactionService.update(transaction)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable Long id){
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    // transactions-items

    @GetMapping("/transaction-items")
    public ResponseEntity<List<TransactionItem>> getTransactionsItemsAll(){
        return ResponseEntity.ok(transactionItemService.findAll());
    }

    @GetMapping("/transaction-items/{id}")
    public ResponseEntity<TransactionItem> getTransactionItemById(@PathVariable Long id){
        return transactionItemService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/transaction-items")
    public ResponseEntity<TransactionItem> postTransactionItem(@RequestBody TransactionItem transactionItem){
        return transactionItemService.create(transactionItem)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(("/transaction-items"))
    public ResponseEntity<TransactionItem> updateTransactionItem(@RequestBody TransactionItem transactionItem){
        return transactionItemService.update(transactionItem)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/transaction-items/{id}")
    public ResponseEntity deleteTransactionItem(@PathVariable Long id){
        transactionItemService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
