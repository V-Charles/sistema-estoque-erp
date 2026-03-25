package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Perfil;
import br.com.vinicius.estoque.model.Usuario;
import br.com.vinicius.estoque.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario cadastrar(Usuario usuario){
        if(usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()){
            throw new IllegalArgumentException("Login é obrigatório.");
        }
        if(usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()){
            throw new IllegalArgumentException("Senha é obrigatória.");
        }

        usuario.setSenha(gerarMD5(usuario.getSenha()));
        usuario.setAtivo(true);

        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String login, String senhaPura) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login é obrigatório.");
        }

        if (senhaPura == null || senhaPura.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }

        String senhaCriptografada = gerarMD5(senhaPura);

        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);
        if(usuarioOpt.isEmpty()){
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        Usuario usuarioDoBanco = usuarioOpt.get();

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
}
