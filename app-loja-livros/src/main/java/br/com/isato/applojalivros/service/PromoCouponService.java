package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.PromoCoupon;
import br.com.isato.applojalivros.model.TradeCoupon;
import br.com.isato.applojalivros.repository.PromoCouponRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PromoCouponService {
    @Autowired
    private PromoCouponRepository promoCouponRepository;

    public List<PromoCoupon> findAll(){
        return promoCouponRepository.findAll();
    }

    public Optional<PromoCoupon> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return promoCouponRepository.findById(id);
    }

    public Optional<PromoCoupon> create(@Valid PromoCoupon promoCoupon){
        return Optional.of(promoCouponRepository.save(promoCoupon));
    }

    public Optional<PromoCoupon> update(@Valid PromoCoupon promoCoupon){
        return Optional.of(promoCouponRepository.save(promoCoupon));
    }

    public Optional<PromoCoupon> changeActiveStatus(Long promoCouponId){

        Optional<PromoCoupon> optPromoCoupon = findById(promoCouponId);

        if(optPromoCoupon.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id de um cupom de promoção válido (Long id)!", null);

        optPromoCoupon.get().setActive(!optPromoCoupon.get().getActive());


        return Optional.of(promoCouponRepository.save(optPromoCoupon.get()));
    }

    public void deleteById(Long id){
        promoCouponRepository.deleteById(id);
    }
}
