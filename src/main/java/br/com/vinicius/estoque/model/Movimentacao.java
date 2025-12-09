package br.com.vinicius.estoque.model;

import java.time.LocalDateTime;

public class Movimentacao {

    private Integer id;
    private LocalDateTime dataHora;
    private Integer quantidade;
    private Double valorUnitario;
    private String observacao;

    private TipoMovimentacao tipoMovimentacao;
    private Usuario usuario;
    private Produto produto;

    public Movimentacao () { }

    public Movimentacao(Integer id, LocalDateTime dataHora, Integer quantidade, Double valorUnitario, String observacao, TipoMovimentacao tipoMovimentacao, Usuario usuario, Produto produto) {
        this.id = id;
        this.dataHora = dataHora;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.observacao = observacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String toString () {
        return "Movimentação [" +dataHora+ "]: " +
                tipoMovimentacao + " de " + quantidade + "x " +
                produto.getNome() + " (Por: " + usuario.getNome() + ")";
    }
}
