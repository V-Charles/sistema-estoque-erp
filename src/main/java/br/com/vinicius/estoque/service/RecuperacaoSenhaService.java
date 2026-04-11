package br.com.vinicius.estoque.service;

import br.com.vinicius.estoque.model.Usuario;
import br.com.vinicius.estoque.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class RecuperacaoSenhaService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private final Map<String, String> codigosRecuperacao = new HashMap<>();

    public RecuperacaoSenhaService(UsuarioRepository usuarioRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void solicitarCodigo(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(email);

        if (usuario.isPresent()) {
            String codigo = String.format("%06d", new Random().nextInt(999999));
            codigosRecuperacao.put(email, codigo);

            System.out.println("\n==================================================");
            System.out.println("SOLICITAÇÃO DE RECUPERAÇÃO DE SENHA");
            System.out.println("E-mail: " + email);
            System.out.println("CÓDIGO GERADO: " + codigo);
            System.out.println("==================================================\n");

            String mensagem = "Olá " + usuario.get().getNome() + "!\n\n"
                    + "Seu código de recuperação de senha é: " + codigo + "\n\n"
                    + "Se você não solicitou isso, ignore este e-mail.";

            try {
                emailService.enviarEmailTexto(email, "Código de Recuperação - Inventory System", mensagem);
            } catch (Exception e) {
                System.out.println("Aviso Interno: O e-mail real não pôde ser enviado. Erro: " + e.getMessage());
            }

        } else {
            throw new IllegalArgumentException("E-mail não encontrado no sistema.");
        }
    }

    public boolean validarCodigo(String email, String codigo) {
        String codigoSalvo = codigosRecuperacao.get(email);
        return codigoSalvo != null && codigoSalvo.equals(codigo);
    }

    public void redefinirSenha(String email, String codigo, String novaSenha) {
        if (validarCodigo(email, codigo)) {
            Usuario usuario = usuarioRepository.findByLogin(email)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

            usuario.setSenha(passwordEncoder.encode(novaSenha));
            usuarioRepository.save(usuario);

            codigosRecuperacao.remove(email);
        } else {
            throw new IllegalArgumentException("Código inválido ou expirado.");
        }
    }
}