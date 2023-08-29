package br.com.isato.applojalivros.business.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCpf implements IValidator{

    @Override
    public Boolean validate(Object obj) {
        String cpf = (String) obj;

        // Remover possíveis caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF possui 11 dígitos numéricos
        if (cpf.length() != 11) {
            return false;
        }

        // Calcular os dígitos verificadores
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int primeiroDigitoVerificador = 11 - (sum % 11);
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }

        sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        sum += primeiroDigitoVerificador * 2;

        int segundoDigitoVerificador = 11 - (sum % 11);
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }

        // Verificar se os dígitos verificadores são iguais aos dígitos informados
        return primeiroDigitoVerificador == Character.getNumericValue(cpf.charAt(9)) &&
                segundoDigitoVerificador == Character.getNumericValue(cpf.charAt(10));
    }
}
