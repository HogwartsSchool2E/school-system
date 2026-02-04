package com.hogwarts.model;

import com.hogwarts.model.banco.Aluno;
import com.hogwarts.model.banco.Disciplina;
import com.hogwarts.model.banco.Observacao;
import com.hogwarts.model.enums.Situacao;

public class Boletim {
//    Criando atributos
    private Aluno aluno;
    private double nota1;
    private double nota2;
    private double media;
    private Observacao observacao;
    private Situacao situacao;
    private Disciplina disciplina;

//    Construtor
    public Boletim(Aluno aluno, double nota1, double nota2, Observacao observacao, Disciplina disciplina) {
        this.aluno = aluno;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.media = (nota1 + nota2) / 2;
        this.observacao = observacao;
        this.disciplina = disciplina;

        if (this.media <= 5) this.situacao = Situacao.REPROVADO;
        else if (this.media < 7) this.situacao = Situacao.RECUPERACAO;
        else this.situacao = Situacao.APROVADO;
    }

//    Getters e Setters
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public Observacao getObservacao() {
        return observacao;
    }

    public void setObservacao(Observacao observacao) {
        this.observacao = observacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
