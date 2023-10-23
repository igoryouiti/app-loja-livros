package br.com.isato.applojalivros.model;

public enum TransactionStatus {

    EM_PROCESSAMENTO("em_processamento"),
    EM_TRANSPORTE("em_transporte"),
    ENTREGUE("entregue"),
    EM_TROCA("em_troca"),
    TROCA_AUTORIZADA("troca_autorizada"),
    APROVADA("aprovada"),
    REPROVADA("reprovada"),
    TROCADA("trocada");


    private final String displayName;

    TransactionStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
