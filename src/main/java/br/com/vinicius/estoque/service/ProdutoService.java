package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Produto;
import br.com.vinicius.estoque.model.StatusProduto;
import br.com.vinicius.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }

    public Produto cadastrar(Produto produto) {
        validarDadosObrigatorios(produto);
        validarPrecos(produto);

        if (produto.getDataCadastro() == null) {
            produto.setDataCadastro(LocalDate.now());
        }

        if(produto.getId() == null){
            produto.setStatus(StatusProduto.ATIVO);
        }

        if(produto.getQuantidadeTotalEstoque() == null){
            produto.setQuantidadeTotalEstoque(0);
        }

        return produtoRepository.save(produto);
    }

    public Produto atualizar(Produto produto) {
        if (produto.getId() == null) {
            throw new IllegalArgumentException("ID do produto é obrigatório para atualização.");
        }

        validarDadosObrigatorios(produto);
        validarPrecos(produto);

        return produtoRepository.save(produto);
    }

    public Double calcularPrecoVenda(Double custo, Double margemLucroPercentagem) {
        if (custo == null) return 0.0;
        double valorMargem = custo * (margemLucroPercentagem / 100);
        return custo + valorMargem;
    }

    private void validarDadosObrigatorios(Produto p) {
        if (p.getNome() == null || p.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }

        if (p.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria do produto é obrigatória.");
        }

        if (p.getFornecedor() == null) {
            throw new IllegalArgumentException("Fornecedor do produto é obrigatório.");
        }
    }

    private void validarPrecos(Produto p) {
        if (p.getPrecoCusto() == null || p.getPrecoCusto() < 0) {
            throw new IllegalArgumentException("Preço de custo inválido.");
        }

        if (p.getPrecoVenda() == null || p.getPrecoVenda() < 0) {
            throw new IllegalArgumentException("Preço de venda inválido.");
        }
    }

    public Produto buscarPorId(Integer id){
        return produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado no banco de dados."));
    }

    public List<Produto> buscarPorNome(String nome){
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }
}