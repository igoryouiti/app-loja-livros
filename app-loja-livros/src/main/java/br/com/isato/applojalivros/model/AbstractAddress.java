package br.com.isato.applojalivros.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class AbstractAddress {

    @NotBlank
    private String typeResidence;
    @NotBlank
    private String typePublicPlace;
    @NotBlank
    private String publicPlace;
    @NotBlank
    private String number;
    @NotBlank
    private String neighborhood;
    @NotBlank
    private String cep;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @Column(columnDefinition = "TEXT")
    private String observation;

}
