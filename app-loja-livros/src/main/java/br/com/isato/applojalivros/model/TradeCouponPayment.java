package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_trade_coupon_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeCouponPayment extends AbstractPayment{

    @OneToMany (mappedBy = "tradeCouponPayment", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "tradeCouponPayment")
    private List<TradeCoupon> tradeCoupons;


    @ManyToOne
    @JoinColumn(name = "fk_payment_method_id", referencedColumnName = "id")
    @JsonIgnoreProperties("tradeCouponPayments")
    private PaymentMethod paymentMethod;



}
