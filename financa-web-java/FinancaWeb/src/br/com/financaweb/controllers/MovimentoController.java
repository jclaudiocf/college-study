package br.com.financaweb.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.dao.MovimentoDAO;
import com.projeto.model.tabelas.Movimentos;
import com.projeto.model.tabelas.Usuarios;

@Controller
public class MovimentoController {
	
	@RequestMapping("/cadastrar-movimento")
	public String cadastrar() {
		return "movimento/cadastrar";
	}
	
	@RequestMapping("/salvar-movimento")
	public String salvar(Movimentos movimento) {
		movimento.setDataLancamento(LocalDateTime.now());
		if (FactoryNegocio.salvar(movimento)) {
			return "forward:listar-movimentos";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/excluir-movimento")
	public String excluir(Movimentos movimento) {
		if (FactoryNegocio.deletar(movimento)) {
			return "forward:listar-movimentos";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/consultar-movimento")
	public String consultar(Movimentos movimento) {
		List<Movimentos> lstContas = new ArrayList<>(FactoryNegocio.listar(movimento).values());
		Movimentos consulta = lstContas.get(0);
		String url = String.format("movimento/consultar.jsp?codigo=%s&codigoUsuario=%s&codigoCentroCusto=%s&codigoConta=%s&tipoMovimento=%s&descricao=%s&dataLancamento=%s&valor=%s&parcelado=%s&situacao=%s&", 
				consulta.getCodigo(),
				consulta.getCodigoUsuario(),
				consulta.getCodigoCentroCusto(),
				consulta.getCodigoConta(),
				consulta.getTipoMovimento(),
				consulta.getDescricao(),
				consulta.getDataLancamento(),
				consulta.getValor(),
				consulta.getParcelado(),
				consulta.getSituacao());
		return url;
	}
	
	@RequestMapping("/listar-movimentos")
	public String listar(Model model, HttpSession sessao) {
		MovimentoDAO dao = new MovimentoDAO();
		Movimentos objeto = new Movimentos();
		Usuarios logado = (Usuarios) sessao.getAttribute("usuarioLogado");
		objeto.setCodigo(0);
		objeto.setCodigoUsuario(logado.getCodigo());
		
		try {
			List<Movimentos> consulta = new ArrayList<>(dao.listar(objeto).values());
			model.addAttribute("movimentos", consulta);
		} catch (BancoException e) {
			return String.format("erro.jsp?mensagem=%s&", e.getMessage());
		}
		
		return "movimento/listar";
	}
}
