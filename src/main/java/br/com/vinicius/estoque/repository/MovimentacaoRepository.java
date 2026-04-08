package br.com.vinicius.estoque.repository;

import br.com.vinicius.estoque.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

    List<Movimentacao> findByProdutoIdOrderByDataHoraDesc(Integer produtoId);

    List<Movimentacao> findByProdutoFornecedorIdOrderByDataHoraDesc(Integer fornecedorId);
}
