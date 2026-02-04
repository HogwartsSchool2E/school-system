package com.hogwarts.model.banco;

public class CasaHogwarts {
//    Criando atributos
    private int id;
    private String nome;
    private int pontuacao;
    private Professor professor;

//    Construtor
    public CasaHogwarts() {}

    public CasaHogwarts(int id, String nome, int pontuacao, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.professor = professor;
    }

//    Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
