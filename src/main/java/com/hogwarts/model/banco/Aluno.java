package com.hogwarts.model.banco;

public class Aluno {
//    Criando atributos
    private int matricula;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private CasaHogwarts casaHogwarts;

//    Construtor
    public Aluno() {}

    public Aluno(int matricula, String nome, String cpf, String email, String senha, CasaHogwarts casaHogwarts) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.casaHogwarts = casaHogwarts;
    }

    public Aluno(String nome, String cpf, String email, String senha, CasaHogwarts casaHogwarts) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.casaHogwarts = casaHogwarts;
    }

//    Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CasaHogwarts getCasaHogwarts() {
        return casaHogwarts;
    }

    public void setCasaHogwarts(CasaHogwarts casaHogwarts) {
        this.casaHogwarts = casaHogwarts;
    }
}
