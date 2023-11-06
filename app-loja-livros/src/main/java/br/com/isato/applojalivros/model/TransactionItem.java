package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_transaction_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "fk_item_id", referencedColumnName = "id")
    @JsonIgnoreProperties("transactionItems")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "fk_transaction_id", referencedColumnName = "id")
    @JsonIgnoreProperties("transactionItems")
    private Transaction transaction;
}
