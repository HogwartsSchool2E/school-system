package com.hogwarts.utils;

public class Formatador {
    public static String mostrar(Object campo){
        return campo != null ? campo.toString() : "Não possui";
    }
}
