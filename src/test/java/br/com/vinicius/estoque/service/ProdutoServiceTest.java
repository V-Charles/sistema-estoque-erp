package br.com.vinicius.estoque.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProdutoServiceTest {

    @Test
    public void calcularPrecoVendaCorretamente(){
        ProdutoService service = new ProdutoService();
        Double custo = 100.00;
        Double margemLucro = 20.0;

        Double precoVendaCalculado = service.calcularPrecoVenda(custo, margemLucro);

        Assertions.assertEquals(120.00, precoVendaCalculado);
    }

    @Test
    public void retornarZeroSeCustoForNulo(){
        ProdutoService service = new ProdutoService();
        Double preco = service.calcularPrecoVenda(null, 20.0);
        Assertions.assertEquals(0.0, preco);
    }
}