package br.com.vinicius.estoque.model;

public enum TipoMovimentacao {

    ENTRADA("Entrada"),
    SAIDA("Saída"),
    AJUSTE("Ajuste");

    private final String descricao;

    TipoMovimentacao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
