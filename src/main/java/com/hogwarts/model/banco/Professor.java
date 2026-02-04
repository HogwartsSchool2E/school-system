package com.hogwarts.model.banco;

public class Professor {
//    Criando atributos
    private int id;
    private String nome;
    private String usuario;
    private String senha;
    private Disciplina disciplina;

//    Construtor
    public Professor() {}

    public Professor(int id, String nome, String usuario, String senha, Disciplina disciplina) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.disciplina = disciplina;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
