package com.hogwarts.model;

import java.util.HashMap;
import java.util.List;

public class Dashboard {
//    Criando atributos
    private HashMap<String, Double> ranking;
    private int qtdAlunos;
    private HashMap<String, Double> mediaCasas;
    private List<QuadroObservacoes> quadroObservacoes;

//    Construtor
    public Dashboard(){}

    public Dashboard(HashMap<String, Double> ranking, int qtdAlunos, HashMap<String, Double> mediaCasas, List<QuadroObservacoes> quadroObservacoes) {
        this.ranking = ranking;
        this.qtdAlunos = qtdAlunos;
        this.mediaCasas = mediaCasas;
        this.quadroObservacoes = quadroObservacoes;
    }

    //    Getters e Setters
    public HashMap<String, Double> getRanking() {
        return ranking;
    }

    public void setRanking(HashMap<String, Double> ranking) {
        this.ranking = ranking;
    }

    public int getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(int qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }

    public HashMap<String, Double> getMediaCasas() {
        return mediaCasas;
    }

    public void setMediaCasas(HashMap<String, Double> mediaCasas) {
        this.mediaCasas = mediaCasas;
    }

    public List<QuadroObservacoes> getQuadroObservacoes() {
        return quadroObservacoes;
    }

    public void setQuadroObservacoes(List<QuadroObservacoes> quadroObservacoes) {
        this.quadroObservacoes = quadroObservacoes;
    }
}
