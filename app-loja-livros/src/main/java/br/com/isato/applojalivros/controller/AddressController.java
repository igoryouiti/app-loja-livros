package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.model.Address;
import br.com.isato.applojalivros.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id){
        return addressService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping
    public ResponseEntity<Address> post (@RequestBody Address data){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(data).get());
    }

    @PutMapping
    public ResponseEntity<Address> put (@RequestBody Address data){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.update(data).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Address> address = addressService.findById(id);
        if(address.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
