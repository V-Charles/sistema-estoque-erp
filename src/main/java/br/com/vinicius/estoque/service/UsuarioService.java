package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Usuario;
import br.com.vinicius.estoque.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
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

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setAtivo(true);

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorretos"));
    }
}