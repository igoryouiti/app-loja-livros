package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.CreditCardPayment;
import br.com.isato.applojalivros.model.PaymentStatus;
import br.com.isato.applojalivros.repository.CreditCardPaymentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardPaymentService {
    @Autowired
    private CreditCardPaymentRepository creditCardPaymentRepository;

    public List<CreditCardPayment> findAll(){
        return creditCardPaymentRepository.findAll();
    }

    public Optional<CreditCardPayment> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return creditCardPaymentRepository.findById(id);
    }

    public Optional<CreditCardPayment> create(@Valid CreditCardPayment creditCardPayment){

        creditCardPayment.setPaymentStatus(PaymentStatus.EM_PROCESSAMENTO);

        // quando tiver sub-adquirente chamar aqui

        return Optional.of(creditCardPaymentRepository.save(creditCardPayment));
    }

    public Optional<CreditCardPayment> update(@Valid CreditCardPayment creditCardPayment){
        return Optional.of(creditCardPaymentRepository.save(creditCardPayment));
    }

    public void deleteById(Long id){
        creditCardPaymentRepository.deleteById(id);
    }
}
