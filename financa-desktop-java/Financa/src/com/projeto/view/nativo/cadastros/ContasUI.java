package com.projeto.view.nativo.cadastros;

import java.awt.Component;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.projeto.controller.enums.EAbas;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoConta;
import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.interfaces.ITabela;
import com.projeto.model.tabelas.Contas;
import com.projeto.model.tabelas.Usuarios;
import com.projeto.view.nativo.padrao.AbstractPadraoUI;
import com.projeto.view.nativo.padrao.IPadraoUI;

public class ContasUI extends AbstractPadraoUI implements IPadraoUI {
	// Nome text, Titulo label, Valor padrão, Tipo do componente
	String[][] componentes = {{"Codigo", "Código", "0", "text"},
			                  {"Descricao", "Descrição", "", "text"},
			                  {"TipoConta", "Tipo da conta", "", "tipoConta"},
			                  {"CodigoAgencia", "Agência", "", "text"},
			                  {"DigitoAgencia", "Digito da agência", "", "text"},
			                  {"CodigoConta", "Conta", "", "text"},
			                  {"DigitoConta", "Digito da conta", "", "text"},
			                  {"EnderecoAgencia", "Endereço da agência", "", "text"},
			                  {"Saldo", "Saldo", "1", "valor"},
			                  {"Situacao", "Situação", "", "situacao"}};
	
	Contas conta = new Contas();

	@Override
	public void prepararComponentes() {
		adicionarComponente(componentes);
	}

	@Override
	public String getNomeTela() {
		return EAbas.CONTAS.getNome();
	}

	@Override
	public ITabela getTabela() {
		return conta;
	}

	@Override
	public void setValoresObjeto() {
		conta.setCodigoUsuario(Usuarios.CODIGO_USUARIO_ATIVO);
		JPanel componentes = getComponentes();
		for (int i = 0; i < componentes.getComponentCount(); i++) {
			try {
				switch (componentes.getComponent(i).getName()) {
				case "textFieldCodigo":
					conta.setCodigo(Integer.valueOf(((JTextField) componentes.getComponent(i)).getText()));
					break;
				case "textFieldDescricao":
					conta.setDescricao(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "comboBoxTipoConta":
					@SuppressWarnings("unchecked")
					String valorTipoConta = ((JComboBox<String>) componentes.getComponent(i)).getSelectedItem().toString();
					conta.setTipoConta(ETipoConta.valueOf(valorTipoConta));
					break;
				case "textFieldAgencia":
					conta.setAgencia(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "textFieldDigitoAgencia":
					conta.setDigitoAgencia(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "textFieldConta":
					conta.setConta(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "textFieldDigitoConta":
					conta.setDigitoConta(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "textFieldEnderecoAgencia":
					conta.setEnderecoAgencia(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "textFieldSaldo":
					Double valorReal = new Double(((JFormattedTextField) componentes.getComponent(i)).getValue().toString());
					conta.setSaldo(valorReal);
					break;
				case "comboBoxSituacao":
					@SuppressWarnings("unchecked")
					String valorSituacao = ((JComboBox<String>) componentes.getComponent(i)).getSelectedItem().toString();
					conta.setSituacao(ESituacao.valueOf(valorSituacao));
					break;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void setValoresComponentes() {
		Contas contaFiltro = new Contas();
		contaFiltro.setCodigo(getTabela().getCodigo());
		
		for (Contas conta: FactoryNegocio.listar(contaFiltro).values()) {
			for(Component componente: getComponentes().getComponents()) {
				switch (componente.getName()) {
				case "textFieldCodigo":
					((JTextField) componente).setText(conta.getCodigo().toString());
					break;
				case "textFieldDescricao":
					((JTextField) componente).setText(conta.getDescricao());
					break;
				case "comboBoxTipoConta": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaTipoConta = ((JComboBox<String>) componente);
					for (int i = 0; i < listaTipoConta.getItemCount(); i++) {
						if (conta.getTipoConta().name().equals(listaTipoConta.getItemAt(i))) {
							listaTipoConta.setSelectedIndex(i);
						}
					}
				}
				break;
				case "textFieldAgencia":
					((JTextField) componente).setText(conta.getAgencia());
					break;
				case "textFieldDigitoAgencia":
					((JTextField) componente).setText(conta.getDigitoAgencia());
					break;
				case "textFieldConta":
					((JTextField) componente).setText(conta.getConta());
					break;
				case "textFieldDigitoConta":
					((JTextField) componente).setText(conta.getDigitoConta());
					break;
				case "textFieldEnderecoAgencia":
					((JTextField) componente).setText(conta.getEnderecoAgencia());
					break;
				case "textFieldSaldo":
					((JFormattedTextField) componente).setValue(conta.getSaldo());
					break;
				case "comboBoxSituacao": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaSituacao = ((JComboBox<String>) componente);
					for (int i = 0; i < listaSituacao.getItemCount(); i++) {
						if (conta.getSituacao().name().equals(listaSituacao.getItemAt(i))) {
							listaSituacao.setSelectedIndex(i);
						}
					}
				}
				break;
				}
			}
		}
	}

	@Override
	public String[] getColunasGrid() {
		String [] colunas = {"Código", "Descrição", "Tipo da conta", "Agência", "Digito da agência", 
				             "Conta", "Digito da conta", "Endereço da agência", "Saldo", "Situação"};
		return colunas;
	}

	@Override
	public Object[][] getDadosGrid() {
		Contas filtro = new Contas();
		filtro.setCodigo(0);
		filtro.setCodigoUsuario(Usuarios.CODIGO_USUARIO_ATIVO);
		Map<Integer, Contas> contas = FactoryNegocio.listar(filtro);
		Object[][] dados = new Object[contas.values().size()][getColunasGrid().length];
		
		Integer posicao = 0;
		for (Contas conta : contas.values()) {
			dados[posicao][0] = conta.getCodigo().toString();
			dados[posicao][1] = conta.getDescricao();
			dados[posicao][2] = conta.getTipoConta().name();
			dados[posicao][3] = conta.getAgencia();
			dados[posicao][4] = conta.getDigitoAgencia();
			dados[posicao][5] = conta.getConta();
			dados[posicao][6] = conta.getDigitoConta();
			dados[posicao][7] = conta.getEnderecoAgencia();
			dados[posicao][8] = conta.getSaldo().toString();
			dados[posicao][9] = conta.getSituacao().name();
			posicao++;
		}
		return dados;
	}
}
