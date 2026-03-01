package com.hogwarts.utils;

import com.hogwarts.exceptions.RegexMatchException;

import java.util.regex.Pattern;

public class Regex {
    private static String apenasNumeros(String valor){
        if (valor == null) return "";
        return valor.replaceAll("\\D", "");
    }

    public static boolean checarCpf(String cpf){
        return Pattern.matches("^\\D{11}$", apenasNumeros(cpf));
    }

    public static boolean checarEmail(String email){
        return Pattern.matches("^[a-z]+\\.[a-z]+@hogwarts\\.com$", email);
    }

    public static boolean checarUsuario(String usuario){
        return Pattern.matches("^[a-z]+\\.[a-z]+$", usuario);
    }

    public static String formatarCpf(String cpf){
        cpf = apenasNumeros(cpf);
        if (cpf.length() == 11){
            return cpf.substring(0,3) + "." +
                    cpf.substring(3,6) + "." +
                    cpf.substring(6,9) + "-" +
                    cpf.substring(9,11);
        } else throw new RegexMatchException("Digite um CPF válido");
    }
}
