package br.com.isato.applojalivros.controller;


import br.com.isato.applojalivros.DTO.userDTO.CreateUserDTO;
import br.com.isato.applojalivros.DTO.userDTO.UpdateUserDTO;
import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/customer/{id}")
    public ResponseEntity<User> getByCustomerId(@PathVariable Long id){
        return userService.searchByCustomer(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping
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
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/active/{id}")
    public ResponseEntity active(@PathVariable Long id) {
        return userService.activateUser(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity inactive(@PathVariable Long id) {
        return userService.inactivateUser(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
