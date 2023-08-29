package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.customerDTO.CreateCustomerDTO;
import br.com.isato.applojalivros.DTO.customerDTO.UpdateCustomerDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidadorCpf;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
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
    IValidator validator;

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
    @Transactional(rollbackOn = Exception.class)
    public Optional<Customer> create(CreateCustomerDTO createCustomerDTO){

        if(createCustomerDTO.getUser().getId() == null || createCustomerDTO.getUser().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O Objeto User deve ter um id");

        if(userService.findById(createCustomerDTO.getUser().getId()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O Objeto User deve ter um id válido");

        validator = new ValidadorCpf();
        if(validator.validate(createCustomerDTO.getCpf()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter um CPF válido");

        Customer customer = new Customer(createCustomerDTO);

        Optional<Customer> optCreatedCustomer = Optional.of(customerRepository.save(customer));

        createCustomerDTO.getAddress().getCustomer().setId(optCreatedCustomer.get().getId());
        if(addressService.create(createCustomerDTO.getAddress()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de endereço residencial");


        createCustomerDTO.getBillingAddress().getCustomer().setId(optCreatedCustomer.get().getId());
        if(billingAddressService.create(createCustomerDTO.getBillingAddress()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de endereço de cobrança");

        createCustomerDTO.getShippingAddress().getCustomer().setId(optCreatedCustomer.get().getId());
        if(shippingAddressService.create(createCustomerDTO.getShippingAddress()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de endereço de entrega");

        createCustomerDTO.getTelephone().getCustomer().setId(optCreatedCustomer.get().getId());
        if(telephoneService.create(createCustomerDTO.getTelephone()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de endereço de cobrança");


        createCustomerDTO.getCreditCard().getCustomer().setId(optCreatedCustomer.get().getId());
        if(creditCardService.create(createCustomerDTO.getCreditCard()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de cartao de credito");

        return optCreatedCustomer;
    }

    public Optional<Customer> update(UpdateCustomerDTO updateCustomerDTO){

        if(customerRepository.findById(updateCustomerDTO.getId()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O cliente deve ter um id válido");

        validator = new ValidadorCpf();
        if(validator.validate(updateCustomerDTO.getCpf()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter um CPF válido");

        Customer customer = new Customer(updateCustomerDTO);

        return Optional.of(customerRepository.save(customer));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        customerRepository.deleteById(id);
    }

}
