package br.com.isato.applojalivros.DTO.billingAddressDTO;

import br.com.isato.applojalivros.model.BillingAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingMinDTO {

    private Long id;
    private String typeResidence;
    private String typePublicPlace;
    private String publicPlace;
    private String number;
    private String neighborhood;
    private String cep;
    private String city;
    private String state;
    private String country;
    private String observation;

    public BillingMinDTO(BillingAddress entity){
        BeanUtils.copyProperties(entity, this);
    }
}
