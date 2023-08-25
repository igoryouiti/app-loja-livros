package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public Optional<User> create(User user){
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> update(User user){
        return Optional.of(userRepository.save(user));
    }

}
