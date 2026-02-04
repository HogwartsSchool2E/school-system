package com.hogwarts.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
//    Criando atributos
    private static final Dotenv dotenv = Dotenv.load();
    private static final String dbUser = dotenv.get("USER");
    private static final String dbPassword = dotenv.get("PASSWORD");
    private static final String dbUrl = dotenv.get("URL");

    public static Connection conectar() throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
