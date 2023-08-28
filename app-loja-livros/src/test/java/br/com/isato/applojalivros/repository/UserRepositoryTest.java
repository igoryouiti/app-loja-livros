package br.com.isato.applojalivros.repository;

import br.com.isato.applojalivros.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @BeforeAll
    void start(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("Retorna usuario utilizando o email")
    public void returnOneUserByEmail(){

        User user = new User(0L, "teste@teste.com", "Teste123!",
                true, null);

        repository.save(user);

        Optional<User> result = repository.findByEmail("teste@teste.com");

        assertEquals(true, result.isPresent());

        assertTrue(result.isPresent() && user.getEmail() == "teste@teste.com");

    }

}
