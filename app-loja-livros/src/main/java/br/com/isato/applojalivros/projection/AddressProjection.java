package br.com.isato.applojalivros.projection;

public interface AddressProjection {
    Long getId();
    String getCep();
    String getCity();
    String getCounty();
    String getNeighborhood();
    String getNumber();
    String getObrservation();
    String getPublicPlace();
    String getState();
    String getTypePublicPlace();
    String getTypeResidence();
    Long getCustomerId();
}
