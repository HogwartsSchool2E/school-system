package com.hogwarts.dao;

import com.hogwarts.model.*;
import com.hogwarts.model.banco.Aluno;
import com.hogwarts.model.banco.Disciplina;
import com.hogwarts.model.banco.Nota;
import com.hogwarts.model.banco.Observacao;
import com.hogwarts.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

//    Método de geração de boletim de um aluno
    public Boletim gerarBoletimIndividual(int matricula){
        // Query SQL
        String sql = """
                SELECT a.nome as "aluno", d.nome as "disciplina", n.nota_um, n.nota_dois, o.observacao
                FROM aluno a
                JOIN nota n ON n.cod_aluno = a.matricula
                JOIN disciplina d ON d.id = n.cod_disciplina
                LEFT JOIN observacao o ON o.cod_aluno = a.matricula AND o.cod_disciplina = d.id
                WHERE a.matricula = ?;
                """;

        // Realizando busca no banco de dados-
        try ( Connection conn = Conexao.conectar();
              PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // Inserindo os atributos e recebendo o SELECT
            pstmt.setInt(1, matricula);
            ResultSet rs = pstmt.executeQuery();

            // Inserindo os valores nos objetos
            if (rs.next()){
                Disciplina d = new Disciplina();
                Nota n = new Nota();
                Observacao o = new Observacao();
                Aluno a = new Aluno();

                // Capturando valores da seleção
                d.setNome(rs.getString("disciplina"));

                n.setNotaUm(rs.getDouble("nota_um"));
                n.setNotaDois(rs.getDouble("nota_dois"));

                o.setObservacao(rs.getString("observacao"));

                a.setNome(rs.getString("aluno"));

                // Geração do boletim
                return new Boletim(a, n.getNotaUm(), n.getNotaDois(), o, d);
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        } return null;
    }

//    Método de geração de boletim de todos os alunos
    public List<Boletim> gerarBoletimTodos(){
        // Criando atributos
        List<Boletim> boletins = new ArrayList<>();
        String sql = """
                    SELECT a.nome as "aluno", d.nome as "disciplina", n.nota_um, n.nota_dois, o.observacao
                    FROM aluno a
                    JOIN nota n ON n.cod_aluno = a.matricula
                    JOIN disciplina d ON d.id = n.cod_disciplina
                    LEFT JOIN observacao o ON o.cod_aluno = a.matricula AND o.cod_disciplina = d.id;
                    """;

        // Realizando busca no banco de dados
        try ( Connection conn = Conexao.conectar();
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql)
                ){

            // Inserindo os valores nos objetos
            while (rs.next()){
                Disciplina d = new Disciplina();
                Nota n = new Nota();
                Observacao o = new Observacao();
                Aluno a = new Aluno();

                // Capturando valores da seleção
                d.setNome(rs.getString("disciplina"));

                n.setNotaUm(rs.getDouble("nota_um"));
                n.setNotaDois(rs.getDouble("nota_dois"));

                o.setObservacao(rs.getString("observacao"));

                a.setNome(rs.getString("aluno"));

                // Geração do boletim
                boletins.add(new Boletim(a, n.getNotaUm(), n.getNotaDois(), o, d));
            } return boletins;
        } catch (SQLException sqle){
            sqle.printStackTrace();
        } return null;
    }
}
