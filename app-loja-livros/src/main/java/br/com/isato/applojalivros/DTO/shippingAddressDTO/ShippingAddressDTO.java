package br.com.isato.applojalivros.DTO.shippingAddressDTO;

import br.com.isato.applojalivros.model.Customer;
import br.com.isato.applojalivros.projection.ShippingAddressProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressDTO {


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

    private Customer customer;

    public ShippingAddressDTO(ShippingAddressProjection entity){
        BeanUtils.copyProperties(entity, this);
        customer.setId(entity.getCustomerId());
    }
}
