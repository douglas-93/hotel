package com.dolts.controledehotel.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

    public String formatarCPF(String cpf) {
        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
        Matcher matcher = pattern.matcher(cpf);
        if (matcher.matches()) {
            return matcher.replaceFirst("$1.$2.$3-$4");
        }

        return cpf;
    }

    public String formatarCelular(String celular) {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{5})(\\d{4})");
        Matcher matcher = pattern.matcher(celular);
        if (matcher.matches()) {
            return matcher.replaceFirst("($1) $2-$3");
        }

        return celular;
    }

    public String formatarTelefone(String telefone) {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{4})(\\d{4})");
        Matcher matcher = pattern.matcher(telefone);
        if (matcher.matches()) {
            return matcher.replaceFirst("($1) $2-$3");
        }

        return telefone;
    }
}
