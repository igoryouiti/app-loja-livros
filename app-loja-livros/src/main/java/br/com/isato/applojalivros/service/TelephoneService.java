package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.Telephone;
import br.com.isato.applojalivros.repository.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TelephoneService {

    @Autowired
    private TelephoneRepository telephoneRepository;

    public List<Telephone> findAll(){
        return telephoneRepository.findAll();
    }

    public Optional<Telephone> findById(Long id){
        return telephoneRepository.findById(id);
    }

    public Optional<Telephone> create(Telephone telephone){
        return Optional.of(telephoneRepository.save(telephone));
    }

    public Optional<Telephone> update(Telephone telephone){
        return Optional.of(telephoneRepository.save(telephone));
    }

    public void delete(Long id){
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
