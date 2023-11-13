package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.TradeCouponPayment;
import br.com.isato.applojalivros.repository.TradeCouponPaymentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TradeCouponPaymentService {
    @Autowired
    private TradeCouponPaymentRepository tradeCouponPaymentRepository;

    public List<TradeCouponPayment> findAll(){
        return tradeCouponPaymentRepository.findAll();
    }

    public Optional<TradeCouponPayment> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return tradeCouponPaymentRepository.findById(id);
    }

    public Optional<TradeCouponPayment> create(@Valid TradeCouponPayment tradeCouponPayment){

        if(!tradeCouponPayment.getTradeCoupon().getActive())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O cupom de troca deve estar ativo!", null);

        return Optional.of(tradeCouponPaymentRepository.save(tradeCouponPayment));
    }

    public Optional<TradeCouponPayment> update(@Valid TradeCouponPayment tradeCouponPayment){
        return Optional.of(tradeCouponPaymentRepository.save(tradeCouponPayment));
    }

    public void deleteById(Long id){
        tradeCouponPaymentRepository.deleteById(id);
    }
}
