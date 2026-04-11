package br.com.vinicius.estoque.controller;

import br.com.vinicius.estoque.service.RecuperacaoSenhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/recuperacao")
public class RecuperacaoController {

    private final RecuperacaoSenhaService recuperacaoService;

    public RecuperacaoController(RecuperacaoSenhaService recuperacaoService) {
        this.recuperacaoService = recuperacaoService;
    }

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitar(@RequestBody Map<String, String> payload) {
        try {
            recuperacaoService.solicitarCodigo(payload.get("email"));
            return ResponseEntity.ok("Código enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validar")
    public ResponseEntity<String> validar(@RequestBody Map<String, String> payload) {
        boolean valido = recuperacaoService.validarCodigo(payload.get("email"), payload.get("codigo"));
        if (valido) {
            return ResponseEntity.ok("Código válido!");
        } else {
            return ResponseEntity.badRequest().body("Código inválido.");
        }
    }

    @PostMapping("/redefinir")
    public ResponseEntity<String> redefinir(@RequestBody Map<String, String> payload) {
        try {
            recuperacaoService.redefinirSenha(
                    payload.get("email"),
                    payload.get("codigo"),
                    payload.get("novaSenha")
            );
            return ResponseEntity.ok("Senha alterada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}