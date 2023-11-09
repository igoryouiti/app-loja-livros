package br.com.isato.applojalivros.model;

import br.com.isato.applojalivros.DTO.customerDTO.CreateCustomerDTO;
import br.com.isato.applojalivros.DTO.customerDTO.UpdateCustomerDTO;
import br.com.isato.applojalivros.DTO.userDTO.UpdateUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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

    @NotNull
    private LocalDate birthday;

    @NotBlank
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres numéricos")
    private String cpf;

    private Integer ranking;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "customer")
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    @JsonIgnoreProperties(value = "customer")
    private Address address;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    @JsonIgnoreProperties(value = "customer")
    private Telephone telephone;

    @JsonIgnore
    @OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "customer")
    private List<BillingAddress> billingAddresses;

    @JsonIgnore
    @OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "customer")
    private List<ShippingAddress> shippingAddresses;

    @JsonIgnore
    @OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "customer")
    private List<CreditCard> creditCards;

    @JsonIgnore
    @OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "customer")
    private List<TradeCoupon> tradeCoupons;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    @JsonIgnoreProperties(value = "customer")
    private Chart chart;

    @JsonIgnore
    @OneToMany (mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "customer")
    private List<Transaction> transactions;

    public Customer(CreateCustomerDTO entity){
        BeanUtils.copyProperties(entity, this);
    }
    public Customer(UpdateCustomerDTO entity){
        BeanUtils.copyProperties(entity, this);
    }

}
