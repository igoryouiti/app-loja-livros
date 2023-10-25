package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.PaymentMethod;
import br.com.isato.applojalivros.repository.PaymentMethodRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> findAll(){
        return paymentMethodRepository.findAll();
    }

    public Optional<PaymentMethod> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return paymentMethodRepository.findById(id);
    }

    public Optional<PaymentMethod> create(@Valid PaymentMethod paymentMethod){
        return Optional.of(paymentMethodRepository.save(paymentMethod));
    }

    public Optional<PaymentMethod> update(@Valid PaymentMethod paymentMethod){
        return Optional.of(paymentMethodRepository.save(paymentMethod));
    }

    public void deleteById(Long id){
        paymentMethodRepository.deleteById(id);
    }
}
