package br.com.isato.applojalivros.DTO.creditCardDTO;

import br.com.isato.applojalivros.model.CreditCard;
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
public class CreditCardMinDTO {

    private Long id;

    private String holderName;

    private Integer expMonth;

    private Integer expYear;

    private String cardNumber;

    private String cvv;

    private String observation;

    private CreditCardBrand creditCardBrand;

    public CreditCardMinDTO(CreditCard entity){
        BeanUtils.copyProperties(entity, this);
    }
}
