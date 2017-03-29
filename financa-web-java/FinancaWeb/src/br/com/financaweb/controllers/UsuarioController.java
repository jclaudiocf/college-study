package br.com.financaweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.tabelas.Usuarios;

@Controller
public class UsuarioController {
	
	@RequestMapping("/cadastrar-usuario")
	public String cadastrar() {
		return "usuario/cadastrar";
	}
	
	@RequestMapping("/salvar-usuario")
	public String salvar(Usuarios usuario) {
		if (FactoryNegocio.salvar(usuario)) {
			return "forward:listar-usuarios";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/excluir-usuario")
	public String excluir(Usuarios usuario) {
		if (FactoryNegocio.deletar(usuario)) {
			return "forward:listar-usuarios";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/consultar-usuario")
	public String consultar(Usuarios usuario) {
		String url = "";
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		try {
			List<Usuarios> lstuaurio = new ArrayList<>(usuarioDAO.listar(usuario).values());
			Usuarios usuarioCompleto = lstuaurio.get(0);
			url = String.format("usuario/consultar.jsp?codigo=%s&nome=%s&login=%s&senha=%s&situacao=%s&", 
					usuarioCompleto.getCodigo(),
					usuarioCompleto.getNome(),
					usuarioCompleto.getLogin(),
					usuarioCompleto.getSenha(),
					usuarioCompleto.getSituacao());
		} catch (BancoException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	@RequestMapping("/listar-usuarios")
	public String listar(Model model) {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			List<Usuarios> lstUsuario = new ArrayList<>(dao.listar().values());
			model.addAttribute("usuarios", lstUsuario);
		} catch (BancoException e) {
			return String.format("erro.jsp?mensagem=%s&", e.getMessage());
		}
		
		return "usuario/listar";
	}
}
