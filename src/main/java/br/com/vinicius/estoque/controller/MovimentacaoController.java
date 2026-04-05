package br.com.vinicius.estoque.controller;

import br.com.vinicius.estoque.service.EstoqueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovimentacaoController {

    private final EstoqueService estoqueService;

    public MovimentacaoController(EstoqueService estoqueService){
        this.estoqueService = estoqueService;
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
