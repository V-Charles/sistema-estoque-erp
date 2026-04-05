package br.com.vinicius.estoque.controller;

import br.com.vinicius.estoque.model.Produto;
import br.com.vinicius.estoque.service.FornecedorService;
import br.com.vinicius.estoque.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProdutoController {

    private final ProdutoService produtoService;
    private final FornecedorService fornecedorService;

    public ProdutoController(ProdutoService produtoService, FornecedorService fornecedorService){
        this.produtoService = produtoService;
        this.fornecedorService = fornecedorService;
    }

    @GetMapping("/produtos")
    public String paginaProdutos(Model model){
        model.addAttribute("listaCategorias", br.com.vinicius.estoque.model.Categoria.values());
        return "produtos";
    }

    @GetMapping("/api/produtos")
    @ResponseBody
    public List<Produto> listarProdutosApi(){
        return produtoService.listarTodos();
    }

    @GetMapping("/cadastro-produtos")
    public String paginaCadastroProdutos(Model model){
        model.addAttribute("produto", new br.com.vinicius.estoque.model.Produto());
        model.addAttribute("listaCategorias", br.com.vinicius.estoque.model.Categoria.values());
        model.addAttribute("listaFornecedores", fornecedorService.listarTodos());
        return "cadastroProdutos";
    }

    @PostMapping("/produtos/salvar")
    public String salvarProduto(Produto produto){
        produtoService.cadastrar(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/visualizar-produto")
    public String paginaVisualizarProduto(@RequestParam("id") Integer id, Model model){
        Produto produto = produtoService.buscarPorId(id);

        model.addAttribute("produto", produto);
        model.addAttribute("listaCategorias", br.com.vinicius.estoque.model.Categoria.values());
        model.addAttribute("listaFornecedores", fornecedorService.listarTodos());
        return "visualizacaoProduto";
    }

    @PostMapping("/produtos/atualizar")
    public String atualizarProduto(Produto produto){
        produtoService.cadastrar(produto);
        return "redirect:/produtos";
    }
}
