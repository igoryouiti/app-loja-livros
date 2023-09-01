package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.creditCardDTO.CreditCardDTO;
import br.com.isato.applojalivros.DTO.telephoneDTO.TelephoneDTO;
import br.com.isato.applojalivros.business.validator.IValidator;
import br.com.isato.applojalivros.business.validator.ValidadorDdd;
import br.com.isato.applojalivros.business.validator.ValidadorTelephone;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.model.Telephone;
import br.com.isato.applojalivros.projection.TelephoneProjection;
import br.com.isato.applojalivros.repository.CustomerRepository;
import br.com.isato.applojalivros.repository.TelephoneRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class TelephoneService {

    @Autowired
    private TelephoneRepository telephoneRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private IValidator validador;

    public List<Telephone> findAll(){
        return telephoneRepository.findAll();
    }

    public Optional<TelephoneDTO> searchByCustomer(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        Optional<Customer> optCustomer = customerRepository.findById(id);

        if(optCustomer.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve ser passado o id cliente válido (Long id)!", null);

        TelephoneDTO telephoneDTO = new TelephoneDTO(telephoneRepository.searchByCustomer(id));
        return Optional.of(telephoneDTO);
    }

    public Optional<Telephone> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return telephoneRepository.findById(id);
    }

    public Optional<Telephone> create(@Valid Telephone telephone){
        if(telephone.getCustomer().getId() == null || telephone.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validador = new ValidadorDdd();
        System.out.println(telephone.getDdd());
        if(!validador.validate(telephone.getDdd()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "DDD é uma String com 2 numeros");

        validador = new ValidadorTelephone();
        if(!validador.validate(telephone.getNumber()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O numero deve ser uma String com 8 a 9 numeros");

        return Optional.of(telephoneRepository.save(telephone));
    }

    public Optional<Telephone> update(Telephone telephone){

        Optional<Telephone> optTelephone = findById(telephone.getId());

        if(optTelephone.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deve conter id de um telefone válido");

        telephone.setCustomer(optTelephone.get().getCustomer());

        if(telephone.getCustomer().getId() == null || telephone.getCustomer().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Customer");

        validador = new ValidadorDdd();
        if(!validador.validate(telephone.getDdd()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "DDD é uma String com 2 numeros");

        validador = new ValidadorTelephone();
        if(!validador.validate(telephone.getNumber()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O numero deve ser uma String com 8 a 9 numeros");

        return Optional.of(telephoneRepository.save(telephone));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<Telephone> telephone = telephoneRepository.findById(id);

        if(telephone.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Telefone não encontrado", null);

        telephoneRepository.deleteById(id);
    }

}
