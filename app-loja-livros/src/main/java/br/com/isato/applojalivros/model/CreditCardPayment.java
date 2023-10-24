package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_credit_card_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardPayment extends AbstractPayment{

    @ManyToOne
    @JoinColumn(name = "fk_credit_card_id", referencedColumnName = "id")
    @JsonIgnoreProperties("creditCardPayments")
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = "fk_payment_method_id", referencedColumnName = "id")
    @JsonIgnoreProperties("creditCardPayments")
    private PaymentMethod paymentMethod;

}
