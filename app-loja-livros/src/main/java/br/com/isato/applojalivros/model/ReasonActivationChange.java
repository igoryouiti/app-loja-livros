package br.com.isato.applojalivros.model;

import br.com.isato.applojalivros.DTO.reasonActivationChangeDTO.ReasonActivationChangeUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "tb_reason_activation_change")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReasonActivationChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String justification;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ReasonCategory reasonCategory;

    @OneToOne
    @JoinColumn(name = "fk_book_id", referencedColumnName = "id")
    @JsonIgnoreProperties("reasonActivationChange")
    @JsonIgnore
    private Book book;

    public ReasonActivationChange(ReasonActivationChangeUpdateDTO entity){
        BeanUtils.copyProperties(entity, this);
        book = new Book();
        book.setId(entity.getBook().getId());
    }

}
