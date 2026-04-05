package br.com.vinicius.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping({"/", "/login", "/index"})
    public String paginaLogin(){
        return "index";
    }

    @GetMapping("/recuperar-senha")
    public String paginaRecuperarSenha(){
        return "recuperarSenha";
    }

    @GetMapping("/dashboard")
    public String paginaDashboard(){
        return "dashboard";
    }
}
