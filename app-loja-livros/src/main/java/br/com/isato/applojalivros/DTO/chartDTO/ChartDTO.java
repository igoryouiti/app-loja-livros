package br.com.isato.applojalivros.DTO.chartDTO;

import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.projection.ChartProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChartDTO {
    private Long id;
    private Customer customer;

    public ChartDTO (ChartProjection entity){
        BeanUtils.copyProperties(entity, this);
        customer = new Customer();
        customer.setId(entity.getCustomerId());
    }

}
