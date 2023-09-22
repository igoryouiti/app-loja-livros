package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.Dimension;
import br.com.isato.applojalivros.repository.BookRepository;
import br.com.isato.applojalivros.repository.DimensionRepository;
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
public class DimensionService {
    @Autowired
    private DimensionRepository dimensionRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Dimension> listAll(){
        return dimensionRepository.findAll();
    }

    public Optional<Dimension> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return dimensionRepository.findById(id);
    }

    public Optional<Dimension> create(@Valid Dimension dimension){

        if(dimension.getBook().getId() == null || dimension.getBook().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Book");

        return Optional.of(dimensionRepository.save(dimension));
    }

    public Optional<Dimension> update(Dimension dimension){

        if(dimension.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }

        Optional<Dimension> optReason = dimensionRepository.findById(dimension.getId());

        if(optReason.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Justificativa de Troca de status não encontrada", null);

        dimension.setBook(optReason.get().getBook());

        if(dimension.getBook().getId() == null || dimension.getBook().getId().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve conter id do Book");

        if(optReason.get().getBook().getId() != dimension.getBook().getId())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O id do Book deve ser o mesmo salvo no banco");


        return Optional.of(dimensionRepository.save(dimension));
    }

    public void deleteById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        Optional<Dimension> dimension = dimensionRepository.findById(id);

        if(dimension.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Dimensão não encontrado", null);

        dimensionRepository.deleteById(id);
    }
}
