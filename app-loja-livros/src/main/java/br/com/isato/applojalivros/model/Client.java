package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_clients")
public class Client {

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

    @OneToOne(mappedBy = "client")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_residential_address_id", referencedColumnName = "id")
    private ResidentialAddress residentialAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_telephone_id", referencedColumnName = "id")
    private Telephone telephone;

    @OneToMany (mappedBy = "client", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "client")
    private List<BillingAddress> billingAddresses;

    @OneToMany (mappedBy = "client", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "client")
    private List<ShippingAddress> shippingAddresses;

    @OneToMany (mappedBy = "client", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "client")
    private List<CreditCard> creditCards;

}
