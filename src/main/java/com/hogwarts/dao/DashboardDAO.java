package com.hogwarts.dao;

import com.hogwarts.model.Dashboard;
import com.hogwarts.model.QuadroObservacoes;
import com.hogwarts.utils.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardDAO {
    public Dashboard gerarDashboard(){
        return new Dashboard(
                gerarRanking(),
                gerarQtdAlunos(),
                gerarMediaCasas(),
                gerarQuadroObservacoes()
        );
    }

    private HashMap<String, Double> gerarRanking(){
//        Criando atributos
        String sql = "SELECT * FROM ranking;";
        HashMap<String, Double> ranking = new HashMap<>();

//        Realizando busca no banco de dados
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ){
//            Inserindo os valores nos objetos
            while (rs.next()){
                String aluno = rs.getString("aluno");
                Double media = rs.getDouble("media");

                ranking.put(aluno, media);
            } return ranking;
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } return null;
    }

    private int gerarQtdAlunos(){
//        Criando atributos
        String sql = "SELECT COUNT(*) FROM aluno";

//        Realizando busca no banco de dados
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ){
//            Inserindo os valores nos objetos
            if (rs.next()) return rs.getInt("count");
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } return 0;
    }

    private HashMap<String, Double> gerarMediaCasas(){
//        Criando atributos
        String sql = "SELECT * FROM media_casas;";
        HashMap<String, Double> mediaCasas = new HashMap<>();

//        Realizando busca no banco de dados
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ){
//            Inserindo os valores nos objetos
            while (rs.next()){
                String casaHogwarts = rs.getString("casa_hogwarts");
                Double media = rs.getDouble("media_casas");

                mediaCasas.put(casaHogwarts, media);
            } return mediaCasas;
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } return null;
    }

    private List<QuadroObservacoes> gerarQuadroObservacoes(){
//        Criando atributos
        String sql = "SELECT * FROM quadro_observacoes;";
        List<QuadroObservacoes> quadroObservacoesList = new ArrayList<>();

//        Realizando busca no banco de dados
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ){
//            Inserindo os valores nos objetos
            while (rs.next()){
                quadroObservacoesList.add(new QuadroObservacoes (
                        rs.getString("aluno"),
                        rs.getString("casa"),
                        rs.getString("professor"),
                        rs.getString("observacao")
                ));
            } return quadroObservacoesList;
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } return null;
    }
}
