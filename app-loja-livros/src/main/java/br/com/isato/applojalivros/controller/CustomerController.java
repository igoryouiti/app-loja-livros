package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.DTO.addressDTO.AddressDTO;
import br.com.isato.applojalivros.DTO.billingAddressDTO.BillingAddressDTO;
import br.com.isato.applojalivros.DTO.creditCardDTO.CreditCardDTO;
import br.com.isato.applojalivros.DTO.customerDTO.CreateCustomerDTO;
import br.com.isato.applojalivros.DTO.customerDTO.UpdateCustomerDTO;
import br.com.isato.applojalivros.DTO.shippingAddressDTO.ShippingAddressDTO;
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
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id){
        return customerService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Customer> post (@RequestBody CreateCustomerDTO createCustomerDTO){
        return customerService.create(createCustomerDTO)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<Customer> updatePassword (@RequestBody UpdateCustomerDTO updateCustomerDTO){
        return customerService.update(updateCustomerDTO)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
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

    @DeleteMapping("/address/{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/billing-addresses/{id}")
    public ResponseEntity<BillingAddress> getBillingAddressById(@PathVariable Long id){
        return billingAddressService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/billing-addresses")
    public ResponseEntity<List<BillingAddressDTO>> getBillingAddressByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(billingAddressService.searchAllByCustomer(id));
    }

    @PostMapping("/billing-addresses")
    public ResponseEntity<BillingAddress> postBillingAdressess(@RequestBody BillingAddress billingAddress){
        return billingAddressService.create(billingAddress)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/billing-addresses")
    public ResponseEntity<BillingAddress> updateBillingAddress (@RequestBody BillingAddress billingAddress){
        return billingAddressService.update(billingAddress)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/billing-addresses/{id}")
    public ResponseEntity deleteBillingAddress(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shipping-addresses/{id}")
    public ResponseEntity<ShippingAddress> getShippingAddressById(@PathVariable Long id){
        return shippingAddressService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/shipping-addresses")
    public ResponseEntity<List<ShippingAddressDTO>> getShippingAddressByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(shippingAddressService.searchAllByCustomer(id));
    }

    @PostMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddress> postShippingAdressess (@RequestBody ShippingAddress shippingAddress){
        return shippingAddressService.create(shippingAddress)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddress> updateBillingAddress (@RequestBody ShippingAddress shippingAddress){
        return shippingAddressService.update(shippingAddress)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/shipping-addresses/{id}")
    public ResponseEntity deleteShippingAddress(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/credit-cards/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable Long id){
        return creditCardService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/credit-cards")
    public ResponseEntity<List<CreditCardDTO>> getCreditCardsByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(creditCardService.searchAllByCustomer(id));
    }

    @PostMapping("/credit-cards")
    public ResponseEntity<CreditCard> postCreditCards (@RequestBody CreditCard creditCard){
        return creditCardService.create(creditCard)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping("/credit-cards")
    public ResponseEntity<CreditCard> updateBillingAddress (@RequestBody CreditCard creditCard){
        return creditCardService.update(creditCard)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/credit-card/{id}")
    public ResponseEntity deleteCreditCard(@PathVariable Long id) {
        userService.deleteById(id);
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
    public ResponseEntity<Telephone> postTelephone (@RequestBody Telephone telephone){
        return telephoneService.create(telephone)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping("/telephone")
    public ResponseEntity<Telephone> updateTelephone (@RequestBody Telephone telephone){
        return telephoneService.update(telephone)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/telephone/{id}")
    public ResponseEntity deleteTelephone(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}
