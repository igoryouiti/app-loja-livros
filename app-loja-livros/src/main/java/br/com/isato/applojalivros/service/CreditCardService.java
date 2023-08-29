package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.creditCardDTO.CreditCardDTO;
import br.com.isato.applojalivros.DTO.shippingAddressDTO.ShippingAddressDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidadorCardNumber;
import br.com.isato.applojalivros.business.validator.ValidadorCvv;
import br.com.isato.applojalivros.model.CreditCard;
import br.com.isato.applojalivros.projection.CreditCardProjection;
import br.com.isato.applojalivros.projection.ShippingAddressProjection;
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

    IValidator validator;

    public List<CreditCard> findAll(){
        return creditCardRepository.findAll();
    }

    public List<CreditCardDTO> searchAllByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        List<CreditCardProjection> creditCards = creditCardRepository.searchAllByCustomer(id);
        return creditCards.stream().map(CreditCardDTO::new).toList();
    }

    public Optional<CreditCard> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return creditCardRepository.findById(id);
    }

    public Optional<CreditCard> create(CreditCard creditCard){

        if(creditCard.getCustomer().getId() == null || creditCard.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCardNumber();
        if(validator.validate(creditCard.getCard_number()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O número do cartão deve conter de 13 a 19 numeros");

        validator = new ValidadorCvv();
        if(validator.validate(creditCard.getCard_number()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O cvv do cartão deve conter de 3 a 4 numeros");

        return Optional.of(creditCardRepository.save(creditCard));
    }

    public Optional<CreditCard> update(CreditCard creditCard){

        if(findById(creditCard.getId()).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve conter id de um cartão de crédito válido");

        if(creditCard.getCustomer().getId() == null || creditCard.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCardNumber();
        if(validator.validate(creditCard.getCard_number()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O número do cartão deve conter de 13 a 19 numeros");

        validator = new ValidadorCvv();
        if(validator.validate(creditCard.getCard_number()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O cvv do cartão deve conter de 3 a 4 numeros");

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
