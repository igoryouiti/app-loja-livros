package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.paymentMethodDTO.PaymentMethodDTO;
import br.com.isato.applojalivros.model.CreditCardPayment;
import br.com.isato.applojalivros.model.PaymentMethod;
import br.com.isato.applojalivros.model.PromoCouponPayment;
import br.com.isato.applojalivros.model.TradeCouponPayment;
import br.com.isato.applojalivros.repository.PaymentMethodRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private PromoCouponPaymentService promoCouponPaymentService;
    @Autowired
    private TradeCouponPaymentService tradeCouponPaymentService;
    @Autowired
    private CreditCardPaymentService creditCardPaymentService;

    public List<PaymentMethod> findAll(){
        return paymentMethodRepository.findAll();
    }

//    public Optional<PaymentMethod> findById(Long id){
//        if(id == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Deve ser passado um id válido (Long id)!", null);
//        }
//        return paymentMethodRepository.findById(id);
//    }

    public Optional<PaymentMethodDTO> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(paymentMethodRepository.findById(id).get());

        return Optional.of(paymentMethodDTO);
    }
    @Transactional(rollbackOn = Exception.class)
    public Optional<PaymentMethod> create(@Valid PaymentMethod paymentMethod){

        if(paymentMethod.getCreditCardPayments() == null &&
                paymentMethod.getPromoCouponPayment() == null &&
                paymentMethod.getCreditCardPayments() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve haver pelo menos um meio de pagamento válido", null);

        Optional<PaymentMethod> optPaymentMethod = Optional.of(paymentMethodRepository.save(paymentMethod));
        if(optPaymentMethod.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Erro ao criar o payment method!", null);

        if(paymentMethod.getCreditCardPayments() != null) {
//            List<CreditCardPayment> creditCardPayments = new ArrayList<CreditCardPayment>();

            paymentMethod.getCreditCardPayments().forEach(creditCardPayment -> {
                creditCardPayment.setPaymentMethod(optPaymentMethod.get());

                Optional<CreditCardPayment> optCreditCardPayment = creditCardPaymentService.create(creditCardPayment);
                if (optCreditCardPayment.isEmpty())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Erro ao criar o credit card payment!", null);

//                creditCardPayments.add(optCreditCardPayment.get());
            });
//            paymentMethod.setCreditCardPayments(creditCardPayments);
        }

        if(paymentMethod.getTradeCouponPayments() != null) {

//            List<TradeCouponPayment> tradeCouponPayments = new ArrayList<TradeCouponPayment>();

            paymentMethod.getTradeCouponPayments().forEach(tradeCouponPayment -> {
                tradeCouponPayment.setPaymentMethod(optPaymentMethod.get());
                Optional<TradeCouponPayment> optTradeCouponPayment = tradeCouponPaymentService.create(tradeCouponPayment);
                if (optTradeCouponPayment.isEmpty())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Erro ao criar o Trade coupon payment!", null);

//                tradeCouponPayments.add(optTradeCouponPayment.get());
            });
//            paymentMethod.setTradeCouponPayments(tradeCouponPayments);
        }

        if(paymentMethod.getPromoCouponPayment() != null){
            paymentMethod.getPromoCouponPayment().setPaymentMethod(optPaymentMethod.get());
            Optional<PromoCouponPayment> optPromoCouponPayment = promoCouponPaymentService.create(paymentMethod.getPromoCouponPayment());
            if(optPromoCouponPayment.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Erro ao criar o promo coupon payment!", null);

        }



        return paymentMethodRepository.findById(optPaymentMethod.get().getId());
    }

    public Optional<PaymentMethod> update(@Valid PaymentMethod paymentMethod){
        return Optional.of(paymentMethodRepository.save(paymentMethod));
    }

    public void deleteById(Long id){
        paymentMethodRepository.deleteById(id);
    }
}
