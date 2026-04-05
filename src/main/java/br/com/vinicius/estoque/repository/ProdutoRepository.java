package br.com.vinicius.estoque.repository;

import br.com.vinicius.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
