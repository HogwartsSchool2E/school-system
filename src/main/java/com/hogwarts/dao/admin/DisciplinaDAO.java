package com.hogwarts.dao.admin;

import com.hogwarts.model.banco.Disciplina;
import com.hogwarts.model.banco.Professor;
import com.hogwarts.utils.Conexao;
import com.hogwarts.utils.Hash;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
//    Método de inserir disciplina
    public boolean inserirDisciplina(Disciplina disciplina) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO disciplina (NOME, COD_PROFESSOR) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, disciplina.getNome());
            pstmt.setInt(2, disciplina.getProfessor().getId());

            return pstmt.executeUpdate() > 0;
        }
    }

//    Método de atualizar disciplina do professor
    public boolean atualizarDisciplina(int idDisciplina, int idProfNovo) throws SQLException, ClassNotFoundException{
        String sql = """
                DELETE FROM observacao WHERE cod_disciplina = ?;
                UPDATE disciplina SET cod_professor = ? WHERE id = ?;
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setInt(1, idDisciplina);
            pstmt.setInt(2, idProfNovo);
            pstmt.setInt(3, idDisciplina);

            return pstmt.executeUpdate() > 0;
        }
    }

//    Método de inserir professor
    public boolean inserirProfessor(Professor professor) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        String sql = "INSERT INTO professor (NOME, USUARIO, SENHA) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setString(1, professor.getNome());
            pstmt.setString(2, professor.getUsuario());
            pstmt.setString(3, Hash.hashSenha(professor.getSenha()));

            return pstmt.executeUpdate() > 0;
        }
    }

//    Método de visualizar professores
    public List<Disciplina> buscarProfessores() throws SQLException, ClassNotFoundException {
        List<Disciplina> professores = new ArrayList<>();
        String sql = """
                     SELECT p.ID, p.NOME, p.USUARIO, d.id as "cod_disciplina", d.NOME as "DISCIPLINA"
                     FROM professor p
                     LEFT JOIN disciplina d ON d.cod_professor = p.id
                     """;

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ){
            while (rs.next()){
                Professor p = new Professor();
                Disciplina d = new Disciplina();

                d.setId(rs.getInt("cod_disciplina"));
                d.setNome(rs.getString("disciplina"));

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setUsuario(rs.getString("usuario"));
                d.setProfessor(p);

                professores.add(d);
            } return professores;
        }
    }

//    Método de visualizar um professor
    public Disciplina buscarProfessor(int id) throws SQLException, ClassNotFoundException{
        String sql = """
                     SELECT p.ID, p.NOME, p.USUARIO, d.id as "cod_disciplina", d.NOME as "DISCIPLINA"
                     FROM professor p
                     LEFT JOIN disciplina d ON d.cod_professor = p.id
                     WHERE p.ID = ?;
                     """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                Professor p = new Professor();
                Disciplina d = new Disciplina();

                d.setId(rs.getInt("cod_disciplina"));
                d.setNome(rs.getString("disciplina"));

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setUsuario(rs.getString("usuario"));
                d.setProfessor(p);

                return d;
            }
        } return null;
    }

//    Método de excluir professores
    public boolean excluirProfessor(int idProfessor) throws SQLException, ClassNotFoundException{
        String sql = """
                      UPDATE disciplina
                      SET cod_professor = NULL
                      WHERE cod_professor = ?;
                      
                      UPDATE casa_hogwarts
                      SET cod_professor = NULL
                      WHERE cod_professor = ?;
                      
                      DELETE FROM observacao
                      WHERE cod_disciplina = (SELECT id FROM disciplina WHERE cod_professor = ?);
                      
                      DELETE FROM professor
                      WHERE id = ?
                      """;
        CasaDAO ch = new CasaDAO();

        try (Connection conn = Conexao.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, idProfessor);
            pstmt.setInt(2, idProfessor);
            pstmt.setInt(3, idProfessor);
            pstmt.setInt(4, idProfessor);

            return pstmt.executeUpdate() > 0;
        }
    }

}
