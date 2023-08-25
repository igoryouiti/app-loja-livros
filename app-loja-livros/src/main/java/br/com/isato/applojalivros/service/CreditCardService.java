package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.CreditCard;
import br.com.isato.applojalivros.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public List<CreditCard> findAll(){
        return creditCardRepository.findAll();
    }

    public Optional<CreditCard> findById(Long id){
        return creditCardRepository.findById(id);
    }

    public Optional<CreditCard> create(CreditCard creditCard){
        return Optional.of(creditCardRepository.save(creditCard));
    }

    public Optional<CreditCard> update(CreditCard creditCard){
        return Optional.of(creditCardRepository.save(creditCard));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<CreditCard> creditCard = creditCardRepository.findById(id);

        if(creditCard.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cartão de crédito não encontrado", null);

        creditCardRepository.deleteById(id);
    }
}
