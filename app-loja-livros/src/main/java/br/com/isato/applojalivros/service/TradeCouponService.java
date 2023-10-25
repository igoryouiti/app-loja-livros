package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.TradeCoupon;
import br.com.isato.applojalivros.repository.TradeCouponRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TradeCouponService {
    @Autowired
    private TradeCouponRepository tradeCouponRepository;

    public List<TradeCoupon> findAll(){
        return tradeCouponRepository.findAll();
    }

    public Optional<TradeCoupon> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return tradeCouponRepository.findById(id);
    }

    public Optional<TradeCoupon> create(@Valid TradeCoupon tradeCoupon){
        return Optional.of(tradeCouponRepository.save(tradeCoupon));
    }

    public Optional<TradeCoupon> update(@Valid TradeCoupon tradeCoupon){
        return Optional.of(tradeCouponRepository.save(tradeCoupon));
    }

    public void deleteById(Long id){
        tradeCouponRepository.deleteById(id);
    }
}
