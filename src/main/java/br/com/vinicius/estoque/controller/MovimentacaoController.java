package br.com.vinicius.estoque.controller;

import br.com.vinicius.estoque.model.Movimentacao;
import br.com.vinicius.estoque.model.Produto;
import br.com.vinicius.estoque.model.TipoMovimentacao;
import br.com.vinicius.estoque.model.Usuario;
import br.com.vinicius.estoque.service.EstoqueService;
import br.com.vinicius.estoque.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovimentacaoController {

    private final EstoqueService estoqueService;
    private final ProdutoService produtoService;

    public MovimentacaoController(EstoqueService estoqueService, ProdutoService produtoService){
        this.estoqueService = estoqueService;
        this.produtoService = produtoService;
    }

    @GetMapping("/movimentacoes")
    public String paginaMovimentacoes(Model model){
        return "movimentacoes";
    }

    @GetMapping("/api/movimentacoes")
    @ResponseBody
    public List<Movimentacao> listarMovimentacoesApi(){
        return estoqueService.listarTodas();
    }

    @GetMapping("/registra-movimentacao")
    public String paginaRegistraMovimentacao(@RequestParam("produtoId") Integer produtoId, Model model, RedirectAttributes redirectAttributes){
        Produto produto = produtoService.buscarPorId(produtoId);

        if(produto.getStatus() == null || !produto.getStatus().name().equals("ATIVO")){
            redirectAttributes.addFlashAttribute("erro", "O produto '" + produto.getNome() + "' está inativo/pausado e não pode ser movimentado.");
            return "redirect:/produtos";
        }

        model.addAttribute("produto", produto);
        model.addAttribute("tiposMovimentacao", TipoMovimentacao.values());

        return "registraMovimentacao";
    }

    @PostMapping("/movimentacoes/salvar")
    public String salvaMovimentacao(
            @RequestParam("produtoId") Integer produtoId,
            @RequestParam("quantidade") Integer quantidade,
            @RequestParam("tipoMovimentacao") TipoMovimentacao tipo,
            @RequestParam("observacao") String observacao,
            RedirectAttributes redirectAttributes
    ){
        try{
            Produto produto = produtoService.buscarPorId(produtoId);

            Usuario usuarioLogado = new Usuario();
            usuarioLogado.setId(1);

            estoqueService.criarMovimentacao(produto, quantidade, tipo, observacao, usuarioLogado);

            redirectAttributes.addFlashAttribute("sucesso", "Movimentação registrada com sucesso!");
            return "redirect:/produtos";
        }catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/registra-movimentacao?produtoId=" + produtoId;
        }
    }

    @GetMapping("/visualizar-movimentacao")
    public String paginaVisualizarMovimentacao(@RequestParam("id") Integer id, Model model){
        Movimentacao movimentacao = estoqueService.buscarMovimentacaoPorId(id);
        model.addAttribute("movimentacao", movimentacao);
        return "visualizacaoMovimentacao";
    }
}
