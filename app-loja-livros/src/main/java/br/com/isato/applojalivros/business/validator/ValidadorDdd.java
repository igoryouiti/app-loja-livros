package br.com.isato.applojalivros.business.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorDdd implements IValidator{
    @Override
    public Boolean validate(Object obj) {
        String ddd = (String) obj;

        Pattern pattern = Pattern.compile("^\\d{2}$");
        Matcher matcher = pattern.matcher(ddd);

        return matcher.matches();
    }
}
