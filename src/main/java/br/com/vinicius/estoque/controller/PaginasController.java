package br.com.vinicius.estoque.controller;

import br.com.vinicius.estoque.service.EstoqueService;
import br.com.vinicius.estoque.service.FornecedorService;
import br.com.vinicius.estoque.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaginasController {

    private final ProdutoService produtoService;
    private final FornecedorService fornecedorService;
    private final EstoqueService estoqueService;

    public PaginasController(ProdutoService produtoService, FornecedorService fornecedorService, EstoqueService estoqueService){
        this.produtoService = produtoService;
        this.fornecedorService = fornecedorService;
        this.estoqueService = estoqueService;
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
    public String paginaDashboard(){
        return "dashboard";
    }

    @GetMapping("/produtos")
    public String paginaProdutos(Model model){
        model.addAttribute("listaProdutos", produtoService.listarTodos());
        return "produtos";
    }

    @GetMapping("/cadastro-produtos")
    public String paginaCadastroProdutos(Model model){
        model.addAttribute("produto", new br.com.vinicius.estoque.model.Produto());
        model.addAttribute("listaCategorias", br.com.vinicius.estoque.model.Categoria.values());
        model.addAttribute("listaFornecedores", fornecedorService.listarTodos());
        return "cadastroProdutos";
    }

    @PostMapping("/produtos/salvar")
    public String salvarProduto(br.com.vinicius.estoque.model.Produto produto){
        produtoService.cadastrar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/visualizar-produto")
    public String paginaVisualizarProduto(){
        return "visualizacaoProduto";
    }

    @GetMapping("/fornecedores")
    public String paginaFornecedores(Model model){
        model.addAttribute("listaFornecedores", fornecedorService.listarTodos());
        return "fornecedores";
    }

    @GetMapping("/cadastro-fornecedores")
    public String paginaCadastroFornecedores(Model model){
        model.addAttribute("fornecedor", new br.com.vinicius.estoque.model.Fornecedor());
        return "cadastroFornecedores";
    }

    @PostMapping("/fornecedores/salvar")
    public String salvarFornecedor(br.com.vinicius.estoque.model.Fornecedor fornecedor){
        fornecedorService.cadastrar(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/visualizar-fornecedor")
    public String paginaVisualizarFornecedor(){
        return "visualizacaoFornecedor";
    }

    @GetMapping("/movimentacoes")
    public String paginaMovimentacoes(Model model){
        model.addAttribute("listaMovimentacoes", estoqueService.listarTodas());
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
