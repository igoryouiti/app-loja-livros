package br.com.isato.applojalivros.model;

import java.math.BigDecimal;

public enum PriceGroup {

    PROMOTION(new BigDecimal("1.10")),
    LOW(new BigDecimal("1.15")),
    MEDIUM(new BigDecimal("1.20")),
    HIGH(new BigDecimal("1.30"));

    private final BigDecimal value;

    PriceGroup(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public BigDecimal getValue(){return value;}
}
