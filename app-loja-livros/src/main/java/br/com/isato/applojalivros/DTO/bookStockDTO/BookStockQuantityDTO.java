package br.com.isato.applojalivros.DTO.bookStockDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookStockQuantityDTO {

    private Long id;

    private Integer quantity;
}
