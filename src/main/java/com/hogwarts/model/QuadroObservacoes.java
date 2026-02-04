package com.hogwarts.model;

public class QuadroObservacoes {
//    Criando atributos
    private String aluno;
    private String casa;
    private String professor;
    private String observacao;

//    Construtores
    public QuadroObservacoes(){}

    public QuadroObservacoes(String aluno, String casa, String professor, String observacao) {
        this.aluno = aluno;
        this.casa = casa;
        this.professor = professor;
        this.observacao = observacao;
    }

//    Getters e Setters
    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
