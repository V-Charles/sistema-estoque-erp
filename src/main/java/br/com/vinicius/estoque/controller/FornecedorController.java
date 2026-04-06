package br.com.vinicius.estoque.controller;

import br.com.vinicius.estoque.model.Fornecedor;
import br.com.vinicius.estoque.service.FornecedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService){
        this.fornecedorService = fornecedorService;
    }

    @GetMapping("/fornecedores")
    public String paginaFornecedores(){
        return "fornecedores";
    }

    @GetMapping("/api/fornecedores")
    @ResponseBody
    public List<Fornecedor> listarFornecedoresApi(){
        return fornecedorService.listarTodos();
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
    public String paginaVisualizarFornecedor(@RequestParam("id") Integer id, Model model){
        br.com.vinicius.estoque.model.Fornecedor fornecedor = fornecedorService.buscarPorId(id);
        model.addAttribute("fornecedor", fornecedor);
        return "visualizacaoFornecedor";
    }

    @PostMapping("/fornecedores/atualizar")
    public String atualizarFornecedor(br.com.vinicius.estoque.model.Fornecedor fornecedor){
        fornecedorService.atualizar(fornecedor);
        return "redirect:/fornecedores";
    }
}
