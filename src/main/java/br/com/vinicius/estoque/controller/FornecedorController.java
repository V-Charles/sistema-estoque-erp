package br.com.vinicius.estoque.controller;

import br.com.vinicius.estoque.model.Fornecedor;
import br.com.vinicius.estoque.service.FornecedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService){
        this.fornecedorService = fornecedorService;
    }

    @GetMapping("/fornecedores")
    public String paginaFornecedores(Model model){
        model.addAttribute("listaFornecedores", fornecedorService.listarTodos());
        return "fornecedores";
    }

    @GetMapping("/cadastro-fornecedores")
    public String paginaCadastroFornecedores(Model model){
        model.addAttribute("fornecedor", new Fornecedor());
        return "cadastroFornecedores";
    }

    @PostMapping("/fornecedores/salvar")
    public String salvarFornecedor(Fornecedor fornecedor){
        fornecedorService.cadastrar(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/visualizar-fornecedor")
    public String paginaVisualizarFornecedor(){
        return "visualizacaoFornecedor";
    }
}
