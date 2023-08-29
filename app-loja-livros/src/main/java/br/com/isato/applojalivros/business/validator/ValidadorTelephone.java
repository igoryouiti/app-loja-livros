package br.com.isato.applojalivros.business.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorTelephone implements IValidator{
    @Override
    public Boolean validate(Object obj) {
        String telephone = (String) obj;

        telephone = telephone.replaceAll("\\s", "");
        telephone = telephone.replaceAll("-", "");

        Pattern pattern = Pattern.compile("^[0-9]{8,9}$");
        Matcher matcher = pattern.matcher(telephone);

        return matcher.matches();
    }
}
