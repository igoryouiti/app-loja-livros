package br.com.isato.applojalivros.business.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCvv implements IValidator{


    @Override
    public Boolean validate(Object obj) {
        String cvv = (String) obj;

        Pattern pattern = Pattern.compile("^[0-9]{3,4}$");
        Matcher matcher = pattern.matcher(cvv);

        return matcher.matches();
    }
}
