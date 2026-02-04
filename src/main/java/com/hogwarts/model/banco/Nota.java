package com.hogwarts.model.banco;

public class Nota {
//    Criando atributos
    private int id;
    private double notaUm;
    private double notaDois;
    private Aluno aluno;
    private Disciplina disciplina;

//    Construtor
    public Nota() {}

    public Nota(int id, double notaUm, double notaDois, Aluno aluno, Disciplina disciplina) {
        this.id = id;
        this.notaUm = notaUm;
        this.notaDois = notaDois;
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

    public double getNotaUm() {
        return notaUm;
    }

    public void setNotaUm(double notaUm) {
        this.notaUm = notaUm;
    }

    public double getNotaDois() {
        return notaDois;
    }

    public void setNotaDois(double notaDois) {
        this.notaDois = notaDois;
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
