package br.com.vinicius.estoque.model;

public enum Categoria {
    PERIFERICOS("Periféricos"),
    HARDWARE("Componentes de Hardware"),
    MONITORES("Telas e Monitores"),
    REDES("Cabeamento e Redes"),
    ACESSORIOS("Acessórios Diversos");

    private String descricao;

    Categoria(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}