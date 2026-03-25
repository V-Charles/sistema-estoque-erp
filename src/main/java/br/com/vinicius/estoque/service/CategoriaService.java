package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Categoria;
import br.com.vinicius.estoque.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas(){
        return categoriaRepository.findAll();
    }

    public Categoria salvar(Categoria categoria){
        categoria.setAtivo(true);
        return categoriaRepository.save(categoria);
    }
}
