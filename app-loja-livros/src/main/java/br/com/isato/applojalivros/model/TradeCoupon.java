package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_trade_coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeCoupon extends AbstractCoupon{

    @ManyToOne
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties("tradeCoupons")
    private Customer customer;

    @OneToOne(mappedBy = "tradeCoupon")
    @JsonIgnoreProperties(value = "tradeCoupon")
    private TradeCouponPayment tradeCouponPayment;

}
