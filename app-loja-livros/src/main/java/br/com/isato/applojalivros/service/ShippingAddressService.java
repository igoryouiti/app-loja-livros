package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.ShippingAddress;
import br.com.isato.applojalivros.repository.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    public List<ShippingAddress> findAll(){
        return shippingAddressRepository.findAll();
    }

    public Optional<ShippingAddress> findById(Long id){
        return shippingAddressRepository.findById(id);
    }

    public Optional<ShippingAddress> create(ShippingAddress address){
        return Optional.of(shippingAddressRepository.save(address));
    }

    public Optional<ShippingAddress> update(ShippingAddress address){
        return Optional.of(shippingAddressRepository.save(address));
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
