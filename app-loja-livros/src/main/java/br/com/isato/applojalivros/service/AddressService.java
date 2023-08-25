package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.Address;
import br.com.isato.applojalivros.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id){
        return addressRepository.findById(id);
    }

    public Optional<Address> create(Address address){
        return Optional.of(addressRepository.save(address));
    }

    public Optional<Address> update(Address address){
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
