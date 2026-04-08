package br.com.vinicius.estoque.controller;

import br.com.vinicius.estoque.model.Fornecedor;
import br.com.vinicius.estoque.model.Movimentacao;
import br.com.vinicius.estoque.model.Produto;
import br.com.vinicius.estoque.model.StatusFornecedor;
import br.com.vinicius.estoque.repository.MovimentacaoRepository;
import br.com.vinicius.estoque.service.FornecedorService;
import br.com.vinicius.estoque.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    private final ProdutoService produtoService;
    private final FornecedorService fornecedorService;
    private final MovimentacaoRepository movimentacaoRepository;

    public DashboardController(ProdutoService produtoService, FornecedorService fornecedorService, MovimentacaoRepository movimentacaoRepository){
        this.produtoService = produtoService;
        this.fornecedorService = fornecedorService;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @GetMapping({"/", "/login", "/index"})
    public String paginaLogin(){
        return "index";
    }

    @GetMapping("/recuperar-senha")
    public String paginaRecuperarSenha(){
        return "recuperarSenha";
    }

    @GetMapping("/dashboard")
    public String paginaDashboard(Model model){

        List<Produto> todosProdutos = produtoService.listarTodos();
        List<Fornecedor> todosFornecedores = fornecedorService.listarTodos();

        int totalProdutos = todosProdutos.size();
        int totalFornecedores = todosFornecedores.size();

        double valorTotalEstoque = todosProdutos.stream()
                .filter(p -> p.getQuantidadeTotalEstoque() != null && p.getPrecoCusto() != null)
                .mapToDouble(p -> p.getQuantidadeTotalEstoque() * p.getPrecoCusto())
                .sum();

        List<Movimentacao> todasMovimentacoes = movimentacaoRepository.findAll();
        List<Movimentacao> ultimasMovimentacoes = todasMovimentacoes.stream()
                .sorted((m1, m2) -> m2.getId().compareTo(m1.getId()))
                .limit(5)
                .collect(Collectors.toList());

        List<Produto> produtosBaixoEstoque = todosProdutos.stream()
                .filter(p -> p.getQuantidadeTotalEstoque() != null && p.getQuantidadeMinimo() != null)
                .filter(p -> p.getQuantidadeTotalEstoque() <= p.getQuantidadeMinimo())
                .collect(Collectors.toList());

        List<Fornecedor> fornecedoresInativos = todosFornecedores.stream()
                .filter(f -> f.getStatus() == null || f.getStatus() != StatusFornecedor.ATIVO)
                .collect(Collectors.toList());

        model.addAttribute("totalProdutos", totalProdutos);
        model.addAttribute("totalFornecedores", totalFornecedores);
        model.addAttribute("valorTotalEstoque", valorTotalEstoque);
        model.addAttribute("ultimasMovimentacoes", ultimasMovimentacoes);
        model.addAttribute("produtosBaixoEstoque", produtosBaixoEstoque);
        model.addAttribute("fornecedoresInativos", fornecedoresInativos);

        return "dashboard";
    }
}
