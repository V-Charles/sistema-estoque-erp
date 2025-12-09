package br.com.vinicius.estoque.model;

public enum TipoMovimentacao {

    ENTRADA("Entrada"),
    SAIDA("Saída"),
    REPOSICAO("Reposição");

    private final String descricao;

    TipoMovimentacao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
