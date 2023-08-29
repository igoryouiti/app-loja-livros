package br.com.isato.applojalivros.projection;

public interface CreditCardProjection {

    Long getId();
    String getHolderName();
    Integer getExpMonth();
    Integer getExpYear();
    String getCardNumber();
    String getCvv();
    String getCreditCardBrand();
    Long getCustomerId();

}
