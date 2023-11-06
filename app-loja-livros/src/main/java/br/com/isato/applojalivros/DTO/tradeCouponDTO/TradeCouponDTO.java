package br.com.isato.applojalivros.DTO.tradeCouponDTO;

import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.projection.TradeCouponProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeCouponDTO {

    private Long id;
    private Boolean active;
    private LocalDate createdDate;
    private BigDecimal value;
    private String description;
    private Customer customer;

    public TradeCouponDTO(TradeCouponProjection entity){
        BeanUtils.copyProperties(entity, this);
        customer = new Customer();
        customer.setId(entity.getCustomerId());
    }



}
