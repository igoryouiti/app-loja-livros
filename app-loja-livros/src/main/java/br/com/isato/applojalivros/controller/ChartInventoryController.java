package br.com.isato.applojalivros.controller;


import br.com.isato.applojalivros.DTO.chartDTO.ChartDTO;
import br.com.isato.applojalivros.DTO.chartItemDTO.ChartItemDTO;
import br.com.isato.applojalivros.model.Chart;
import br.com.isato.applojalivros.model.ChartItem;
import br.com.isato.applojalivros.service.ChartItemService;
import br.com.isato.applojalivros.service.ChartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart-inventory")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChartInventoryController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private ChartItemService chartItemService;


    @GetMapping("/charts")
    public ResponseEntity<List<Chart>> getAllCharts(){
        return ResponseEntity.ok(chartService.findAll());
    }

    @GetMapping("/charts/{id}")
    public ResponseEntity<Chart> getChartById(@PathVariable Long id){
        return chartService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/charts/customer/{id}")
    public ResponseEntity<ChartDTO> getChartByCustomerId(@PathVariable Long id){
        return chartService.findChartByCustomer(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/charts")
    public ResponseEntity<Chart> createChart(@RequestBody @Valid Chart chart){
        return chartService.create(chart)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

//    @PutMapping("/charts/items")
//    public ResponseEntity<Chart> updateItemsInChart (@RequestBody Chart chart){
//        return chartService.update(chart)
//                .map(response -> ResponseEntity.ok(response))
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

    @GetMapping("/chart-item")
    public ResponseEntity<List<ChartItem>> getAllChartItems(){
        return ResponseEntity.ok(chartItemService.findAll());
    }

    @GetMapping("/chart-item/{id}")
    public ResponseEntity<ChartItem> getChartItemsById(@PathVariable Long id){
        return chartItemService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/chart-item/chart/{id}")
    public ResponseEntity<ChartItemDTO> getItemByChartId(@PathVariable Long id){
        return chartItemService.findItemsByChart(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/chart-item")
    public ResponseEntity<ChartItem> createChartItem(@RequestBody ChartItem chartItem){
        System.out.println(chartItem.toString());

        return chartItemService.create(chartItem)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/chart-item")
    public ResponseEntity<ChartItem> updateChartItem(@RequestBody ChartItem chartItem){
        return chartItemService.updateQuantity(chartItem)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/chart-item/{id}")
    public ResponseEntity deleteChartItem(@PathVariable Long id){
        chartItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
