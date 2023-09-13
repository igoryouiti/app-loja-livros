package br.com.isato.applojalivros.projection;

public interface UserProjection {

    Long getId();
    String getEmail();
    String getPassword();
    Boolean getActive();
    Long getCustomerId();

}
