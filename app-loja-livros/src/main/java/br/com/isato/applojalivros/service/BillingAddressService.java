package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.billingAddressDTO.BillingAddressDTO;
import br.com.isato.applojalivros.DTO.billingAddressDTO.BillingMinDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidadorCep;
import br.com.isato.applojalivros.model.BillingAddress;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.projection.BillingAddressProjection;
import br.com.isato.applojalivros.repository.BillingAddressRepository;
import br.com.isato.applojalivros.repository.CustomerRepository;
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
public class BillingAddressService {

    @Autowired
    private BillingAddressRepository billingAddressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private IValidator validator;

    public List<BillingAddress> findAll(){
        return billingAddressRepository.findAll();
    }

    public Optional<BillingMinDTO> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        BillingMinDTO billingMinDTO = new BillingMinDTO(billingAddressRepository.findById(id).get());
        return Optional.of(billingMinDTO);
    }

    public List<BillingAddressDTO> searchAllByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        Optional<Customer> optCustomer = customerRepository.findById(id);

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);

        List<BillingAddressProjection> addresses = billingAddressRepository.searchAllByCustomer(id);
        return addresses.stream().map(BillingAddressDTO::new).toList();
    }

    public Optional<BillingAddress> create(@Valid BillingAddress billingAddress){



        if(billingAddress.getCustomer().getId() == null || billingAddress.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCep();
        if(!validator.validate(billingAddress.getCep()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O CEP deve ser uma String de 8 caracteres numéricos");

        return Optional.of(billingAddressRepository.save(billingAddress));
    }

    public Optional<BillingAddress> update(@Valid BillingAddress billingAddress){

        Optional<BillingAddress> optBillingAddress = billingAddressRepository.findById(billingAddress.getId());

        if(optBillingAddress.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        billingAddress.setCustomer(optBillingAddress.get().getCustomer());

        if(billingAddress.getCustomer().getId() == null || billingAddress.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCep();
        if(!validator.validate(billingAddress.getCep()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O CEP deve ser uma String de 8 caracteres numéricos");

        return Optional.of(billingAddressRepository.save(billingAddress));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<BillingAddress> billingAddress = billingAddressRepository.findById(id);

        if(billingAddress.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        billingAddressRepository.deleteById(id);
    }
}
