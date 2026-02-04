package com.hogwarts.dao;

import com.hogwarts.model.banco.Observacao;
import com.hogwarts.utils.Conexao;

import java.sql.*;

public class ObservacaoDAO {
    public boolean inserirObs(Observacao obs) throws SQLException, ClassNotFoundException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        int retorno = 0;
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO OBSERVACAO(OBSERVACAO, COD_ALUNO, COD_DISCIPLINA) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obs.getObservacao());
            pstmt.setInt(2, obs.getAluno().getMatricula());
            pstmt.setInt(3, obs.getDisciplina().getId());
            retorno = pstmt.executeUpdate();
            if(retorno > 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obs.setId(id);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            conexao.desconectar(conn);
        }
        return retorno > 0;
    }
}
