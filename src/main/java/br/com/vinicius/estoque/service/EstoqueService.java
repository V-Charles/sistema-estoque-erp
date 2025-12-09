package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Movimentacao;
import br.com.vinicius.estoque.model.Produto;
import br.com.vinicius.estoque.model.TipoMovimentacao;

public class EstoqueService {

    public void realizarMovimentacao(Movimentacao movimentacao) {
        Produto produto = movimentacao.getProduto();

        if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            movimentacao.setValorUnitario(produto.getPrecoCusto());
        } else {
            movimentacao.setValorUnitario(produto.getPrecoVenda());
        }

        if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
            boolean estoqueInsuficiente = verificarEstoqueInsuficiente(produto, movimentacao.getQuantidade());
            if (estoqueInsuficiente) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }
        }

        atualizarSaldoProduto(movimentacao);

        System.out.println("Movimentação realizada com sucesso! Novo saldo: " + produto.getQuantidadeTotalEstoque());
    }

    private boolean verificarEstoqueInsuficiente(Produto produto, Integer quantidadeSaida) {
        int saldoAtual = produto.getQuantidadeTotalEstoque() != null ? produto.getQuantidadeTotalEstoque() : 0;
        return saldoAtual < quantidadeSaida;
    }

    private void atualizarSaldoProduto(Movimentacao movimentacao) {
        Produto produto = movimentacao.getProduto();

        int saldoAtual = produto.getQuantidadeTotalEstoque() != null ? produto.getQuantidadeTotalEstoque() : 0;
        int quantidadeMovimentada = movimentacao.getQuantidade();

        if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            produto.setQuantidadeTotalEstoque(saldoAtual + quantidadeMovimentada);

        } else if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
            produto.setQuantidadeTotalEstoque(saldoAtual - quantidadeMovimentada);

        } else if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.AJUSTE) {
            produto.setQuantidadeTotalEstoque(saldoAtual + quantidadeMovimentada);
        }
    }
}