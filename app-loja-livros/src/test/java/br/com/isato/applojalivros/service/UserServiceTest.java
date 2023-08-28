package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.CreateUserDTO;
import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeAll
    void start(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Retorna um usuario criado")
    public void createUser(){

        CreateUserDTO userDTO = new CreateUserDTO("teste-diferente@teste.com", "Teste123!",
                "Teste123!");

        Optional<User> result = userService.create(userDTO);

        assertEquals(true, result.isPresent());

        assertTrue(result.isPresent() && userDTO.getEmail() == "teste-diferente@teste.com");
    }

    @Test
    @DisplayName("Retorna um Erro do Password invalido")
    public void createUserWithInvalidPassword(){

        CreateUserDTO userDTO = new CreateUserDTO("teste@.com", "!Teste123",
                "!Teste123");

        try {
            Optional<User> response = userService.create(userDTO);
            fail("An exception was expected");
        }catch (ResponseStatusException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }

    }

    @Test
    @DisplayName("Retorna um Erro do Password diferente")
    public void createUserWithDifferentPassword(){

        CreateUserDTO userDTO = new CreateUserDTO("teste@teste.com", "!Teste123",
                "!Teste1244");

        try {
            Optional<User> response = userService.create(userDTO);
            fail("An exception was expected");
        }catch (ResponseStatusException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }

    }

    @Test
    @DisplayName("Retorna um Erro de email invalido")
    public void createUserWithInvalidEmail(){

        CreateUserDTO userDTO = new CreateUserDTO("teste@.com", "!Teste123",
                "!Teste123");

        try {
            Optional<User> response = userService.create(userDTO);
            fail("An exception was expected");
        }catch (ResponseStatusException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }

    }

    @Test
    @DisplayName("Retorna um Erro password invalido tamanho menor que 8")
    public void createUserWithInvalidPasswordMinReq(){

        CreateUserDTO userDTO = new CreateUserDTO("teste@teste3.com", "!Te123",
                "!Te123");

        try {
            Optional<User> response = userService.create(userDTO);
            fail("An exception was expected");
        }catch (ResponseStatusException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }

    }

    @Test
    @DisplayName("Criar um user com email ja existente")
    public void createUserWithSameEmail(){

        CreateUserDTO userDTO = new CreateUserDTO("teste@teste.com", "!Teste123",
                "!Teste123");

        userService.create(userDTO);

        CreateUserDTO userToFailDTO = new CreateUserDTO("teste@teste.com", "!Teste123",
                "!Teste123");

        try {
            Optional<User> response = userService.create(userToFailDTO);
            fail("An exception was expected");
        }catch (ResponseStatusException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }

    @Test
    @DisplayName("Espera que user se torne inativo")
    public void inactivateUser(){

        CreateUserDTO userDTO = new CreateUserDTO("teste-inactive@teste.com", "!Teste123",
                "!Teste123");

        try {
            Optional<User> response = userService.create(userDTO);
        }catch (ResponseStatusException e){
            fail("Exception not expected");
        }

        User user = userRepository.findByEmail("teste-inactive@teste.com").get();

        userService.inactivateUser(user.getId());

        user = userRepository.findByEmail("teste-inactive@teste.com").get();

        assertEquals(false, user.getActive());

    }

    @Test
    @DisplayName("Espera que user inativo se torne  ativo")
    public void activateUser(){

        CreateUserDTO userDTO = new CreateUserDTO("teste-active@teste.com", "!Teste123",
                "!Teste123");

        try {
            Optional<User> response = userService.create(userDTO);
        }catch (ResponseStatusException e){
            fail("Exception not expected");
        }

        User user = userRepository.findByEmail("teste-active@teste.com").get();

        userService.inactivateUser(user.getId());

        User inactivatedUser = userRepository.findByEmail("teste-active@teste.com").get();

        userService.activateUser(inactivatedUser.getId());

        user = userRepository.findByEmail("teste-active@teste.com").get();

        assertEquals(true, user.getActive());
    }


}
