package br.com.isato.applojalivros.projection;

public interface BillingAddressProjection {
    Long getId();
    String getCep();
    String getCity();
    String getCountry();
    String getNeighborhood();
    String getNumber();
    String getObservation();
    String getPublicPlace();
    String getState();
    String getTypePublicPlace();
    String getTypeResidence();
    Long getCustomerId();
}
