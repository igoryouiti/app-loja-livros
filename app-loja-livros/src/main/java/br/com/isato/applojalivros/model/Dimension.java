package br.com.isato.applojalivros.model;

import br.com.isato.applojalivros.projection.DimensionProjection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "tb_dimensions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dimension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double height;

    @NotNull
    private Double width;

    @NotNull
    private Double depth;

    @NotNull
    private Double weight;

    @OneToOne
    @JoinColumn(name = "fk_book_id", referencedColumnName = "id")
    @JsonIgnoreProperties("dimension")
    private Book book;


    public Dimension(DimensionProjection entity){
        BeanUtils.copyProperties(entity, this);
        book = new Book();
        book.setId(entity.getBookId());
    }

}
