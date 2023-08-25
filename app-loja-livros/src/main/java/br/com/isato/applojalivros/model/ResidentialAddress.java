package br.com.isato.applojalivros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_residential_address")
public class ResidentialAddress extends AbstractAddress{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "residentialAddress")
    private Client client;
}
