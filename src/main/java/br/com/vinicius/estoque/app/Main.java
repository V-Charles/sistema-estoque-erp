package br.com.vinicius.estoque.app;

import br.com.vinicius.estoque.model.*;
import br.com.vinicius.estoque.service.EstoqueService;
import br.com.vinicius.estoque.service.ProdutoService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Iniciando Sistema de Estoque (teste) ---");

        ProdutoService produtoService = new ProdutoService();
        EstoqueService estoqueService = new EstoqueService();

        Usuario usuario = new Usuario(1, "Vinicius", "vinicius.admin", "123", true, Perfil.GERENTE);
        Categoria categoria = new Categoria(1, "Eletrônicos", true);
        Fornecedor fornecedor = new Fornecedor(1, "Tech Distribuidora", "00.000.000/0001-99", "contato@tech", "11999999999", true);

        Produto mouse = new Produto(
                1,
                "Mouse Gamer",
                "Mouse RGB 12000 DPI",
                LocalDate.now(),
                50.00,
                120.00,
                0,
                5,
                true,
                categoria,
                fornecedor
        );

        try {
            produtoService.cadastrar(mouse);
            System.out.println("Sucesso: " + mouse);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }

        System.out.println("\n--- Teste 1: Entrada de Estoque ---");
        Movimentacao entrada = new Movimentacao(
                1,
                LocalDateTime.now(),
                100,
                0.0,
                "Compra de lote inicial",
                TipoMovimentacao.ENTRADA,
                usuario,
                mouse
        );

        estoqueService.realizarMovimentacao(entrada);
        System.out.println("Estoque atual do Mouse: " + mouse.getQuantidadeTotalEstoque());

        System.out.println("\n--- Teste 2: Venda (saída) ---");
        Movimentacao venda = new Movimentacao(
                2,
                LocalDateTime.now(),
                5,
                0.0,
                "Venda para cliente balcão",
                TipoMovimentacao.SAIDA,
                usuario,
                mouse
        );

        estoqueService.realizarMovimentacao(venda);
        System.out.println("Estoque após venda: " + mouse.getQuantidadeTotalEstoque());

        System.out.println("\n--- Teste 3: Erro de estoque insuficiente ---");
        try {
            Movimentacao vendaInvalida = new Movimentacao(
                    3,
                    LocalDateTime.now(),
                    200,
                    0.0,
                    "Tentativa de venda grande",
                    TipoMovimentacao.SAIDA,
                    usuario,
                    mouse
            );
            estoqueService.realizarMovimentacao(vendaInvalida);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\n--- Fim dos testes ---");
    }
}
