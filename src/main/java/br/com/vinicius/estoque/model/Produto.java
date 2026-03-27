package br.com.vinicius.estoque.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @NumberFormat(pattern = "#,##0.00")
    @Column(nullable = false)
    private Double precoCusto;

    @NumberFormat(pattern = "#,##0.00")
    @Column(nullable = false)
    private Double precoVenda;

    @Column(nullable = false)
    private Integer quantidadeTotalEstoque;

    @Column(nullable = false)
    private Integer quantidadeMinimo;

    @Column(nullable = false)
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
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
