package com.hogwarts.dao;

import com.hogwarts.model.banco.Nota;
import com.hogwarts.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotaDAO {
    public boolean inserirNota(double notaValor, Nota infoNota, boolean ehNotaUm) throws SQLException, ClassNotFoundException {
        String sql;

        if (ehNotaUm) sql = "INSERT INTO NOTA (NOTA_UM, COD_ALUNO, COD_DISCIPLINA) VALUES (?, ?, ?)";
        else sql = "UPDATE NOTA SET NOTA_DOIS = ? WHERE COD_ALUNO = ? AND COD_DISCIPLINA = ?";

        return setarValores(notaValor, infoNota, sql);
    }

    public boolean atualizarNota(double notaValor, Nota infoNota, boolean ehNotaUm) throws SQLException, ClassNotFoundException{
        String sql;

        if (ehNotaUm){
            sql = "UPDATE NOTA SET NOTA_UM = ? WHERE COD_ALUNO = ? AND COD_DISCIPLINA = ?";
            return setarValores(notaValor, infoNota, sql);
        }
        else return inserirNota(notaValor, infoNota, false);
    }

    public boolean excluirNota(int matricula, int idDisc, boolean ehNotaUm) throws SQLException, ClassNotFoundException{
        String sql;
        if (ehNotaUm) sql = "UPDATE NOTA SET NOTA_UM = 0 WHERE COD_ALUNO = ? AND COD_DISCIPLINA = ?";
        else sql = "UPDATE NOTA SET NOTA_DOIS = 0 WHERE COD_ALUNO = ? AND COD_DISCIPLINA = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, matricula);
            pstmt.setInt(2, idDisc);

            return pstmt.executeUpdate() > 0;
        }
    }

    private boolean setarValores(double notaValor, Nota infoNota, String sql) throws SQLException, ClassNotFoundException{
        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, notaValor);
            pstmt.setInt(2, infoNota.getAluno().getMatricula());
            pstmt.setInt(3, infoNota.getDisciplina().getId());

            return pstmt.executeUpdate() > 0;
        }
    }
}
