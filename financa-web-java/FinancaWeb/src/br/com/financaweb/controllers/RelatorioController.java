package br.com.financaweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RelatorioController {
	
	@RequestMapping("/relatorio-saldo")
	public String construcao() {
		return "relatorios/saldo";
	}
}
