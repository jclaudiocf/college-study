package com.projeto.view.nativo.cadastros;

import java.awt.Component;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.projeto.controller.enums.EAbas;
import com.projeto.controller.enums.EPeriodicidade;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoFixo;
import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.interfaces.ITabela;
import com.projeto.model.tabelas.Fixos;
import com.projeto.model.tabelas.Usuarios;
import com.projeto.view.nativo.padrao.AbstractPadraoUI;
import com.projeto.view.nativo.padrao.IPadraoUI;

public class FixosUI extends AbstractPadraoUI implements IPadraoUI  {
	// Nome text, Titulo label, Valor padrão, Tipo do componente
	String[][] componentes = {{"Codigo", "Código", "0", "text"},
			                  {"TipoFixo", "Tipo", "", "tipoFixo"},
			                  {"Descricao", "Descrição", "", "text"},
			                  {"Valor", "Valor", "1", "valor"},
			                  {"Periodicidade", "Periodicidade", "", "periodicidade"},
			                  {"Situacao", "Situação", "", "situacao"}};
		
	Fixos fixo = new Fixos();

	@Override
	public void prepararComponentes() {
		adicionarComponente(componentes);
	}

	@Override
	public String getNomeTela() {
		return EAbas.FIXOS.getNome();
	}

	@Override
	public ITabela getTabela() {
		return fixo;
	}

	@Override
	public void setValoresObjeto() {
		fixo.setCodigoUsuario(Usuarios.CODIGO_USUARIO_ATIVO);
		JPanel componentes = getComponentes();
		for (int i = 0; i < componentes.getComponentCount(); i++) {
			try {
				switch (componentes.getComponent(i).getName()) {
				case "textFieldCodigo":
					fixo.setCodigo(Integer.valueOf(((JTextField) componentes.getComponent(i)).getText()));
					break;
				case "comboBoxTipoFixo":
					@SuppressWarnings("unchecked")
					String valorTipofixo = ((JComboBox<String>) componentes.getComponent(i)).getSelectedItem().toString();
					fixo.setTipoFixo(ETipoFixo.valueOf(valorTipofixo));
					break;
				case "textFieldDescricao":
					fixo.setDescricao(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "textFieldValor":
					Double valorReal = new Double(((JFormattedTextField) componentes.getComponent(i)).getValue().toString());
					fixo.setValor(valorReal);
					break;
				case "comboBoxPeriodicidade":
					@SuppressWarnings("unchecked")
					String valorPeriodicidade = ((JComboBox<String>) componentes.getComponent(i)).getSelectedItem().toString();
					fixo.setPeriodicidade(EPeriodicidade.valueOf(valorPeriodicidade));
					break;
				case "comboBoxSituacao":
					@SuppressWarnings("unchecked")
					String valorSituacao = ((JComboBox<String>) componentes.getComponent(i)).getSelectedItem().toString();
					fixo.setSituacao(ESituacao.valueOf(valorSituacao));
					break;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void setValoresComponentes() {
		Fixos fixoFiltro = new Fixos();
		fixoFiltro.setCodigo(getTabela().getCodigo());
		
		for (Fixos fixo: FactoryNegocio.listar(fixoFiltro).values()) {
			for(Component componente: getComponentes().getComponents()) {
				switch (componente.getName()) {
				case "textFieldCodigo":
					((JTextField) componente).setText(fixo.getCodigo().toString());
					break;
				case "comboBoxTipoFixo": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaTipoFixo = ((JComboBox<String>) componente);
					for (int i = 0; i < listaTipoFixo.getItemCount(); i++) {
						if (fixo.getTipoFixo().name().equals(listaTipoFixo.getItemAt(i))) {
							listaTipoFixo.setSelectedIndex(i);
						}
					}
				}
				break;
				case "textFieldDescricao":
					((JTextField) componente).setText(fixo.getDescricao());
					break;
				case "textFieldValor":
					((JFormattedTextField) componente).setValue(fixo.getValor());
					break;
				case "comboBoxPeriodicidade": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaPeriodicidade = ((JComboBox<String>) componente);
					for (int i = 0; i < listaPeriodicidade.getItemCount(); i++) {
						if (fixo.getPeriodicidade().name().equals(listaPeriodicidade.getItemAt(i))) {
							listaPeriodicidade.setSelectedIndex(i);
						}
					}
				}
				break;
				case "comboBoxSituacao": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaSituacao = ((JComboBox<String>) componente);
					for (int i = 0; i < listaSituacao.getItemCount(); i++) {
						if (fixo.getSituacao().name().equals(listaSituacao.getItemAt(i))) {
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
		String [] colunas = {"Código", "Tipo", "Descrição", "Valor", "Periodicidade", "Situação"};
		return colunas;
	}

	@Override
	public Object[][] getDadosGrid() {
		Fixos filtro = new Fixos();
		filtro.setCodigo(0);
		filtro.setCodigoUsuario(Usuarios.CODIGO_USUARIO_ATIVO);
		Map<Integer, Fixos> contas = FactoryNegocio.listar(filtro);
		Object[][] dados = new Object[contas.values().size()][getColunasGrid().length];
		
		Integer posicao = 0;
		for (Fixos conta : contas.values()) {
			dados[posicao][0] = conta.getCodigo().toString();
			dados[posicao][1] = conta.getTipoFixo().name();
			dados[posicao][2] = conta.getDescricao();
			dados[posicao][3] = conta.getValor().toString();
			dados[posicao][4] = conta.getPeriodicidade().name();
			dados[posicao][5] = conta.getSituacao().name();
			posicao++;
		}
		return dados;
	}
}
