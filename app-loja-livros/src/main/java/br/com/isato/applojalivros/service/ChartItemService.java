package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.DTO.chartDTO.ChartDTO;
import br.com.isato.applojalivros.DTO.chartItemDTO.ChartItemDTO;
import br.com.isato.applojalivros.model.ChartItem;
import br.com.isato.applojalivros.model.Item;
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
    @Autowired
    private ItemService itemService;

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

    public Optional<ChartItemDTO> findItemsByChart(Long chartId){
        if(chartId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id de cliente válido (Long id)!", null);

        ChartItemDTO chartItemDTO = new ChartItemDTO(chartItemRepository.searchByChart(chartId));

        return Optional.of(chartItemDTO);
    }

    public Optional<ChartItem> create(ChartItem chartItem){

        System.out.println(chartItem.toString());


        return Optional.of(chartItemRepository.save(chartItem));
    }

    public Optional<ChartItem> updateQuantity(ChartItem chartItem){

        Optional<ChartItem> optChartItem = findById(chartItem.getId());

        optChartItem.get().setQuantity(chartItem.getQuantity());

        return Optional.of(chartItemRepository.save(optChartItem.get()));
    }

    public void deleteById(Long id){
        chartItemRepository.deleteById(id);
    }
}
