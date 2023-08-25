package br.com.isato.applojalivros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_telephone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String type;
    @NotBlank
    private String ddd;
    @NotBlank
    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "id")
    private Customer customer;
}
