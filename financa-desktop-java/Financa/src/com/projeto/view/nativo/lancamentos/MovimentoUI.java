package com.projeto.view.nativo.lancamentos;

import java.awt.Component;
import java.time.LocalDateTime;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.projeto.controller.enums.EAbas;
import com.projeto.controller.enums.EDecisao;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoMovimento;
import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.interfaces.ITabela;
import com.projeto.model.tabelas.Movimentos;
import com.projeto.model.tabelas.Usuarios;
import com.projeto.view.nativo.padrao.AbstractPadraoUI;
import com.projeto.view.nativo.padrao.IPadraoUI;

public class MovimentoUI extends AbstractPadraoUI implements IPadraoUI {
	// Nome text, Titulo label, Valor padrão, Tipo do componente	
	String[][] componentes = {{"Codigo", "Código", "0", "text"}, 
							  {"CentroCusto", "Centro de custo", "", "text"},
							  {"Conta", "Conta", "", "text"},
							  {"TipoMovimento", "Tipo do movimento", "", "tipoMovimento"},
							  {"Descricao", "Descrição", "", "text"},
							  {"DataLancamento", "Data do lançamento", "", "data"},
							  {"Valor", "Valor", "1", "valor"},
							  {"Parcelado", "Parcelado", "", "parcelado"},
							  {"Situacao", "Situação", "", "situacao"}};
	
	Movimentos movimento = new Movimentos();

	@Override
	public void prepararComponentes() {
		adicionarComponente(componentes);
	}

	@Override
	public String getNomeTela() {
		return EAbas.MOVIMENTO.getNome();
	}

	@Override
	public ITabela getTabela() {
		return movimento;
	}

