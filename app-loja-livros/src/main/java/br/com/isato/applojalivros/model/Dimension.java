package br.com.isato.applojalivros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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




}
