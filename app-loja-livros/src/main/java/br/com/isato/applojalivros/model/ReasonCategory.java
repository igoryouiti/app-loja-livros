package br.com.isato.applojalivros.model;

public enum ReasonCategory {
    FORA_MERCADO("Fora do mercado"),
    NOVA_ENTRADA("Nova entrada"),
    TROCA_ENTRADA("Entrada de troca"),
    OUTROS("Outros, justificar!");

    private final String displayName;

    ReasonCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
