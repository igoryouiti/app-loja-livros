package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.billingAddressDTO.BillingAddressDTO;
import br.com.isato.applojalivros.DTO.customerDTO.CreateCustomerDTO;
import br.com.isato.applojalivros.DTO.telephoneDTO.TelephoneDTO;
import br.com.isato.applojalivros.DTO.tradeCouponDTO.CreateTradeCouponDTO;
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

import java.math.BigDecimal;
import java.time.LocalDate;
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

    public Optional<TradeCoupon> create(@Valid CreateTradeCouponDTO createTradeCouponDTO){

        Optional<Customer> optCustomer = customerRepository.findById(createTradeCouponDTO.getCustomerId());

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);

        if(createTradeCouponDTO.getValue().compareTo(BigDecimal.ZERO) < 0 || createTradeCouponDTO.getValue().compareTo(BigDecimal.ZERO) == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um valor maior que zero!", null);


        TradeCoupon tradeCoupon = new TradeCoupon(createTradeCouponDTO);

        tradeCoupon.setCreatedDate(LocalDate.now());
        tradeCoupon.setActive(true);
        tradeCoupon.setDescription("TROCA" + tradeCoupon.getValue().toString());


        return Optional.of(tradeCouponRepository.save(tradeCoupon));
    }

    public Optional<TradeCoupon> update(@Valid TradeCoupon tradeCoupon){
        return Optional.of(tradeCouponRepository.save(tradeCoupon));
    }

//    public Optional<TradeCoupon> updatePayment(@Valid )

    public Optional<TradeCoupon> changeActiveStatus(Long tradeCouponId){

        Optional<TradeCoupon> optTradeCoupon = findById(tradeCouponId);

        if(optTradeCoupon.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);

        optTradeCoupon.get().setActive(!optTradeCoupon.get().getActive());


        return Optional.of(tradeCouponRepository.save(optTradeCoupon.get()));
    }

    public void deleteById(Long id){
        tradeCouponRepository.deleteById(id);
    }
}
