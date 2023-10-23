package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "tb_book_stocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookStock extends AbstractStock{

    @NotNull
    private LocalDate insertDate;

    @OneToMany(mappedBy = "bookStock", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "bookStock")
    private List<Item> items;

}
