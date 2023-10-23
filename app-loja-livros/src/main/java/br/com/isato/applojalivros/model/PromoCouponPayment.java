package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_promo_coupon_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromoCouponPayment extends AbstractPayment{

    @ManyToOne
    @JoinColumn(name = "fk_promo_coupons_id", referencedColumnName = "id")
    @JsonIgnoreProperties("promoCouponPayments")
    private PromoCoupon promoCoupon;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_payment_method_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "promoCouponPayment")
    private PaymentMethod paymentMethod;

}
