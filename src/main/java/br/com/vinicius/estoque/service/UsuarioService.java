package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Perfil;
import br.com.vinicius.estoque.model.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioService {

    public Usuario autenticar(String login, String senhaPura) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login é obrigatório.");
        }

        if (senhaPura == null || senhaPura.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }

        String senhaCriptografada = gerarMD5(senhaPura);

        // simulação temporária
        Usuario usuarioDoBanco = simularBuscaNoBanco(login);

        if (usuarioDoBanco == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (!usuarioDoBanco.getSenha().equals(senhaCriptografada)) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        if (!usuarioDoBanco.isAtivo()) {
            throw new IllegalArgumentException("Usuário inativo. Contate o administrador.");
        }

        return usuarioDoBanco;
    }

    private String gerarMD5(String senha){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(senha.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array){
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Erro ao criptografar senha: " + e.getMessage());
        }
    }

    // Simula busca no banco
    private Usuario simularBuscaNoBanco(String login){
        if ("vinicius.admin".equals(login)){
            Usuario u = new Usuario();
            u.setId(1);
            u.setNome("Vinicius");
            u.setLogin("vinicius.admin");
            // MD5 de 123
            u.setSenha("202cb962ac59075b964b07152d234b70");
            u.setAtivo(true);
            u.setPerfil(Perfil.GERENTE);
            return u;
        }
        return null;
    }
}
