package br.com.isato.applojalivros.DTO.paymentMethodDTO;

import br.com.isato.applojalivros.model.CreditCardPayment;
import br.com.isato.applojalivros.model.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {

    private Long id;

    private BigDecimal totalPrice;

    @JsonIgnoreProperties(value = "paymentMethod")
    private CreditCardPayment creditCardPayment;

    public PaymentMethodDTO(PaymentMethod entity){
        BeanUtils.copyProperties(entity, this);
    }

}
