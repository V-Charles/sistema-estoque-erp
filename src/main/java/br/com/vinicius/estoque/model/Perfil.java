package br.com.vinicius.estoque.model;

public enum Perfil {

    GERENTE("Gerente"),
    ESTOQUISTA("Estoquista");

    private final String descricao;

    Perfil(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String toString() {
        return descricao;
    }
}
