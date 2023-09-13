package br.com.isato.applojalivros.DTO.userDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO extends AbstractUserSenhasDTO{

    @NotNull
    private Long id;



}
