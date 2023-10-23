package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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



    @OneToMany(mappedBy = "promoCoupon", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "promoCoupon")
    private List<PromoCouponPayment> promoCouponPayments;

}
