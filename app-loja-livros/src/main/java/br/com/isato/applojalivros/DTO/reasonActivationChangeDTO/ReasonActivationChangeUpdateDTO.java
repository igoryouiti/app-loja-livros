package br.com.isato.applojalivros.DTO.reasonActivationChangeDTO;

import br.com.isato.applojalivros.DTO.bookDTO.BookIdDTO;
import br.com.isato.applojalivros.model.ReasonCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReasonActivationChangeUpdateDTO {

    private Long id;
    private String justification;
    private ReasonCategory reasonCategory;
    private BookIdDTO book;
}
