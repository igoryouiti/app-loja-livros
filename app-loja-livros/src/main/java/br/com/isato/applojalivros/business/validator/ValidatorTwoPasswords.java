package br.com.isato.applojalivros.business.validator;

import br.com.isato.applojalivros.DTO.AbstractUserSenhasDTO;
import br.com.isato.applojalivros.DTO.CreateUserDTO;
import jakarta.validation.OverridesAttribute;

public class ValidatorTwoPasswords implements IValidator{

    @Override
    public Boolean validate(Object obj) {
        AbstractUserSenhasDTO userDTO = (AbstractUserSenhasDTO) obj;
        return(userDTO.getPassword().equals(userDTO.getPassword2()));
    }
}