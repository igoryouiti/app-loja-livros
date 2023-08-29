package br.com.isato.applojalivros.business.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorDdd implements IValidator{
    @Override
    public Boolean validate(Object obj) {
        String ddd = (String) obj;

        String regex = "^[0-9]{2}$"; // Regex para duas letras numéricas
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ddd);
        return matcher.matches();

    }
}
