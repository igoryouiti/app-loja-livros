package br.com.isato.applojalivros.model;

import br.com.isato.applojalivros.projection.CategoryProjection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnoreProperties(value = "categories")
    private Set<Book> books;

    public Category(CategoryProjection entity){
        BeanUtils.copyProperties(entity, this);
        books = new HashSet<Book>();
        books.add(new Book(entity.getBookId()));
    }

}
