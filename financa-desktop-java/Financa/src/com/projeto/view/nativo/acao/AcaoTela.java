package com.projeto.view.nativo.acao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.projeto.view.nativo.auxiliar.SingletonAba;
import com.projeto.view.nativo.padrao.AbstractPadraoUI;
import com.projeto.view.nativo.padrao.ConsultaUI;

public class AcaoTela {
	
	public static ActionListener acaoBotaoNovo(JPanel painelBotao, AbstractPadraoUI padraoUI) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < painelBotao.getComponentCount(); i++) {
					Boolean acao = false;
					switch (painelBotao.getComponent(i).getName()) {
					case "btnNovo":
						acao = false;
						break;
					case "btnSalvar":
						acao = true;
						break;
					case "btnCancelar":
						acao = true;
						break;
					case "btnExcluir":
						acao = false;
						break;
					default:
						break;
					}
					painelBotao.getComponent(i).setEnabled(acao);
				}
				
				padraoUI.limparTodosComponentes();
				padraoUI.ativarTodosComponentes(true);
			}
		};
	}
	
	public static ActionListener acaoBotaoSalvar(JPanel painelBotao, AbstractPadraoUI padraoUI) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < painelBotao.getComponentCount(); i++) {
					Boolean acao = true;
					switch (painelBotao.getComponent(i).getName()) {
					case "btnNovo":
						acao = true;
						break;
					case "btnSalvar":
						acao = false;
						break;
					case "btnCancelar":
						acao = false;
						break;
					case "btnExcluir":
						acao = false;
						break;
					default:
						break;
					}
					painelBotao.getComponent(i).setEnabled(acao);
				}
				
				padraoUI.setValoresObjeto();
				padraoUI.gravarRegistroTabela();
				padraoUI.ativarTodosComponentes(false);
			}
		};
	}
	
	public static ActionListener acaoBotaoExcluir(JPanel painelBotao, AbstractPadraoUI padraoUI) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < painelBotao.getComponentCount(); i++) {
					Boolean acao = true;
					switch (painelBotao.getComponent(i).getName()) {
					case "btnNovo":
						acao = true;
						break;
					case "btnSalvar":
						acao = false;
						break;
					case "btnCancelar":
						acao = false;
						break;
					case "btnExcluir":
						acao = false;
						break;
					default:
						break;
					}
					painelBotao.getComponent(i).setEnabled(acao);
				}
				
				padraoUI.setValoresObjeto();
				padraoUI.deletarRegistroTabela();
				padraoUI.limparTodosComponentes();
				padraoUI.ativarTodosComponentes(false);
			}
		};
	}

	public static ActionListener acaoBotaoCancelar(JPanel painelBotao, AbstractPadraoUI padraoUI) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < painelBotao.getComponentCount(); i++) {
					Boolean acao = true;
					switch (painelBotao.getComponent(i).getName()) {
					case "btnNovo":
						acao = true;
						break;
					case "btnSalvar":
						acao = false;
						break;
					case "btnCancelar":
						acao = false;
						break;
					case "btnExcluir":
						acao = false;
						break;
					default:
						break;
					}
					painelBotao.getComponent(i).setEnabled(acao);
				}
				
				padraoUI.ativarTodosComponentes(false);
			}
		};
	}
	
	public static ActionListener acaoBotaoFecharAba(String nomeTela) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SingletonAba.removerAba(nomeTela);
			}
		};
	}
	
	public static ActionListener acaoBotaoConsultaCodigo(AbstractPadraoUI padraoUI) {
		return acaoBotaoConsultaCodigo(padraoUI, null);
	}
	
	public static ActionListener acaoBotaoConsultaCodigo(AbstractPadraoUI padraoUI, JTextField campoRetorno) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsultaUI telaConsulta = new ConsultaUI();
				telaConsulta.mostrarTelaConsulta(padraoUI.getDadosGrid(), padraoUI.getColunasGrid(), padraoUI, campoRetorno);
			}
		};
	}

	public static ActionListener acaoSairFrame() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
	}
}
