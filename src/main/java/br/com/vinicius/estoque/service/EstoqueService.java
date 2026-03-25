package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Movimentacao;
import br.com.vinicius.estoque.model.Produto;
import br.com.vinicius.estoque.model.TipoMovimentacao;
import br.com.vinicius.estoque.model.Usuario;
import br.com.vinicius.estoque.repository.MovimentacaoRepository;
import br.com.vinicius.estoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class EstoqueService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository){
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Movimentacao> listarTodas(){
        return movimentacaoRepository.findAll();
    }

    public Movimentacao realizarMovimentacao(Movimentacao movimentacao) {
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
        produtoRepository.save(produto);

        return movimentacaoRepository.save(movimentacao);
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

    @Transactional
    public Movimentacao criarMovimentacao(Produto produto, Integer quantidade, TipoMovimentacao tipo, String observacao, Usuario usuario) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser um número positivo maior que zero.");
        }

        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        mov.setQuantidade(quantidade);
        mov.setTipoMovimentacao(tipo);
        mov.setObservacao(observacao);
        mov.setUsuario(usuario);

        mov.setDataHora(java.time.LocalDateTime.now());

        return realizarMovimentacao(mov);
    }
}