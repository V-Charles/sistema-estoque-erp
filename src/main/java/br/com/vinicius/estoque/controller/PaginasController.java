package br.com.vinicius.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {

    @GetMapping({"/", "/login", "/index"})
    public String paginaLogin(){
        return "index";
    }

    @GetMapping("/recuperar-senha")
    public String paginaRecuperarSenha(){
        return "recuperarSenha";
    }

    @GetMapping("/dashboard")
    public String paginaDashboard(){
        return "dashboard";
    }

    @GetMapping("/produtos")
    public String paginaProdutos(){
        return "produtos";
    }

    @GetMapping("/cadastro-produtos")
    public String paginaCadastroProdutos(){
        return "cadastroProdutos";
    }

    @GetMapping("/visualizar-produto")
    public String paginaVisualizarProduto(){
        return "visualizacaoProduto";
    }

    @GetMapping("/fornecedores")
    public String paginaFornecedores(){
        return "fornecedores";
    }

    @GetMapping("/cadastro-fornecedores")
    public String paginaCadastroFornecedores(){
        return "cadastroFornecedores";
    }

    @GetMapping("/visualizar-fornecedor")
    public String paginaVisualizarFornecedor(){
        return "visualizacaoFornecedor";
    }

    @GetMapping("/movimentacoes")
    public String paginaMovimentacoes(){
        return "movimentacoes";
    }

    @GetMapping("/registra-movimentacao")
    public String paginaRegistraMovimentacao(){
        return "registraMovimentacao";
    }

    @GetMapping("/visualizar-movimentacao")
    public String paginaVisualizarMovimentacao(){
        return "visualizacaoMovimentacao";
    }
}
