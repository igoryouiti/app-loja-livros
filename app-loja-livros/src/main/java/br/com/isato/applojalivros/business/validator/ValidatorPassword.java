package br.com.isato.applojalivros.business.validator;

public class ValidatorPassword implements IValidator {

    @Override
    public Boolean validate(Object obj) {
        String password = (String) obj;
        return password.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}");
    }
}
