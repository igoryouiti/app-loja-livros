package br.com.isato.applojalivros.projection;

import br.com.isato.applojalivros.model.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionProjection {

    Long getId();
    LocalDateTime getDateTime();
    BigDecimal getTotalPrice();
    TransactionStatus getTransactionStatus();
    Long getCustomerId();


}
