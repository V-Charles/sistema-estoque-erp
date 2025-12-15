package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Movimentacao;
import br.com.vinicius.estoque.model.Produto;
import br.com.vinicius.estoque.model.TipoMovimentacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EstoqueServiceTest {

    @Test
    public void naoDevePermitirSaidaMaiorQueEstoque(){
        Produto produto = new Produto();
        produto.setNome("Teclado");
        produto.setQuantidadeTotalEstoque(10);
        produto.setPrecoVenda(50.0);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProduto(produto);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao.setQuantidade(15);

        EstoqueService service = new EstoqueService();

        IllegalArgumentException erro = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.realizarMovimentacao(movimentacao);
        });

        Assertions.assertEquals("Estoque insuficiente para o produto: Teclado", erro.getMessage());
    }

    @Test
    public void deveAtualizarSaldoNaEntrada(){
        Produto produto = new Produto();
        produto.setQuantidadeTotalEstoque(0);
        produto.setPrecoCusto(10.0);

        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        mov.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
        mov.setQuantidade(50);

        EstoqueService service = new EstoqueService();
        service.realizarMovimentacao(mov);

        Assertions.assertEquals(50, produto.getQuantidadeTotalEstoque());
    }
}
