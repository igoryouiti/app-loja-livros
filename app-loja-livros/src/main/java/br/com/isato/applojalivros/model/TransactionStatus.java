package br.com.isato.applojalivros.model;

public enum TransactionStatus {

    EM_PROCESSAMENTO("em_processamento"),
    PEDIDO_APROVADO("pedido_aprovado"),
    PAGAMENTO_RECUSADO("pagamento_recusado"),
    EM_TRANSITO("em_transito"),
    ENTREGUE("entregue"),
    TROCA_SOLICITADA("troca_solicitada"),
    TROCA_APROVADA("troca_aprovada"),
    TROCA_REPROVADA("troca_reprovada"),
    TROCA_REALIZADA("troca_realizada");


    private final String displayName;

    TransactionStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
