package br.com.vinicius.estoque.model;

public class Fornecedor {

    private Integer id;
    private String razaoSocial;
    private String cnpj;
    private String email;
    private String telefone;
    private boolean ativo;

    public Fornecedor(boolean ativo, String telefone, String email, String cnpj, String razaoSocial, Integer id) {
        this.ativo = ativo;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
