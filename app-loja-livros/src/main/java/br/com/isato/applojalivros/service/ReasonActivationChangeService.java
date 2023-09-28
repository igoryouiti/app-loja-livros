package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.ReasonActivationChange;
import br.com.isato.applojalivros.repository.BookRepository;
import br.com.isato.applojalivros.repository.ReasonActivationChangeRepository;
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
public class ReasonActivationChangeService {

    @Autowired
    private ReasonActivationChangeRepository reasonActivationChangeRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<ReasonActivationChange> listAll(){
        return reasonActivationChangeRepository.findAll();
    }

    public Optional<ReasonActivationChange> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return reasonActivationChangeRepository.findById(id);
    }

    public Optional<ReasonActivationChange> create(@Valid ReasonActivationChange reasonActivationChange){

        if(reasonActivationChange.getBook().getId() == null || reasonActivationChange.getBook().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Book");

        return Optional.of(reasonActivationChangeRepository.save(reasonActivationChange));
    }


    public Optional<ReasonActivationChange> update(ReasonActivationChange reasonActivationChange){

        if(reasonActivationChange.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        Optional<ReasonActivationChange> optReason = reasonActivationChangeRepository.findById(reasonActivationChange.getId());

        if(optReason.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Justificativa de Troca de status não encontrada", null);

        if(reasonActivationChange.getBook().getId() == null || reasonActivationChange.getBook().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Book");

        if(optReason.get().getBook().getId() != reasonActivationChange.getBook().getId())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O id do Book deve ser o mesmo relacionado a razão");

        reasonActivationChange.setBook(optReason.get().getBook());

        return Optional.of(reasonActivationChangeRepository.save(reasonActivationChange));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<ReasonActivationChange> reasonActivationChange = reasonActivationChangeRepository.findById(id);

        if(reasonActivationChange.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Justificativa não encontrada", null);

        reasonActivationChangeRepository.deleteById(id);
    }

}
