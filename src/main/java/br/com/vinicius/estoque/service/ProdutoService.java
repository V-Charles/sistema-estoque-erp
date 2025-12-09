package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Produto;

public class ProdutoService {

    public void cadastrar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }

        if (produto.getPrecoCusto() == null || produto.getPrecoCusto() < 0) {
            throw new IllegalArgumentException("Preço de Custo inválido ou não informado.");
        }

        if (produto.getPrecoVenda() == null || produto.getPrecoVenda() < 0) {
            throw new IllegalArgumentException("Preço de Venda inválido ou não informado.");
        }

        System.out.println("Produto validado e cadastrado: " + produto.getNome());
    }

    public Double calcularPrecoVenda(Double custo, Double margemLucroPercentagem) {
        if (custo == null) return 0.0;
        double valorMargem = custo * (margemLucroPercentagem / 100);
        return custo + valorMargem;
    }
}