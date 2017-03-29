package br.com.financaweb.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.tabelas.Usuarios;

@Controller
public class PrincipalController {
	
	@RequestMapping("/")
	public String principal() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Usuarios usuario, HttpSession sessao) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuario.getLogin() != null && usuarioDAO.login(usuario.getLogin(), usuario.getSenha())) {
			usuario.setCodigo(Usuarios.CODIGO_USUARIO_ATIVO);
			sessao.setAttribute("usuarioLogado", usuario);
			return "redirect:/";
		}
		return "login.jsp?mensagem=Falha&";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession sessao) {
		 sessao.invalidate();
		 return "login.jsp?mensagem=Encerrado&";
	}
	
	@RequestMapping("/sobre")
	public String sobre() {
		return "sobre";
	}
	
	@RequestMapping("/construcao")
	public String construcao() {
		return "construcao";
	}
}
