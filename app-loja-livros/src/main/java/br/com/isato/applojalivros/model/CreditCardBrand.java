package br.com.isato.applojalivros.model;

public enum CreditCardBrand {
    ELO("Elo"),
    VISA("Visa"),
    MASTERCARD("Mastercard"),
    AMEX("American Express"),
    DISCOVER("Discover"),
    JCB("JCB"),
    DINERS("Diners Club"),
    HIPERCARD("Hipercard"),
    AURA("Aura");

    private final String displayName;

    CreditCardBrand(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
