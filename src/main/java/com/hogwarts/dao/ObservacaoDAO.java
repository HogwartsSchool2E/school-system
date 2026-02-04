package com.hogwarts.dao;

import com.hogwarts.model.banco.Observacao;
import com.hogwarts.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObservacaoDAO {

    // Método inserir
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

    // Método alterar
    public boolean alterarObs(int id, String obs) throws SQLException, ClassNotFoundException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        int retorno = 0;
        try{
            PreparedStatement pstmt = conn.prepareStatement("UPDATE OBSERVACAO SET OBSERVACAO = ? WHERE ID = ?");
            pstmt.setString(1, obs);
            pstmt.setInt(2, id);
            retorno = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            conexao.desconectar(conn);
        }
        return retorno > 0;
    }

    // Método deletar
    public boolean deletarObs(String aluno, String disciplina) throws SQLException, ClassNotFoundException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        int retorno = 0;
        try{
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM observacao" +
                    "WHERE ID IN (" +
                    "    SELECT o.ID" +
                    "    FROM OBSERVACAO o" +
                    "    JOIN ALUNO a ON o.COD_ALUNO = a.MATRICULA" +
                    "    JOIN DISCIPLINA d ON o.COD_DISCIPLINA = d.ID" +
                    "    WHERE a.NOME = ?" +
                    "    AND d.NOME = ? )");
            pstmt.setString(1, aluno);
            pstmt.setString(2, disciplina);
            retorno = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            conexao.desconectar(conn);
        }
        return retorno > 0;
    }

    // Método buscar obs por aluno
    public List<String[]> buscarPorAluno(String aluno) throws SQLException, ClassNotFoundException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        List<String[]> lista = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement("SELECT a.NOME, o.OBSERVACAO FROM OBSERVACAO o " +
                "JOIN ALUNO a ON o.COD_ALUNO = a.MATRICULA " +
                "WHERE a.NOME = ?")) {
            pstmt.setString(1, aluno);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                String[] linha = new String[2];
                linha[0] = rs.getString("NOME");
                linha[1] = rs.getString("OBSERVACAO");

                lista.add(linha);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(conn);
        }

        return lista;

    }

    // Método buscar obs por aluno e disciplina
    public List<String[]> buscarPorAlunoDisciplina(String aluno, String disciplina) throws SQLException, ClassNotFoundException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        List<String[]> lista = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement("SELECT a.NOME AS Aluno, d.NOME AS Disciplina, o.OBSERVACAO FROM OBSERVACAO o " +
                "JOIN ALUNO a ON o.COD_ALUNO = a.MATRICULA " +
                "JOIN DISCIPLINA d ON o.COD_DISCIPLINA = d.ID " +
                "WHERE a.NOME = ? AND d.NOME = ?")) {
            pstmt.setString(1, aluno);
            pstmt.setString(2, disciplina);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                String[] linha = new String[3];
                linha[0] = rs.getString("Aluno");
                linha[1] = rs.getString("Disciplina");
                linha[2] = rs.getString("OBSERVACAO");

                lista.add(linha);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(conn);
        }

        return lista;

    }
}
