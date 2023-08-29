package br.com.isato.applojalivros.business.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCep implements IValidator{

    @Override
    public Boolean validate(Object obj) {
        String cep = (String) obj;

        cep = cep.replace(" ", "");
        cep = cep.replace("-", "");
        cep = cep.replace(".", "");

        Pattern pattern = Pattern.compile("^\\d{8}$");
        Matcher matcher = pattern.matcher(cep);

        return matcher.matches();
    }
}
