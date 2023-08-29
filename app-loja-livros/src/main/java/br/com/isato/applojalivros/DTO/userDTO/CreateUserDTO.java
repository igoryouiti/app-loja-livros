package br.com.isato.applojalivros.DTO.userDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO extends AbstractUserSenhasDTO{

    @NotBlank
    private String email;

}
