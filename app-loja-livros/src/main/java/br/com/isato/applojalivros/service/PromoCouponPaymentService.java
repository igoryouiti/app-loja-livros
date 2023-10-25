package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.PromoCouponPayment;
import br.com.isato.applojalivros.repository.PromoCouponPaymentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PromoCouponPaymentService {
    @Autowired
    private PromoCouponPaymentRepository promoCouponPaymentRepository;

    public List<PromoCouponPayment> findAll(){
        return promoCouponPaymentRepository.findAll();
    }

    public Optional<PromoCouponPayment> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return promoCouponPaymentRepository.findById(id);
    }

    public Optional<PromoCouponPayment> create(@Valid PromoCouponPayment promoCouponPayment){
        return Optional.of(promoCouponPaymentRepository.save(promoCouponPayment));
    }

    public Optional<PromoCouponPayment> update(@Valid PromoCouponPayment promoCouponPayment){
        return Optional.of(promoCouponPaymentRepository.save(promoCouponPayment));
    }

    public void deleteById(Long id){
        promoCouponPaymentRepository.deleteById(id);
    }
}
