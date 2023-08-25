package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_shipping_address")
public class ShippingAddress extends AbstractAddress{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_client_id", referencedColumnName = "id")
    @JsonIgnoreProperties("shippingAddresses")
    private Client client;
}