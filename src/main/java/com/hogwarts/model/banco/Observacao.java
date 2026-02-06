package com.hogwarts.model.banco;

public class Observacao {
//    Criando atributos
    private int id;
    private String observacao;
    private Aluno aluno;
    private Disciplina disciplina;

//    Construtor
    public Observacao() {}

    public Observacao(String observacao, Aluno aluno, Disciplina disciplina) {
        this.observacao = observacao;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

//    Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
