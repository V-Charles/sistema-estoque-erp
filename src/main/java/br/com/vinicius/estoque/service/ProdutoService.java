package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Produto;

import java.time.LocalDate;

public class ProdutoService {

    public void cadastrar(Produto produto) {
        validarDadosObrigatorios(produto);
        validarPrecos(produto);

        if (produto.getDataCadastro() == null) {
            produto.setDataCadastro(LocalDate.now());
        }

        System.out.println("Produto cadastrado com sucesso: " + produto.getNome());
        System.out.println("Fornecedor vinculado: " + (produto.getFornecedor() != null ? produto.getFornecedor().getRazaoSocial() : "Nenhum"));
    }

    public void atualizar(Produto produto) {
        if (produto.getId() == null) {
            throw new IllegalArgumentException("ID do produto é obrigatório para atualização.");
        }

        validarDadosObrigatorios(produto);
        validarPrecos(produto);

        System.out.println("Produto atualizado com sucesso: " + produto.getNome());
    }

    public Double calcularPrecoVenda(Double custo, Double margemLucroPercentagem) {
        if (custo == null) return 0.0;
        double valorMargem = custo * (margemLucroPercentagem / 100);
        return custo + valorMargem;
    }

    private void validarDadosObrigatorios(Produto p) {
        if (p.getNome() == null || p.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }

        if (p.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria do produto é obrigatória.");
        }

        if (p.getFornecedor() == null) {
            throw new IllegalArgumentException("Fornecedor do produto é obrigatório.");
        }
    }

    private void validarPrecos(Produto p) {
        if (p.getPrecoCusto() == null || p.getPrecoCusto() < 0) {
            throw new IllegalArgumentException("Preço de custo inválido.");
        }

        if (p.getPrecoVenda() == null || p.getPrecoVenda() < 0) {
            throw new IllegalArgumentException("Preço de venda inválido.");
        }
    }
}