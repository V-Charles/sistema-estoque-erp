package br.com.vinicius.estoque.model;

public enum StatusProduto {

    ATIVO("Ativo", "status-ativo"),
    PAUSADO("Pausado", "status-pausado"),
    DESCONTINUADO("Descontinuado", "status-descontinuado");

    private String descricao;
    private String classeCss;

    StatusProduto(String descricao, String classeCss){
        this.descricao = descricao;
        this.classeCss = classeCss;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getClasseCss(){
        return classeCss;
    }
}
