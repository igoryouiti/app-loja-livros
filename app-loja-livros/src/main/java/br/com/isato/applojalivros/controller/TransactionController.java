package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.DTO.addressDTO.AddressDTO;
import br.com.isato.applojalivros.DTO.bookDTO.BookMinDTO;
import br.com.isato.applojalivros.DTO.transactionDTO.TransactionDTO;
import br.com.isato.applojalivros.model.*;
import br.com.isato.applojalivros.service.*;
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

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PromoCouponPaymentService promoCouponPaymentService;

    @Autowired
    private TradeCouponPaymentService tradeCouponPaymentService;

    @Autowired
    private CreditCardPaymentService creditCardPaymentService;

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

    // Payment Methods

    @GetMapping("/payment-method")
    public ResponseEntity<List<PaymentMethod>> getPaymentMethodAll(){
        return ResponseEntity.ok(paymentMethodService.findAll());
    }

    @GetMapping("/payment-method/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable Long id){
        return paymentMethodService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/payment-method")
    public ResponseEntity<PaymentMethod> postPaymentMethod(@RequestBody PaymentMethod paymentMethod){
        return paymentMethodService.create(paymentMethod)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(("/payment-method"))
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@RequestBody PaymentMethod paymentMethod){
        return paymentMethodService.update(paymentMethod)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/payment-method/{id}")
    public ResponseEntity deletePaymentMethod(@PathVariable Long id){
        paymentMethodService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    // Promo Coupom Payment

    @GetMapping("/promo-coupon-payment")
    public ResponseEntity<List<PromoCouponPayment>> getPromoCouponPaymentAll(){
        return ResponseEntity.ok(promoCouponPaymentService.findAll());
    }

    @GetMapping("/promo-coupon-payment/{id}")
    public ResponseEntity<PromoCouponPayment> getPromoCouponPaymentById(@PathVariable Long id){
        return promoCouponPaymentService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/promo-coupon-payment")
    public ResponseEntity<PromoCouponPayment> postPromoCouponPayment(@RequestBody PromoCouponPayment promoCouponPayment){
        return promoCouponPaymentService.create(promoCouponPayment)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(("/promo-coupon-payment"))
    public ResponseEntity<PromoCouponPayment> updatePromoCouponPayment(@RequestBody PromoCouponPayment promoCouponPayment){
        return promoCouponPaymentService.update(promoCouponPayment)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/promo-coupon-payment/{id}")
    public ResponseEntity deletePromoCouponPayment(@PathVariable Long id){
        promoCouponPaymentService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    // Trade Coupon Payment

    @GetMapping("/trade-coupon-payment")
    public ResponseEntity<List<TradeCouponPayment>> getTradeCouponPaymentAll(){
        return ResponseEntity.ok(tradeCouponPaymentService.findAll());
    }

    @GetMapping("/trade-coupon-payment/{id}")
    public ResponseEntity<TradeCouponPayment> getTradeCouponPaymentById(@PathVariable Long id){
        return tradeCouponPaymentService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/trade-coupon-payment")
    public ResponseEntity<TradeCouponPayment> postTradeCouponPayment(@RequestBody TradeCouponPayment tradeCouponPayment){
        return tradeCouponPaymentService.create(tradeCouponPayment)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(("/trade-coupon-payment"))
    public ResponseEntity<TradeCouponPayment> updateTradeCouponPayment(@RequestBody TradeCouponPayment tradeCouponPayment){
        return tradeCouponPaymentService.update(tradeCouponPayment)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/trade-coupon-payment/{id}")
    public ResponseEntity deleteTradeCouponPayment(@PathVariable Long id){
        tradeCouponPaymentService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    // Credit Card Payment

    @GetMapping("/credit-card-payment")
    public ResponseEntity<List<CreditCardPayment>> getCreditCardPaymentAll(){
        return ResponseEntity.ok(creditCardPaymentService.findAll());
    }

    @GetMapping("/credit-card-payment/{id}")
    public ResponseEntity<CreditCardPayment> getCreditCardPaymentById(@PathVariable Long id){
        return creditCardPaymentService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/credit-card-payment")
    public ResponseEntity<CreditCardPayment> postCreditCardPayment(@RequestBody CreditCardPayment creditCardPayment){
        return creditCardPaymentService.create(creditCardPayment)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(("/credit-card-payment"))
    public ResponseEntity<CreditCardPayment> updateCreditCardPayment(@RequestBody CreditCardPayment creditCardPayment){
        return creditCardPaymentService.update(creditCardPayment)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/credit-card-payment/{id}")
    public ResponseEntity deleteCreditCardPayment(@PathVariable Long id){
        creditCardPaymentService.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
