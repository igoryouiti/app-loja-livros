package br.com.isato.applojalivros.model;

import br.com.isato.applojalivros.DTO.bookStockDTO.CreateBookStockDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "tb_book_stocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookStock extends AbstractStock{

    @NotNull
    private LocalDateTime insertDate;

//    @OneToMany(mappedBy = "bookStock", cascade = CascadeType.REMOVE)
//    @JsonIgnoreProperties(value = "bookStock")
//    private List<Item> items;


    @OneToOne
    @JoinColumn(name = "fk_item_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "bookStock")
    private Item item;

    public BookStock(CreateBookStockDTO entity){
        BeanUtils.copyProperties(entity, this);
        item = new Item();
        item.setId(entity.getItem().getId());
    }

}
