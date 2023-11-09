package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_payment_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_transaction_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "paymentMethod")
    private Transaction transaction;

    @OneToOne(mappedBy = "paymentMethod")
    @JsonIgnoreProperties(value = "paymentMethod")
    private PromoCouponPayment promoCouponPayment;

    @OneToMany (mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "paymentMethod")
    private List<TradeCouponPayment> tradeCouponPayments;

    @OneToMany (mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "paymentMethod")
    private List<CreditCardPayment> creditCardPayments;

}
