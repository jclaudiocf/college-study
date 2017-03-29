package br.com.financaweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.dao.CentroCustoDAO;
import com.projeto.model.tabelas.CentroCustos;
import com.projeto.model.tabelas.Usuarios;

@Controller
public class CentroCustoController {
	
	@RequestMapping("/cadastrar-centrocusto")
	public String cadastrar() {
		return "centrocusto/cadastrar";
	}
	
	@RequestMapping("/salvar-centrocusto")
	public String salvar(CentroCustos centrocusto) {
		if (FactoryNegocio.salvar(centrocusto)) {
			return "forward:listar-centrocustos";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/excluir-centrocusto")
	public String excluir(CentroCustos centrocusto) {
		if (FactoryNegocio.deletar(centrocusto)) {
			return "forward:listar-centrocustos";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/consultar-centrocusto")
	public String consultar(CentroCustos centrocusto) {
		List<CentroCustos> lstUsuario = new ArrayList<>(FactoryNegocio.listar(centrocusto).values());
		CentroCustos consulta = lstUsuario.get(0);
		String url = String.format("centrocusto/consultar.jsp?codigo=%s&codigoUsuario=%s&descricao=%s&situacao=%s&", 
				consulta.getCodigo(),
				consulta.getCodigoUsuario(),
				consulta.getDescricao(),
				consulta.getSituacao());
		return url;
	}
	
	@RequestMapping("/listar-centrocustos")
	public String listar(Model model, HttpSession sessao) {
		CentroCustoDAO dao = new CentroCustoDAO();
		CentroCustos objeto = new CentroCustos();
		Usuarios logado = (Usuarios) sessao.getAttribute("usuarioLogado");
		objeto.setCodigo(0);
		objeto.setCodigoUsuario(logado.getCodigo());
		
		try {
			List<CentroCustos> consulta = new ArrayList<>(dao.listar(objeto).values());
			model.addAttribute("centrocustos", consulta);
		} catch (BancoException e) {
			return String.format("erro.jsp?mensagem=%s&", e.getMessage());
		}
		
		return "centrocusto/listar";
	}
}
