package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_book_stocks_temp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookStockTemp extends AbstractStock{

    @OneToMany(mappedBy = "bookStockTemp", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "bookStockTemp")
    private List<ChartItem> chartItems;
}
