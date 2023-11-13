package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_items")
@Getter
@Setter
@NoArgsConstructor
public class Item extends AbstractItem{

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "item")
    @JsonIgnore
    private List<ChartItem> chartItems;

//    @ManyToOne
//    @JoinColumn(name = "fk_book_stocks_id", referencedColumnName = "id")
//    @JsonIgnoreProperties("items")
//    private BookStock bookStock;

    @OneToOne(mappedBy = "item")
    @JsonIgnoreProperties(value = "item")
    private BookStock bookStock;

}
