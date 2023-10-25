package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.ChartItem;
import br.com.isato.applojalivros.repository.ChartItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChartItemService {

    @Autowired
    private ChartItemRepository chartItemRepository;

    public List<ChartItem> findAll(){
        return chartItemRepository.findAll();
    }

    public Optional<ChartItem> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return chartItemRepository.findById(id);
    }

    public Optional<ChartItem> create(@Valid ChartItem chartItem){
        return Optional.of(chartItemRepository.save(chartItem));
    }

    public Optional<ChartItem> update(@Valid ChartItem chartItem){
        return Optional.of(chartItemRepository.save(chartItem));
    }

    public void deleteById(Long id){
        chartItemRepository.deleteById(id);
    }
}
