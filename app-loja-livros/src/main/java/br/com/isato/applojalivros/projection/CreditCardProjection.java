package br.com.isato.applojalivros.projection;

import br.com.isato.applojalivros.model.CreditCardBrand;

public interface CreditCardProjection {

    Long getId();
    String getHolderName();
    Integer getExpMonth();
    Integer getExpYear();
    String getCardNumber();
    String getCvv();
    String getObservation();
    CreditCardBrand getCreditCardBrand();
    Long getCustomerId();

}
