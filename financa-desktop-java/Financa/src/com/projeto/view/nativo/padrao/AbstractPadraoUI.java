package com.projeto.view.nativo.padrao;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.projeto.controller.enums.EDecisao;
import com.projeto.controller.enums.EPeriodicidade;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoConta;
import com.projeto.controller.enums.ETipoFixo;
import com.projeto.controller.enums.ETipoMovimento;
import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.view.nativo.acao.AcaoTela;
import com.projeto.view.nativo.cadastros.CentroCustosUI;
import com.projeto.view.nativo.cadastros.ContasUI;

public abstract class AbstractPadraoUI implements IPadraoUI {
	private JPanel painelContainer = new JPanel(new BorderLayout());
	private JPanel painelComponentes = new JPanel();
	private JPanel painelBotoes = new JPanel();
	private GridBagConstraints gridBag = new GridBagConstraints();
	JButton botaoSalvar;
	JButton botaoExcluir;
	
	private void prepararContainer() {
		painelComponentes.setLayout(new GridBagLayout());
		painelComponentes.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		gridBag.fill = GridBagConstraints.HORIZONTAL;
		gridBag.anchor = GridBagConstraints.LINE_START;
		gridBag.weightx = 0.1;
		gridBag.weighty = 0.1;
	}
	
	private void prepararBotao() {
		JButton botaoNovo = new JButton("Novo");
		botaoNovo.setName("btnNovo");
		botaoNovo.addActionListener(AcaoTela.acaoBotaoNovo(painelBotoes, this));
		
		botaoSalvar = new JButton("Salvar");
		botaoSalvar.setName("btnSalvar");
		botaoSalvar.setEnabled(false);
		botaoSalvar.addActionListener(AcaoTela.acaoBotaoSalvar(painelBotoes, this));
		
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setName("btnCancelar");
		botaoCancelar.setEnabled(false);
		botaoCancelar.addActionListener(AcaoTela.acaoBotaoCancelar(painelBotoes, this));
		
		botaoExcluir = new JButton("Excluir");
		botaoExcluir.setName("btnExcluir");
		botaoExcluir.setEnabled(false);
		botaoExcluir.addActionListener(AcaoTela.acaoBotaoExcluir(painelBotoes, this));
		
		JButton botaoFechar = new JButton("Fechar");
		botaoFechar.setName("btnFechar");
		botaoFechar.addActionListener(AcaoTela.acaoBotaoFecharAba(getNomeTela()));
		
		painelBotoes.add(botaoNovo);
		painelBotoes.add(botaoSalvar);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoExcluir);
		painelBotoes.add(botaoFechar);
	}
	
	protected void adicionarComponente(String[][] componentes) {
		for (int i = 0; i < componentes.length; i++) {
			// "Codigo", "Código", "0", "text"
			String nomeComponente = componentes[i][0];
			String nomeComponenteCorreto = componentes[i][1];
			String valorComponente = componentes[i][2];
			String tipoComponente = componentes[i][3];
			
			JLabel label = new JLabel(nomeComponenteCorreto);
			label.setName(String.format("label%s", nomeComponente));
			gridBag.gridy = i;
			painelComponentes.add(label, gridBag);
			
			switch (tipoComponente) {
			case "text":
				JTextField campo = new JTextField(valorComponente);
				campo.setName(String.format("textField%s", nomeComponente));
				campo.setHorizontalAlignment(JTextField.LEFT);
				painelComponentes.add(campo, gridBag);
				campo.setEnabled(false);
				
				switch (campo.getName()) {
				case "textFieldCodigo": {
						gridBag.fill = GridBagConstraints.NONE;
						
						JButton consulta = new JButton("...");
						consulta.setName("btnConsultaCodigo");
						consulta.setPreferredSize(new Dimension(30, 19));
						consulta.addActionListener(AcaoTela.acaoBotaoConsultaCodigo(this));
						
						painelComponentes.add(consulta, gridBag);
						gridBag.fill = GridBagConstraints.BOTH;
					}
				break;
				case "textFieldCentroCusto": {
					gridBag.fill = GridBagConstraints.NONE;
					
					JButton consulta = new JButton("...");
					consulta.setName("btnConsultaCentroCusto");
					consulta.setPreferredSize(new Dimension(30, 19));
					consulta.addActionListener(AcaoTela.acaoBotaoConsultaCodigo(new CentroCustosUI(), campo));
					
					painelComponentes.add(consulta, gridBag);
					gridBag.fill = GridBagConstraints.BOTH;
				}
				break;
				case "textFieldConta": {
					gridBag.fill = GridBagConstraints.NONE;
					
					JButton consulta = new JButton("...");
					consulta.setName("btnConsultaConta");
					consulta.setPreferredSize(new Dimension(30, 19));
					consulta.addActionListener(AcaoTela.acaoBotaoConsultaCodigo(new ContasUI(), campo));
					
					painelComponentes.add(consulta, gridBag);
					gridBag.fill = GridBagConstraints.BOTH;
				}
				break;
				}
				break;
			case "valor":
				DecimalFormat mascaraDecinal = new DecimalFormat("#,###,###.00") ;
				NumberFormatter mascaraNumerica = new NumberFormatter(mascaraDecinal);
				mascaraNumerica.setFormat(mascaraDecinal);
				mascaraNumerica.setAllowsInvalid(false);
				
				JFormattedTextField campoValor = new JFormattedTextField(new Double(valorComponente));
				campoValor.setName(String.format("textField%s", nomeComponente));
				campoValor.setFormatterFactory(new DefaultFormatterFactory(mascaraNumerica));
				campoValor.setHorizontalAlignment(JTextField.LEFT);
				
				painelComponentes.add(campoValor, gridBag);
				break;
			case "data":
				JSpinner campoData = new JSpinner(new SpinnerDateModel());
				campoData.setName(String.format("data%s", nomeComponente));
				campoData.setEditor(new JSpinner.DateEditor(campoData, "dd/MM/yyyy"));
				
				painelComponentes.add(campoData, gridBag);
				break;
			case "situacao":
				JComboBox<String> campoSituacao = new JComboBox<String>(ESituacao.getNomes());
				campoSituacao.setName(String.format("comboBox%s", nomeComponente));
				
				painelComponentes.add(campoSituacao, gridBag);
				break;
			case "tipoConta":
				JComboBox<String> campoTipoConta = new JComboBox<String>(ETipoConta.getNomes());
				campoTipoConta.setName(String.format("comboBox%s", nomeComponente));
				
				painelComponentes.add(campoTipoConta, gridBag);
				break;
			case "tipoFixo":
				JComboBox<String> campoTipoFixo = new JComboBox<String>(ETipoFixo.getNomes());
				campoTipoFixo.setName(String.format("comboBox%s", nomeComponente));
				
				painelComponentes.add(campoTipoFixo, gridBag);
				break;
			case "periodicidade":
				JComboBox<String> campoPeriodicidade = new JComboBox<String>(EPeriodicidade.getNomes());
				campoPeriodicidade.setName(String.format("comboBox%s", nomeComponente));
				
				painelComponentes.add(campoPeriodicidade, gridBag);
				break;
			case "tipoMovimento":
				JComboBox<String> campoTipoMovimento = new JComboBox<String>(ETipoMovimento.getNomes());
				campoTipoMovimento.setName(String.format("comboBox%s", nomeComponente));
				
				painelComponentes.add(campoTipoMovimento, gridBag);
				break;
			case "parcelado":
				JComboBox<String> campoParcelado = new JComboBox<String>(EDecisao.getNomes());
				campoParcelado.setName(String.format("comboBox%s", nomeComponente));
				
				painelComponentes.add(campoParcelado, gridBag);
				break;
			}
		}
		
		ativarTodosComponentes(false);
	}
	
	protected JPanel getComponentes() {
		return painelComponentes;
	}
	
	public JPanel getPainel() {
		prepararContainer();
		prepararComponentes();
		prepararBotao();
		
		painelContainer.add(painelComponentes, BorderLayout.NORTH);
		painelContainer.add(painelBotoes, BorderLayout.SOUTH);
		
		return painelContainer;
	}
	
	public void ativarTodosComponentes(Boolean ativar) {
		for (Component componente: painelComponentes.getComponents()) {
			if (componente.getName() != null && 
					(componente.getName().contains("text") ||
					componente.getName().contains("valor") ||
					componente.getName().contains("data") ||
					componente.getName().contains("combo")) &&
					!componente.getName().equals("textFieldCodigo")) {
				componente.setEnabled(ativar);
			}
		}
	}
	
	public void limparTodosComponentes() {
		for (Component componente: painelComponentes.getComponents()) {
			if (componente.getName() != null && componente.getName().equals("textFieldCodigo")) {
				((JTextField) componente).setText("0");
			} else if (componente.getName() != null && 	componente.getName().contains("text")) {
				((JTextField) componente).setText("");
			} else if (componente.getName() != null && componente.getName().contains("valor")) {
				((JFormattedTextField) componente).setValue(0);;
			}
		}
	}
	
	public void ativarBotaoExcluir(Boolean ativar) {
		botaoSalvar.setEnabled(ativar);
		botaoExcluir.setEnabled(ativar);
	}
	
	public void gravarRegistroTabela() {
		if (!FactoryNegocio.salvar(getTabela())) {
			JOptionPane.showMessageDialog(null, FactoryNegocio.getMensagemErro());
		}
	}
	
	public void deletarRegistroTabela() {
		if (!FactoryNegocio.deletar(getTabela())) {
			JOptionPane.showMessageDialog(null, FactoryNegocio.getMensagemErro());
		}
	}
}
