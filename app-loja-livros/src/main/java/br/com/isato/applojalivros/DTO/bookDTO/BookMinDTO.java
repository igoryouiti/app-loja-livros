package br.com.isato.applojalivros.DTO.bookDTO;

import br.com.isato.applojalivros.model.Book;
import br.com.isato.applojalivros.model.PriceGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookMinDTO {

    private Long id;
    private Boolean active;
    private String author;
    private String year;
    private String title;
    private String imageUrl;
    private String editor;
    private String edition;
    private String isbn;
    private Integer pages;
    private String synopsis;
    private String barCode;
    private Float rawPrice;
    private Float sellPrice;
    private PriceGroup priceGroup;

    public BookMinDTO(Book entity){
        BeanUtils.copyProperties(entity, this);
    }
}
