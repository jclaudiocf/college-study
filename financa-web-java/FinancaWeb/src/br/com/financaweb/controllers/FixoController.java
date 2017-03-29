package br.com.financaweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.dao.FixoDAO;
import com.projeto.model.tabelas.Fixos;
import com.projeto.model.tabelas.Usuarios;

@Controller
public class FixoController {
	
	@RequestMapping("/cadastrar-fixo")
	public String cadastrar() {
		return "fixo/cadastrar";
	}
	
	@RequestMapping("/salvar-fixo")
	public String salvar(Fixos fixo) {
		if (FactoryNegocio.salvar(fixo)) {
			return "forward:listar-fixos";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/excluir-fixo")
	public String excluir(Fixos fixo) {
		if (FactoryNegocio.deletar(fixo)) {
			return "forward:listar-fixos";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/consultar-fixo")
	public String consultar(Fixos fixo) {
		List<Fixos> lista = new ArrayList<>(FactoryNegocio.listar(fixo).values());
		Fixos consulta = lista.get(0);
		String url = String.format("fixo/consultar.jsp?codigo=%s&codigoUsuario=%s&tipoFixo=%s&descricao=%s&valor=%s&periodicidade=%s&situacao=%s&", 
				consulta.getCodigo(),
				consulta.getCodigoUsuario(),
				consulta.getTipoFixo(),
				consulta.getDescricao(),
				consulta.getValor(),
				consulta.getPeriodicidade(),
				consulta.getSituacao());
		return url;
	}
	
	@RequestMapping("/listar-fixos")
	public String listar(Model model, HttpSession sessao) {
		FixoDAO dao = new FixoDAO();
		Fixos objeto = new Fixos();
		Usuarios logado = (Usuarios) sessao.getAttribute("usuarioLogado");
		objeto.setCodigo(0);
		objeto.setCodigoUsuario(logado.getCodigo());
		
		try {
			List<Fixos> consulta = new ArrayList<>(dao.listar(objeto).values());
			model.addAttribute("fixos", consulta);
		} catch (BancoException e) {
			return String.format("erro.jsp?mensagem=%s&", e.getMessage());
		}
		
		return "fixo/listar";
	}
}
