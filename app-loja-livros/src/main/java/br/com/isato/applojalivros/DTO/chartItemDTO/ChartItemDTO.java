package br.com.isato.applojalivros.DTO.chartItemDTO;

import br.com.isato.applojalivros.model.Chart;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.projection.ChartItemProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.management.MBeanServer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChartItemDTO {

    private Long id;
    private Chart chart;


    public ChartItemDTO(ChartItemProjection entity){
        BeanUtils.copyProperties(entity, this);
        chart = new Chart();
        chart.setId(entity.getChartId());

    }
}
