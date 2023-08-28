package br.com.isato.applojalivros.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractUserSenhasDTO {

    @NotBlank
    private String password;

    @NotBlank
    private String password2;
}
