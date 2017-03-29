package br.com.financaweb.controllers;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.controller.conexao.TesteConexao;
import com.projeto.controller.conexao.configuracao.ArquivoPropriedades;
import com.projeto.view.nativo.populador.PopuladorBase;

@Controller
public class ConfiguracaoController {

	@RequestMapping("/configuracao")
	public String configuracao() {
		return "configuracao";
	}

	@RequestMapping("/salvar-testar-configuracao")
	public String salvarTestar(HttpServletRequest request) {
		Properties propriedades = new Properties();
		propriedades.put("servidor.banco.homologado", "mysql");
		propriedades.put("servidor.banco.dados.ip", request.getParameter("host"));
		propriedades.put("servidor.banco.dados.porta", request.getParameter("porta"));
		propriedades.put("banco.dados.nome", "financa");
		propriedades.put("banco.dados.usuario.nome", request.getParameter("usuario"));
		propriedades.put("banco.dados.usuario.senha", request.getParameter("senha"));
		
		ArquivoPropriedades.salvarConfiguracoes(propriedades);
		if (TesteConexao.conexaoEstahAtiva()) {
			if (request.getParameter("popular").equals("S")) {
				new PopuladorBase().popular();
			}
			return "login";
		}
		return "configuracao";
	}
}
