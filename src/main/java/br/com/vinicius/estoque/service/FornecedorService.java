package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Fornecedor;

public class FornecedorService {

    public void cadastrar(Fornecedor fornecedor) {
        validarCamposObrigatorios(fornecedor);
        limparFormatacao(fornecedor);

        // simulação da persistência
        System.out.println("Fornecedor cadastrado com sucesso: " + fornecedor.getRazaoSocial());
    }

    public void atualizar(Fornecedor fornecedor) {
        if (fornecedor.getId() == null) {
            throw new IllegalArgumentException("ID é obrigatório para atualização.");
        }

        validarCamposObrigatorios(fornecedor);
        limparFormatacao(fornecedor);

        System.out.println("Fornecedor atualizado com sucesso: " + fornecedor.getRazaoSocial());
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
