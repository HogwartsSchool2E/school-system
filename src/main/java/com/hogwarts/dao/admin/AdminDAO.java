package com.hogwarts.dao.admin;

import com.hogwarts.utils.Conexao;
import com.hogwarts.utils.Hash;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public String ehAdmin(String email, String senha) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        String sql = "SELECT NOME, SENHA FROM ADMINISTRADOR WHERE EMAIL = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                String senhaBanco = rs.getString("senha");
                String nome = rs.getString("nome");
                String hashSenha = Hash.hashSenha(senha);

                if (hashSenha.equals(senhaBanco)) return nome;
            } return null;
        }
    }
}
