package br.com.isato.applojalivros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_telephone")
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

    @OneToOne(mappedBy = "telephone")
    private Client client;
}
