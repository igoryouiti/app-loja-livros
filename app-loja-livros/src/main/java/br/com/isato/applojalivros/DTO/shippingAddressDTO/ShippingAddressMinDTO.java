package br.com.isato.applojalivros.DTO.shippingAddressDTO;

import br.com.isato.applojalivros.model.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressMinDTO {

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

    public ShippingAddressMinDTO(ShippingAddress entity){
        BeanUtils.copyProperties(entity, this);
    }
}
