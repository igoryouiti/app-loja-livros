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

    @OneToOne
    @JoinColumn(name = "fk_trade_coupon_payment_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "tradeCouponPayment")
    private TradeCoupon tradeCoupon;


    @ManyToOne
    @JoinColumn(name = "fk_payment_method_id", referencedColumnName = "id")
    @JsonIgnoreProperties("tradeCouponPayments")
    private PaymentMethod paymentMethod;



}