	@Override
	public void setValoresObjeto() {
		movimento.setCodigoUsuario(Usuarios.CODIGO_USUARIO_ATIVO);
		for (Component componente: getComponentes().getComponents()) {
			try {
				switch (componente.getName()) {
				case "textFieldCodigo":
					movimento.setCodigo(Integer.valueOf(((JTextField) componente).getText()));
					break;
				case "textFieldCentroCusto":
					movimento.setCodigoCentroCusto(Integer.valueOf(((JTextField) componente).getText()));
					break;
				case "textFieldConta":
					movimento.setCodigoConta(Integer.valueOf(((JTextField) componente).getText()));
					break;
				case "comboBoxTipoMovimento":
					@SuppressWarnings("unchecked")
					String valorTipoMovimento = ((JComboBox<String>) componente).getSelectedItem().toString();
					movimento.setTipoMovimento(ETipoMovimento.valueOf(valorTipoMovimento));
					break;
				case "textFieldDescricao":
					movimento.setDescricao(((JTextField) componente).getText());
					break;
				case "dataDataLancamento":
					String valorTextDataBackup = "";
					Integer pocisao = 0;
					
					JComponent editor = ((JSpinner) componente).getEditor();
					String valorTextData = ((JSpinner.DateEditor) editor).getTextField().getText();
					
					valorTextDataBackup = valorTextData;
					pocisao = valorTextDataBackup.indexOf('/');
					String valorTextoDia = valorTextDataBackup.substring(0, pocisao);
					
					valorTextDataBackup = valorTextDataBackup.substring(++pocisao);
					pocisao = valorTextDataBackup.indexOf('/');
					String valorTextoMes = valorTextDataBackup.substring(0, pocisao);
					
					valorTextDataBackup = valorTextDataBackup.substring(++pocisao);
					String valorTextoAno = valorTextDataBackup;

					LocalDateTime localDataHora = LocalDateTime.of(Integer.valueOf(valorTextoAno), Integer.valueOf(valorTextoMes), Integer.valueOf(valorTextoDia), 0, 0);
					movimento.setDataLancamento(localDataHora);
					break;
				case "textFieldValor":
					Double valorReal = new Double(((JFormattedTextField) componente).getValue().toString());
					movimento.setValor(valorReal);
					break;
				case "comboBoxParcelado":
					@SuppressWarnings("unchecked")
					String valorParcelado = ((JComboBox<String>) componente).getSelectedItem().toString();
					movimento.setParcelado(EDecisao.valueOf(valorParcelado));
					break;
				case "comboBoxSituacao":
					@SuppressWarnings("unchecked")
					String valorSituacao = ((JComboBox<String>) componente).getSelectedItem().toString();
					movimento.setSituacao(ESituacao.valueOf(valorSituacao));
					break;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setValoresComponentes() {
		Movimentos movimentoFiltro = new Movimentos();
		movimentoFiltro.setCodigo(getTabela().getCodigo());
		
		for (Movimentos movimento: FactoryNegocio.listar(movimentoFiltro).values()) {
			for(Component componente: getComponentes().getComponents()) {
				switch (componente.getName()) {
				case "textFieldCodigo":
					((JTextField) componente).setText(movimento.getCodigo().toString());
					break;
				case "textFieldCentroCusto":
					((JTextField) componente).setText(movimento.getCodigoCentroCusto().toString());
					break;
				case "textFieldConta":
					((JTextField) componente).setText(movimento.getCodigoConta().toString());
					break;
				case "comboBoxTipoMovimento": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaMovimento = ((JComboBox<String>) componente);
					for (int i = 0; i < listaMovimento.getItemCount(); i++) {
						if (movimento.getTipoMovimento().name().equals(listaMovimento.getItemAt(i))) {
							listaMovimento.setSelectedIndex(i);
						}
					}
				}
				break;
				case "textFieldDescricao":
					((JTextField) componente).setText(movimento.getDescricao());
					break;
				case "dataDataLancamento":
					LocalDateTime localDataHora = movimento.getDataLancamento();
					Integer valorDia = localDataHora.getDayOfMonth();
					Integer valorMes = localDataHora.getMonthValue();
					Integer valorAno = localDataHora.getYear();
					String valorTextoData = String.format("%d/%d/%d", valorDia, valorMes, valorAno);
					
					JComponent editor = ((JSpinner) componente).getEditor();
					((JSpinner.DateEditor) editor).getTextField().setText(valorTextoData);
					break;
				case "textFieldValor":
					((JFormattedTextField) componente).setValue(movimento.getValor());
					break;
				case "comboBoxParcelado": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaParcelado = ((JComboBox<String>) componente);
					for (int i = 0; i < listaParcelado.getItemCount(); i++) {
						if (movimento.getParcelado().name().equals(listaParcelado.getItemAt(i))) {
							listaParcelado.setSelectedIndex(i);
						}
					}
				}
				break;
				case "comboBoxSituacao": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaSituacao = ((JComboBox<String>) componente);
					for (int i = 0; i < listaSituacao.getItemCount(); i++) {
						if (movimento.getSituacao().name().equals(listaSituacao.getItemAt(i))) {
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
		String [] colunas = {"Código", "Centro de custo", "Tipo do movimento", "Descrição", "Data do lançamento", "Valor", "Parcelado", "Situação"};
		return colunas;
	}

	@Override
	public Object[][] getDadosGrid() {
		Movimentos filtro = new Movimentos();
		filtro.setCodigo(0);
		filtro.setCodigoUsuario(Usuarios.CODIGO_USUARIO_ATIVO);
		Map<Integer, Movimentos> movimentos = FactoryNegocio.listar(filtro);
		Object[][] dados = new Object[movimentos.values().size()][getColunasGrid().length];
		
		Integer posicao = 0;
		for (Movimentos centroCusto : movimentos.values()) {
			dados[posicao][0] = centroCusto.getCodigo().toString();
			dados[posicao][1] = centroCusto.getCodigoCentroCusto().toString();
			dados[posicao][2] = centroCusto.getTipoMovimento().name();
			dados[posicao][3] = centroCusto.getDescricao();
			dados[posicao][4] = centroCusto.getDataLancamento().toString();
			dados[posicao][5] = centroCusto.getValor().toString();
			dados[posicao][6] = centroCusto.getParcelado().name();
			dados[posicao][7] = centroCusto.getSituacao().name();
			posicao++;
		}
		return dados;
	}
}
