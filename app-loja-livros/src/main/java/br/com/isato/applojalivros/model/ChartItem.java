package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_chart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "fk_chart_id", referencedColumnName = "id")
    @JsonIgnoreProperties("chartItems")
    private Chart chart;

    @ManyToOne
    @JoinColumn(name = "fk_item_id", referencedColumnName = "id")
    @JsonIgnoreProperties("chartItems")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "fk_book_stocks_temp_id", referencedColumnName = "id")
    @JsonIgnoreProperties("chartItems")
    private BookStockTemp bookStockTemp;

//    @ManyToOne
//    @JoinColumn(name = "fk_transaction_id", referencedColumnName = "id")
//    @JsonIgnoreProperties("chartItems")
//    private Transaction transaction;

}
