package br.com.isato.applojalivros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_promo_coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromoCoupon extends AbstractCoupon{

    @NotNull
    private LocalDate expirationDate;

    @NotBlank
    private String secret;

}
