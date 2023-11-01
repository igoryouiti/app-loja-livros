package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.DTO.addressDTO.AddressDTO;
import br.com.isato.applojalivros.DTO.billingAddressDTO.BillingAddressDTO;
import br.com.isato.applojalivros.DTO.billingAddressDTO.BillingMinDTO;
import br.com.isato.applojalivros.DTO.creditCardDTO.CreditCardDTO;
import br.com.isato.applojalivros.DTO.creditCardDTO.CreditCardMinDTO;
import br.com.isato.applojalivros.DTO.customerDTO.CreateCustomerDTO;
import br.com.isato.applojalivros.DTO.customerDTO.UpdateCustomerDTO;
import br.com.isato.applojalivros.DTO.shippingAddressDTO.ShippingAddressDTO;
import br.com.isato.applojalivros.DTO.shippingAddressDTO.ShippingAddressMinDTO;
import br.com.isato.applojalivros.DTO.telephoneDTO.TelephoneDTO;
import br.com.isato.applojalivros.model.*;
import br.com.isato.applojalivros.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private BillingAddressService billingAddressService;
    @Autowired
    private ShippingAddressService shippingAddressService;
    @Autowired
    private TelephoneService telephoneService;
    @Autowired
    private CreditCardService creditCardService;


    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id){
        return customerService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Customer> post (@RequestBody CreateCustomerDTO customer){
        return customerService.create(customer)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<Customer> updatePassword (@RequestBody UpdateCustomerDTO updateCustomerDTO){
        return customerService.update(updateCustomerDTO)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        return addressService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/address")
    public ResponseEntity<AddressDTO> getAddressByCustomerId(@PathVariable Long id){
        return addressService.searchByCustomer(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/address")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address){
        return addressService.update(address)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

//    @DeleteMapping("/address/{id}")
//    public ResponseEntity deleteAddress(@PathVariable Long id) {
//        addressService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/billing-addresses/{id}")
    public ResponseEntity<BillingMinDTO> getBillingAddressById(@PathVariable Long id){
        return billingAddressService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/billing-addresses")
    public ResponseEntity<List<BillingAddressDTO>> getAllBillingAddressByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(billingAddressService.searchAllByCustomer(id));
    }

    @PostMapping("/billing-addresses")
    public ResponseEntity<BillingAddress> postBillingAddress(@RequestBody BillingAddress billingAddress){
        return billingAddressService.create(billingAddress)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/billing-addresses")
    public ResponseEntity<BillingAddress> updateBillingAddress(@RequestBody BillingAddress billingAddress){
        return billingAddressService.update(billingAddress)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/billing-addresses/{id}")
    public ResponseEntity deleteBillingAddress(@PathVariable Long id) {
        billingAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shipping-addresses/{id}")
    public ResponseEntity<ShippingAddressMinDTO> getShippingAddressById(@PathVariable Long id){
        return shippingAddressService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/shipping-addresses")
    public ResponseEntity<List<ShippingAddressDTO>> getAllShippingAddressByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(shippingAddressService.searchAllByCustomer(id));
    }

    @PostMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddress> postShippingAddress(@RequestBody ShippingAddress shippingAddress){
        return shippingAddressService.create(shippingAddress)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddress> updateShippingAddress(@RequestBody ShippingAddress shippingAddress){
        return shippingAddressService.update(shippingAddress)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/shipping-addresses/{id}")
    public ResponseEntity deleteShippingAddress(@PathVariable Long id) {
        shippingAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/credit-cards/{id}")
    public ResponseEntity<CreditCardMinDTO> getCreditCardsById(@PathVariable Long id){
        return creditCardService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/credit-cards")
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCardByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(creditCardService.searchAllByCustomer(id));
    }

    @PostMapping("/credit-cards")
    public ResponseEntity<CreditCard> postCreditCard(@RequestBody CreditCard creditCard){
        return creditCardService.create(creditCard)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping("/credit-cards")
    public ResponseEntity<CreditCard> updateCreditCard(@RequestBody CreditCard creditCard){
        return creditCardService.update(creditCard)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/credit-cards/{id}")
    public ResponseEntity deleteCreditCard(@PathVariable Long id) {
        creditCardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/telephone/{id}")
    public ResponseEntity<Telephone> getTelephoneById(@PathVariable Long id){
        return telephoneService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/telephone")
    public ResponseEntity<TelephoneDTO> getTelephoneByCustomerId(@PathVariable Long id){
        return telephoneService.searchByCustomer(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/telephone")
    public ResponseEntity<Telephone> postTelephone(@RequestBody Telephone telephone){
        return telephoneService.create(telephone)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping("/telephone")
    public ResponseEntity<Telephone> updateTelephone(@RequestBody Telephone telephone){
        return telephoneService.update(telephone)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/telephone/{id}")
    public ResponseEntity deleteTelephone(@PathVariable Long id) {
        telephoneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}
