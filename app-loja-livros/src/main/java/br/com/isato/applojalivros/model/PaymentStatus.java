package br.com.isato.applojalivros.model;

public enum PaymentStatus {
    APROVADO("aprovado"),
    EM_ANALISE("em_analise"),
    EM_PROCESSAMENTO("em_processamento"),
    INVALIDO("invalido");


    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
