package com.projeto.controller.factorys;

import java.util.Map;

import javax.swing.JOptionPane;

import com.projeto.controller.interfaces.ITabela;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.dao.CentroCustoDAO;
import com.projeto.model.dao.ContaDAO;
import com.projeto.model.dao.FixoDAO;
import com.projeto.model.dao.MovimentoDAO;
import com.projeto.model.dao.ParcelaDAO;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.tabelas.CentroCustos;
import com.projeto.model.tabelas.Contas;
import com.projeto.model.tabelas.Fixos;
import com.projeto.model.tabelas.Movimentos;
import com.projeto.model.tabelas.Parcelas;
import com.projeto.model.tabelas.Usuarios;

public class FactoryNegocio {
	private static String mensagemErro = "";

	public static String getMensagemErro() {
		return mensagemErro;
	}
	
	public static boolean salvar(ITabela tabela) {
		if (tabela.getCodigo() > 0) {
			return alterar(tabela);
		}
		return adicionar(tabela);
	}
	
	public static boolean adicionar(ITabela tabela) {
		try {
			if (tabela instanceof Usuarios) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.adicionar((Usuarios) tabela);
			} else if (tabela instanceof CentroCustos) {
				CentroCustoDAO centroCustoDAO = new CentroCustoDAO();
				centroCustoDAO.adicionar((CentroCustos) tabela);
			} else if (tabela instanceof Contas) {
				ContaDAO contaDAO = new ContaDAO();
				contaDAO.adicionar((Contas) tabela);
			} else if (tabela instanceof Fixos) {
				FixoDAO fixoDAO = new FixoDAO();
				fixoDAO.adicionar((Fixos) tabela);
			} else if (tabela instanceof Movimentos) {
				MovimentoDAO movimentoDAO = new MovimentoDAO();
				movimentoDAO.adicionar((Movimentos) tabela);
			} else if (tabela instanceof Parcelas) {
				ParcelaDAO parcelaDAO = new ParcelaDAO();
				parcelaDAO.adicionar((Parcelas) tabela);
			}
		} catch (BancoException e) {
			mensagemErro = e.getMessage();
			return false;
		}
		return true;
	}

	public static boolean alterar(ITabela tabela) {
		try {
			if (tabela instanceof Usuarios) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.alterar((Usuarios) tabela);
			} else if (tabela instanceof CentroCustos) {
				CentroCustoDAO centroCustoDAO = new CentroCustoDAO();
				centroCustoDAO.alterar((CentroCustos) tabela);
			} else if (tabela instanceof Contas) {
				ContaDAO contaDAO = new ContaDAO();
				contaDAO.alterar((Contas) tabela);
			} else if (tabela instanceof Fixos) {
				FixoDAO fixoDAO = new FixoDAO();
				fixoDAO.alterar((Fixos) tabela);
			} else if (tabela instanceof Movimentos) {
				MovimentoDAO movimentoDAO = new MovimentoDAO();
				movimentoDAO.alterar((Movimentos) tabela);
			} else if (tabela instanceof Parcelas) {
				ParcelaDAO parcelaDAO = new ParcelaDAO();
				parcelaDAO.alterar((Parcelas) tabela);
			}
		} catch (BancoException e) {
			mensagemErro = e.getMessage();
			return false;
		}
		return true;
	}

	public static boolean deletar(ITabela tabela) {
		try {
			if (tabela instanceof Usuarios) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.deletar((Usuarios) tabela);
			} else if (tabela instanceof CentroCustos) {
				CentroCustoDAO centroCustoDAO = new CentroCustoDAO();
				centroCustoDAO.deletar((CentroCustos) tabela);
			} else if (tabela instanceof Contas) {
				ContaDAO contaDAO = new ContaDAO();
				contaDAO.deletar((Contas) tabela);
			} else if (tabela instanceof Fixos) {
				FixoDAO fixoDAO = new FixoDAO();
				fixoDAO.deletar((Fixos) tabela);
			} else if (tabela instanceof Movimentos) {
				MovimentoDAO movimentoDAO = new MovimentoDAO();
				movimentoDAO.deletar((Movimentos) tabela);
			} else if (tabela instanceof Parcelas) {
				ParcelaDAO parcelaDAO = new ParcelaDAO();
				parcelaDAO.deletar((Parcelas) tabela);
			}
		} catch (BancoException e) {
			mensagemErro = e.getMessage();
			return false;
		}
		return true;
	}

	public static Map<Integer, CentroCustos> listar(CentroCustos aCentroCusto) {
		CentroCustoDAO dao = new CentroCustoDAO();
		try {
			return dao.listar(aCentroCusto);
		} catch (BancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	public static Map<Integer, Contas> listar(Contas aConta) {
		ContaDAO dao = new ContaDAO();
		try {
			return dao.listar(aConta);
		} catch (BancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	public static Map<Integer, Fixos> listar(Fixos aFixo) {
		FixoDAO dao = new FixoDAO();
		try {
			return dao.listar(aFixo);
		} catch (BancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	public static Map<Integer, Movimentos> listar(Movimentos aMovimento) {
		MovimentoDAO dao = new MovimentoDAO();
		try {
			return dao.listar(aMovimento);
		} catch (BancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	public static Map<Integer, Parcelas> listar(Parcelas aParcela) {
		ParcelaDAO dao = new ParcelaDAO();
		try {
			return dao.listar(aParcela);
		} catch (BancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	public static Map<Integer, Usuarios> listar(Usuarios aUsuario) {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			return dao.listar(aUsuario);
		} catch (BancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
}
