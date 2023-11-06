package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.billingAddressDTO.BillingAddressDTO;
import br.com.isato.applojalivros.DTO.telephoneDTO.TelephoneDTO;
import br.com.isato.applojalivros.DTO.tradeCouponDTO.TradeCouponDTO;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.model.TradeCoupon;
import br.com.isato.applojalivros.projection.BillingAddressProjection;
import br.com.isato.applojalivros.projection.TradeCouponProjection;
import br.com.isato.applojalivros.repository.CustomerRepository;
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
    @Autowired
    private CustomerRepository customerRepository;

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

    public List<TradeCouponDTO> searchAllByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        Optional<Customer> optCustomer = customerRepository.findById(id);

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);

        List<TradeCouponProjection> tradeCouponsProjection = tradeCouponRepository.searchAllByCustomer(id);
        return tradeCouponsProjection.stream().map(TradeCouponDTO::new).toList();
    }

    public Optional<TradeCoupon> create(@Valid TradeCoupon tradeCoupon){

        Optional<Customer> optCustomer = customerRepository.findById(tradeCoupon.getCustomer().getId());

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);

        return Optional.of(tradeCouponRepository.save(tradeCoupon));
    }

    public Optional<TradeCoupon> update(@Valid TradeCoupon tradeCoupon){
        return Optional.of(tradeCouponRepository.save(tradeCoupon));
    }

    public void deleteById(Long id){
        tradeCouponRepository.deleteById(id);
    }
}
