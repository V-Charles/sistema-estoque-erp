package br.com.vinicius.estoque.model;

import java.time.LocalDate;

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dataCadastro;
    private Double precoCusto;
    private Double precoVenda;
    private Integer quantidadeTotalEstoque;
    private Integer quantidadeMinimo;
    private boolean ativo;

    private Categoria categoria;
    private Fornecedor fornecedor;

    public Produto () { }

    public Produto(Integer id, String nome, String descricao, LocalDate dataCadastro, Double precoCusto, Double precoVenda, Integer quantidadeTotalEstoque, Integer quantidadeMinimo, boolean ativo, Categoria categoria, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeTotalEstoque = quantidadeTotalEstoque;
        this.quantidadeMinimo = quantidadeMinimo;
        this.ativo = ativo;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getQuantidadeTotalEstoque() {
        return quantidadeTotalEstoque;
    }

    public void setQuantidadeTotalEstoque(Integer quantidadeTotalEstoque) {
        this.quantidadeTotalEstoque = quantidadeTotalEstoque;
    }

    public Integer getQuantidadeMinimo() {
        return quantidadeMinimo;
    }

    public void setQuantidadeMinimo(Integer quantidadeMinimo) {
        this.quantidadeMinimo = quantidadeMinimo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Produto: " +nome+ " (Estoque: " +quantidadeTotalEstoque+ ")";
    }
}
