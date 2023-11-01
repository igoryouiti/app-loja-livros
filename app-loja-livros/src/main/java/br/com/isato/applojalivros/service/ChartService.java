package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.chartDTO.ChartDTO;
import br.com.isato.applojalivros.model.Chart;
import br.com.isato.applojalivros.repository.ChartRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChartService {

    @Autowired
    private ChartRepository chartRepository;

    public List<Chart> findAll(){
        return chartRepository.findAll();
    }

    public Optional<Chart> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return chartRepository.findById(id);
    }

    public Optional<ChartDTO> findChartByCustomer(Long customerId){
        if(customerId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id de cliente válido (Long id)!", null);

        ChartDTO chartDTO = new ChartDTO(chartRepository.searchByCustomer(customerId));

        return Optional.of(chartDTO);
    }

    public Optional<Chart> create(@Valid Chart chart){



        return Optional.of(chartRepository.save(chart));
    }

    public Optional<Chart> update(@Valid Chart chart){
        return Optional.of(chartRepository.save(chart));
    }

    public void deleteById(Long id){
        chartRepository.deleteById(id);
    }

}
