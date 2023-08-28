package br.com.isato.applojalivros.business.validator;

import java.util.regex.Pattern;

public class ValidatorEmail implements IValidator{
    @Override
    public Boolean validate(Object obj) {
        String email = (String) obj;
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        return Pattern.matches(regex, email);
    }
}
