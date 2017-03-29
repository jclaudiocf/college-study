package br.com.financaweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.dao.ContaDAO;
import com.projeto.model.tabelas.Contas;
import com.projeto.model.tabelas.Usuarios;

@Controller
public class ContaController {
	
	@RequestMapping("/cadastrar-conta")
	public String cadastrar() {
		return "conta/cadastrar";
	}
	
	@RequestMapping("/salvar-conta")
	public String salvar(Contas conta) {
		if (FactoryNegocio.salvar(conta)) {
			return "forward:listar-contas";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/excluir-conta")
	public String excluir(Contas conta) {
		if (FactoryNegocio.deletar(conta)) {
			return "forward:listar-contas";
		}
		return String.format("erro.jsp?mensagem=%s&", FactoryNegocio.getMensagemErro());
	}
	
	@RequestMapping("/consultar-conta")
	public String consultar(Contas conta) {
		List<Contas> lstContas = new ArrayList<>(FactoryNegocio.listar(conta).values());
		Contas consulta = lstContas.get(0);
		String url = String.format("conta/consultar.jsp?codigo=%s&codigoUsuario=%s&descricao=%s&tipoConta=%s&agencia=%s&digitoAgencia=%s&conta=%s&digitoConta=%s&enderecoAgencia=%s&saldo=%s&situacao=%s&", 
				consulta.getCodigo(),
				consulta.getCodigoUsuario(),
				consulta.getDescricao(),
				consulta.getTipoConta(),
				consulta.getAgencia(),
				consulta.getDigitoAgencia(),
				consulta.getConta(),
				consulta.getDigitoConta(),
				consulta.getEnderecoAgencia(),
				consulta.getSaldo(),
				consulta.getSituacao());
		return url;
	}
	
	@RequestMapping("/listar-contas")
	public String listar(Model model, HttpSession sessao) {
		ContaDAO dao = new ContaDAO();
		Contas objeto = new Contas();
		Usuarios logado = (Usuarios) sessao.getAttribute("usuarioLogado");
		objeto.setCodigo(0);
		objeto.setCodigoUsuario(logado.getCodigo());
		
		try {
			List<Contas> consulta = new ArrayList<>(dao.listar(objeto).values());
			model.addAttribute("contas", consulta);
		} catch (BancoException e) {
			return String.format("erro.jsp?mensagem=%s&", e.getMessage());
		}
		
		return "conta/listar";
	}
}
