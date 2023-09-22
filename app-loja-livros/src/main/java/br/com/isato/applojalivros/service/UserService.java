package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.telephoneDTO.TelephoneDTO;
import br.com.isato.applojalivros.DTO.userDTO.CreateUserDTO;
import br.com.isato.applojalivros.DTO.userDTO.UpdateUserDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidatorEmail;
import br.com.isato.applojalivros.business.validator.ValidatorPassword;
import br.com.isato.applojalivros.business.validator.ValidatorTwoPasswords;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.repository.CustomerRepository;
import br.com.isato.applojalivros.repository.UserRepository;
import jakarta.validation.Valid;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private IValidator validator;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        return userRepository.findById(id);
    }

    public Optional<User> searchByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        Optional<Customer> optCustomer = customerRepository.findById(id);

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);

        User user = new User(userRepository.searchByCustomer(id));
        return Optional.of(user);
    }

    public Optional<User> create(@Valid CreateUserDTO createUserDTO){

        validator = new ValidatorEmail();
        if(!validator.validate(createUserDTO.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Email não válido");

        if(userRepository.findByEmail(createUserDTO.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Email de usuário já existe!", null);


        validator = new ValidatorTwoPasswords();
        if(!validator.validate(createUserDTO))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A senha deve ser igual nos dois campos", null);

        validator = new ValidatorPassword();
        if(!validator.validate(createUserDTO.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A senha deve conter pelo menos 1 caractere minusculo, 1 maiusculo, 1 especial" +
                            "e no minimo de 8 digitos", null);

        createUserDTO.setPassword(cryptographyPassword(createUserDTO.getPassword()));

        User user = new User(createUserDTO);

        user.setActive(true);

        return Optional.of(userRepository.save(user));
    }

    public Optional<User> updatePassword(@Valid UpdateUserDTO updateUserDTO){
        if(updateUserDTO.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<User> optUser = userRepository.findById(updateUserDTO.getId());

        if(optUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Usuário não encontrado", null);

        validator = new ValidatorTwoPasswords();
        if(!validator.validate(updateUserDTO))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A senha deve ser igual nos dois campos", null);

        validator = new ValidatorPassword();
        if(!validator.validate(updateUserDTO.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A senha deve conter pelo menos 1 caractere minusculo, 1 maiusculo, 1 especial" +
                            "e no minimo de 8 digitos", null);

        updateUserDTO.setPassword(cryptographyPassword(updateUserDTO.getPassword()));

        optUser.get().setPassword(updateUserDTO.getPassword());

        return Optional.of(userRepository.save(optUser.get()));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Usuario não encontrado", null);

        userRepository.deleteById(id);
    }

    public Optional<User> inactivateUser(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        user.get().setActive(false);

        return Optional.of(userRepository.save(user.get()));
    }

    public Optional<User> activateUser(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        user.get().setActive(true);

        return Optional.of(userRepository.save(user.get()));
    }

    private String cryptographyPassword (String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private boolean comparePasswords(String loginPassword, String userPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(loginPassword, userPassword);
    }

    private String generateBasicToken(String email, String password) {
        String token = email + ":" + password;
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(tokenBase64);
    }

}
