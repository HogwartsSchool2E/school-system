package com.hogwarts.model.banco;

public class Usuario {

    private String login; // email ou usuário
    private String senha; // usado apenas no login
    private String tipo;  // ALUNO ou PROFESSOR

    public Usuario() {
    }

    public Usuario(String login, String senha, String tipo) {
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}