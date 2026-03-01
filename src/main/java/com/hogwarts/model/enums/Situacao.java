package com.hogwarts.model.enums;

public enum Situacao {
    APROVADO("Aprovado"),
    RECUPERACAO("Recuperação"),
    REPROVADO("Reprovado"),
    EM_ANDAMENTO("Em Andamento");

    private final String nome;

    Situacao(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }
}
