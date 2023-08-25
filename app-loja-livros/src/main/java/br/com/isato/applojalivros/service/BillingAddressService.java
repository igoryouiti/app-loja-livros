package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.Address;
import br.com.isato.applojalivros.model.BillingAddress;
import br.com.isato.applojalivros.repository.AddressRepository;
import br.com.isato.applojalivros.repository.BillingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BillingAddressService {

    @Autowired
    private BillingAddressRepository billingAddressRepository;

    public List<BillingAddress> findAll(){
        return billingAddressRepository.findAll();
    }

    public Optional<BillingAddress> findById(Long id){
        return billingAddressRepository.findById(id);
    }

    public Optional<BillingAddress> create(BillingAddress address){
        return Optional.of(billingAddressRepository.save(address));
    }

    public Optional<BillingAddress> update(BillingAddress address){
        return Optional.of(billingAddressRepository.save(address));
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
