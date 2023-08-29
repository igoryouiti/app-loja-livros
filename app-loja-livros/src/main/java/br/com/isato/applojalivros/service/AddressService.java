package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.addressDTO.AddressDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidadorCep;
import br.com.isato.applojalivros.business.validator.ValidatorEmail;
import br.com.isato.applojalivros.model.Address;
import br.com.isato.applojalivros.model.User;
import br.com.isato.applojalivros.projection.AddressProjection;
import br.com.isato.applojalivros.repository.AddressRepository;
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
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    private IValidator validator;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Optional<AddressDTO> searchByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        AddressDTO address = new AddressDTO(addressRepository.searchByCustomer(id));
        return Optional.of(address);
    }

    public Optional<Address> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return addressRepository.findById(id);
    }

    public Optional<Address> create(@Valid  Address address){

        if(address.getCustomer().getId() == null || address.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCep();
        if(!validator.validate(address.getCep()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O CEP deve ser uma String de 8 caracteres numéricos");

        return Optional.of(addressRepository.save(address));
    }

    public Optional<Address> update(@Valid Address address){

        Optional<Address> optAddress = addressRepository.findById(address.getId());

        if(optAddress.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        if(address.getCustomer().getId() == null || address.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCep();
        if(!validator.validate(address.getCep()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O CEP deve ser uma String de 8 caracteres numéricos");

        return Optional.of(addressRepository.save(address));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<Address> address = addressRepository.findById(id);

        if(address.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Endereço não encontrado", null);

        addressRepository.deleteById(id);
    }
}
