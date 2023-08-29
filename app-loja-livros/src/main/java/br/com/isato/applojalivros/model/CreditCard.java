package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_credit_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 64, message = "O nome do portador do cartão deve ter no máximo 64 caracteres")
    private String holder_name;

    @NotNull
    private Integer exp_month;

    @NotNull
    private Integer exp_year;

    @NotBlank
    @Size(min = 13, max = 19, message = "O número do cartão deve conter de 13 a 19 digitos")
    private String card_number;

    @NotBlank
    @Size(min = 3, max = 4, message = "O cvv deve conter de 3 a 4 digitos")
    private String cvv;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private CreditCardBrand creditCardBrand;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties("creditCards")
    private Customer customer;

}
