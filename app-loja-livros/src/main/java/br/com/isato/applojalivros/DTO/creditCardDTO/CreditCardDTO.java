package br.com.isato.applojalivros.DTO.creditCardDTO;

import br.com.isato.applojalivros.model.CreditCardBrand;
import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.projection.CreditCardProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

    private Long id;

    private String holder_name;

    private Integer exp_month;

    private Integer exp_year;

    private String card_number;

    private String cvv;

    private CreditCardBrand creditCardBrand;

    private Customer customer;

    public CreditCardDTO(CreditCardProjection entity){
        BeanUtils.copyProperties(entity, this);
        customer.setId(entity.getCustomerId());
    }

}
