package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Fornecedor;
import br.com.vinicius.estoque.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository){
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listarTodos(){
        return fornecedorRepository.findAll();
    }

    public Fornecedor cadastrar(Fornecedor fornecedor) {
        validarCamposObrigatorios(fornecedor);
        limparFormatacao(fornecedor);

        fornecedor.setAtivo(true);
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Fornecedor fornecedor) {
        if (fornecedor.getId() == null) {
            throw new IllegalArgumentException("ID é obrigatório para atualização.");
        }

        validarCamposObrigatorios(fornecedor);
        limparFormatacao(fornecedor);

        return fornecedorRepository.save(fornecedor);
    }

    private void validarCamposObrigatorios(Fornecedor f) {
        if (f.getRazaoSocial() == null || f.getRazaoSocial().trim().isEmpty()) {
            throw new IllegalArgumentException("Razão Social/Nome é obrigatório.");
        }

        if (f.getEmail() == null || f.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail é obrigatório.");
        }

        if (f.getCnpj() == null || f.getCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ é obrigatório.");
        }

        if (f.getTelefone() == null || f.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
    }

    private void limparFormatacao(Fornecedor f) {
        if (f.getCnpj() != null) {
            String cnpjLimpo = f.getCnpj().replaceAll("[^0-9]", "");
            f.setCnpj(cnpjLimpo);
        }

        if (f.getTelefone() != null) {
            String telLimpo = f.getTelefone().replaceAll("[^0-9]", "");
            f.setTelefone(telLimpo);
        }
    }
}
