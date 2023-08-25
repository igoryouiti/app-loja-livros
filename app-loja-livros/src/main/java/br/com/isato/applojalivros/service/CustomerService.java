package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return customerRepository.findById(id);
    }

    public Optional<Customer> create(Customer customer){
        return Optional.of(customerRepository.save(customer));
    }

    public Optional<Customer> update(Customer customer){
        return Optional.of(customerRepository.save(customer));
    }

}
