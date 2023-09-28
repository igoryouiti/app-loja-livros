package br.com.isato.applojalivros.model;

import br.com.isato.applojalivros.DTO.bookDTO.BookMinDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity
@Table(name = "tb_books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;

    @NotBlank
    private String author;

    @NotBlank
    private String year;

    @NotBlank
    private String title;

    @URL
    private String imageUrl;

    @NotBlank
    private String editor;

    @NotBlank
    private String edition;

    @NotBlank
    private String isbn;

    @NotNull
    private Integer pages;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String synopsis;

    @NotBlank
    private String barCode;

    @NotNull
    private Float rawPrice;

    private Float sellPrice;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "book")
    private List<Category> categories;

    @OneToOne(mappedBy = "book", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "book")
    private Dimension dimension;

    @OneToOne(mappedBy = "book", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "book")
    private ReasonActivationChange reasonActivationChange;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PriceGroup priceGroup;

    public Book(BookMinDTO entity){
        BeanUtils.copyProperties(entity, this);
    }

}
