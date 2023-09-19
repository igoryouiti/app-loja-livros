package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.billingAddressDTO.BillingAddressDTO;
import br.com.isato.applojalivros.DTO.shippingAddressDTO.ShippingAddressDTO;
import br.com.isato.applojalivros.DTO.shippingAddressDTO.ShippingAddressMinDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidadorCep;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.model.ShippingAddress;
import br.com.isato.applojalivros.projection.BillingAddressProjection;
import br.com.isato.applojalivros.projection.ShippingAddressProjection;
import br.com.isato.applojalivros.repository.CustomerRepository;
import br.com.isato.applojalivros.repository.ShippingAddressRepository;
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
public class ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private IValidator validator;

    public List<ShippingAddress> findAll(){
        return shippingAddressRepository.findAll();
    }

    public List<ShippingAddressDTO> searchAllByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        List<ShippingAddressProjection> addresses = shippingAddressRepository.searchAllByCustomer(id);
        return addresses.stream().map(ShippingAddressDTO::new).toList();
    }

    public Optional<ShippingAddressMinDTO> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        ShippingAddressMinDTO shippingAddressMinDTO = new ShippingAddressMinDTO(shippingAddressRepository.findById(id).get());

        return Optional.of(shippingAddressMinDTO);
    }

    public Optional<ShippingAddress> create(@Valid ShippingAddress shippingAddress){

        if(shippingAddress.getCustomer().getId() == null || shippingAddress.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCep();
        if(!validator.validate(shippingAddress.getCep()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O CEP deve ser uma String de 8 caracteres numéricos");

        return Optional.of(shippingAddressRepository.save(shippingAddress));
    }

    public Optional<ShippingAddress> update(@Valid ShippingAddress shippingAddress){

        Optional<ShippingAddress> optShippingAddress = shippingAddressRepository.findById(shippingAddress.getId());

        if(optShippingAddress.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        shippingAddress.setCustomer(optShippingAddress.get().getCustomer());

        if(shippingAddress.getCustomer().getId() == null || shippingAddress.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCep();
        if(!validator.validate(shippingAddress.getCep()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O CEP deve ser uma String de 8 caracteres numéricos");

        return Optional.of(shippingAddressRepository.save(shippingAddress));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<ShippingAddress> shippingAddress = shippingAddressRepository.findById(id);

        if(shippingAddress.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        shippingAddressRepository.deleteById(id);
    }
}
