package br.com.isato.applojalivros.DTO.tradeCouponDTO;

import br.com.isato.applojalivros.model.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTradeCouponDTO {

    @NotNull
    private BigDecimal value;
    @NotNull
    private Long customerId;


}
