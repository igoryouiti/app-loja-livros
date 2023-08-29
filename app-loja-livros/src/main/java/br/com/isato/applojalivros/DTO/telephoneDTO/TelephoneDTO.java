package br.com.isato.applojalivros.DTO.telephoneDTO;

import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.projection.TelephoneProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneDTO {

    private Long id;


    private String type;

    private String ddd;

    private String number;

    private Customer customer;

    public TelephoneDTO(TelephoneProjection entity){
        BeanUtils.copyProperties(entity, this);
        customer.setId(entity.getCustomerId());
    }
}
