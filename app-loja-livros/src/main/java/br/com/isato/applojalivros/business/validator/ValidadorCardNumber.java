package br.com.isato.applojalivros.business.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCardNumber implements IValidator{

    @Override
    public Boolean validate(Object obj) {
        String number = (String) obj;

        number = number.replaceAll("\\s", "");
        number = number.replaceAll("-", "");

        Pattern pattern = Pattern.compile("^[0-9]{13,19}$");
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }
}
