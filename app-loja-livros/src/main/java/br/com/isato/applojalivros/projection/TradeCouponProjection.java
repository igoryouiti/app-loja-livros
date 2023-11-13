package br.com.isato.applojalivros.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TradeCouponProjection {

    Long getId();
    Boolean getActive();
    LocalDate getCreatedDate();
    String getDescription();
    BigDecimal getValue();
    Long getCustomerId();

}
