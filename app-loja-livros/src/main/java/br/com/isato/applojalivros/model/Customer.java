package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 128, message = "O nome deve ter no mínimo 2 e no máximo 128 caracteres.")
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private LocalDate birthday;

    @NotBlank
    private String cpf;

    @OneToOne(mappedBy = "customer")
    private User user;

    @OneToOne(mappedBy = "customer")
    private Address address;


    @OneToOne(mappedBy = "customer")
    private Telephone telephone;

    @OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "customer")
    private List<BillingAddress> billingAddresses;

    @OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "customer")
    private List<ShippingAddress> shippingAddresses;

    @OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "customer")
    private List<CreditCard> creditCards;

}
