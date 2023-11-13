package br.com.isato.applojalivros.DTO.bookStockDTO;

import br.com.isato.applojalivros.model.Item;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookStockDTO {

    @NotNull
    private Integer quantity;

    @NotNull
    private Item item;

}
