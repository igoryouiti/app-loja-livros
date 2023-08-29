package br.com.isato.applojalivros.projection;

public interface TelephoneProjection {
    Long getId();
    String getType();
    String getDdd();
    String getNumber();
    Long getCustomerId();
}
