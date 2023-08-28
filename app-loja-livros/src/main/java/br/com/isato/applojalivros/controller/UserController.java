package br.com.isato.applojalivros.controller;


import br.com.isato.applojalivros.DTO.CreateUserDTO;
import br.com.isato.applojalivros.DTO.UpdateUserDTO;
import br.com.isato.applojalivros.model.Address;
import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.service.AddressService;
import br.com.isato.applojalivros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){

            return userService.findById(id)
                    .map(response -> ResponseEntity.ok(response))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping("/create")
    public ResponseEntity<User> post (@RequestBody CreateUserDTO createUserDTO){

            return userService.create(createUserDTO)
                    .map(response -> ResponseEntity.ok(response))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PutMapping("/password")
    public ResponseEntity<User> updatePassword (@RequestBody UpdateUserDTO updateUserDTO){
            return userService.updatePassword(updateUserDTO)
                    .map(response -> ResponseEntity.ok(response))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            throw e;
        }
    }

    @PutMapping("/active/{id}")
    public ResponseEntity active(@PathVariable Long id) {
            userService.activateUser(id);
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity inactive(@PathVariable Long id) {

            userService.inactivateUser(id);
            return ResponseEntity.noContent().build();
    }
}
