package com.hogwarts.dao;

import com.hogwarts.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO {

    // Método de login
    public boolean login(String usuario, String senha) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        boolean retorno = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT 1 FROM PROFESSOR WHERE USUARIO = ? AND SENHA = ?");
            pstmt.setString(1, usuario);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                retorno = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            conexao.desconectar(conn);
        }
        return retorno;
    }
}
