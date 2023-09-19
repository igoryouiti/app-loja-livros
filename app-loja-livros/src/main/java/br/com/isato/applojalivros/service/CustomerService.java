package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.customerDTO.CreateCustomerDTO;
import br.com.isato.applojalivros.DTO.customerDTO.UpdateCustomerDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidadorCpf;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public Optional<Customer> create(@Valid CreateCustomerDTO createCustomerDTO){

        if(createCustomerDTO.getUser().getId() == null || createCustomerDTO.getUser().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O Objeto User deve ter um id");

        Optional<User> user = userService.findById(createCustomerDTO.getUser().getId());

        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O Objeto User deve existir");

        createCustomerDTO.setUser(userService.findById(createCustomerDTO.getUser().getId()).get());

        validator = new ValidadorCpf();
        System.out.println(createCustomerDTO.getCpf());
        if(!validator.validate(createCustomerDTO.getCpf()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter um CPF válido");

        Customer customer = new Customer(createCustomerDTO);

        Optional<Customer> optCreatedCustomer = Optional.of(customerRepository.save(customer));

        createCustomerDTO.getAddress().setCustomer(optCreatedCustomer.get());
        if(addressService.create(createCustomerDTO.getAddress()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de endereço residencial");


        createCustomerDTO.getBillingAddresses().setCustomer(optCreatedCustomer.get());
        if(billingAddressService.create(createCustomerDTO.getBillingAddresses()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de endereço de cobrança");

        createCustomerDTO.getShippingAddresses().setCustomer(optCreatedCustomer.get());
        if(shippingAddressService.create(createCustomerDTO.getShippingAddresses()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de endereço de entrega");

        createCustomerDTO.getTelephone().setCustomer(optCreatedCustomer.get());
        if(telephoneService.create(createCustomerDTO.getTelephone()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de telefone");


        createCustomerDTO.getCreditCards().setCustomer(optCreatedCustomer.get());
        if(creditCardService.create(createCustomerDTO.getCreditCards()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro na criação de cartao de credito");

        return optCreatedCustomer;
    }

    public Optional<Customer> update(@Valid UpdateCustomerDTO updateCustomerDTO){

        Optional<Customer> optCustomer = customerRepository.findById(updateCustomerDTO.getId());

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O cliente deve ter um id válido");

        if(optCustomer.get().getUser().getId() != updateCustomerDTO.getUser().getId())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O usuário deve conter um id e ser o mesmo da criação cliente");

        validator = new ValidadorCpf();
        if(!validator.validate(updateCustomerDTO.getCpf()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter um CPF válido");

        Customer customer = new Customer(updateCustomerDTO);

        customer.setUser(optCustomer.get().getUser());

        return Optional.of(customerRepository.save(customer));
    }

//    public void deleteById(Long id){
//        if(id == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Deve ser passado um id válido (Long id)!", null);
//        }
//        Optional<Customer> customer = customerRepository.findById(id);
//
//        if(customer.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "Cliente não encontrado", null);
//
//        customerRepository.deleteById(id);
//    }

}
