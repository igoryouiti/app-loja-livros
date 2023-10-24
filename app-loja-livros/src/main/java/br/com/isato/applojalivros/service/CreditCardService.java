package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.creditCardDTO.CreditCardDTO;
import br.com.isato.applojalivros.DTO.creditCardDTO.CreditCardMinDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidadorCardNumber;
import br.com.isato.applojalivros.business.validator.ValidadorCvv;
import br.com.isato.applojalivros.model.CreditCard;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.projection.CreditCardProjection;
import br.com.isato.applojalivros.repository.CreditCardRepository;
import br.com.isato.applojalivros.repository.CustomerRepository;
import jakarta.validation.Valid;
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
    @Autowired
    private CustomerRepository customerRepository;
    IValidator validator;

    public List<CreditCard> findAll(){
        return creditCardRepository.findAll();
    }

    public List<CreditCardDTO> searchAllByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        Optional<Customer> optCustomer = customerRepository.findById(id);

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);

        List<CreditCardProjection> creditCards = creditCardRepository.searchAllByCustomer(id);
        return creditCards.stream().map(CreditCardDTO::new).toList();
    }

    public Optional<CreditCardMinDTO> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        CreditCardMinDTO creditCardMinDTO = new CreditCardMinDTO(creditCardRepository.findById(id).get());
        return Optional.of(creditCardMinDTO);
    }

    public Optional<CreditCard> create(@Valid CreditCard creditCard){

        if(creditCard.getCustomer().getId() == null || creditCard.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCardNumber();
        if(!validator.validate(creditCard.getCardNumber()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O número do cartão deve conter de 13 a 19 numeros");

        validator = new ValidadorCvv();
        if(!validator.validate(creditCard.getCvv()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O cvv do cartão deve conter de 3 a 4 numeros");

        return Optional.of(creditCardRepository.save(creditCard));
    }

    public Optional<CreditCard> update(@Valid CreditCard creditCard){

        Optional<CreditCard> optCreditCard = creditCardRepository.findById(creditCard.getId());

        if(optCreditCard.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve conter id de um cartão de crédito válido");

        creditCard.setCustomer(optCreditCard.get().getCustomer());

        if(creditCard.getCustomer().getId() == null || creditCard.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validator = new ValidadorCardNumber();
        if(!validator.validate(creditCard.getCardNumber()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O número do cartão deve conter de 13 a 19 numeros");

        validator = new ValidadorCvv();
        if(!validator.validate(creditCard.getCvv()))
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
