package com.projeto.view.nativo.cadastros;

import java.awt.Component;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.projeto.controller.enums.EAbas;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.controller.interfaces.ITabela;
import com.projeto.model.tabelas.CentroCustos;
import com.projeto.model.tabelas.Usuarios;
import com.projeto.view.nativo.padrao.AbstractPadraoUI;
import com.projeto.view.nativo.padrao.IPadraoUI;

public class CentroCustosUI extends AbstractPadraoUI implements IPadraoUI {
	// Nome text, Titulo label, Valor padrão, Tipo do componente	
	String[][] componentes = {{"Codigo", "Código", "0", "text"}, 
							  {"Descricao", "Descrição", "", "text"},
							  {"Situacao", "Situação", "", "situacao"}};
	
	CentroCustos centroCustos = new CentroCustos();
	
	@Override
	public void prepararComponentes() {
		adicionarComponente(componentes);
	}

	@Override
	public String getNomeTela() {
		return EAbas.CENTRO_DE_CUSTOS.getNome();
	}

	@Override
	public ITabela getTabela() {
		return centroCustos;
	}
	
	@Override
	public void setValoresObjeto() {
		centroCustos.setCodigoUsuario(Usuarios.CODIGO_USUARIO_ATIVO);
		JPanel componentes = getComponentes();
		for (int i = 0; i < componentes.getComponentCount(); i++) {
			try {
				switch (componentes.getComponent(i).getName()) {
				case "textFieldCodigo":
					centroCustos.setCodigo(Integer.valueOf(((JTextField) componentes.getComponent(i)).getText()));
					break;
				case "textFieldDescricao":
					centroCustos.setDescricao(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "comboBoxSituacao":
					@SuppressWarnings("unchecked")
					String valorSituacao = ((JComboBox<String>) componentes.getComponent(i)).getSelectedItem().toString();
					centroCustos.setSituacao(ESituacao.valueOf(valorSituacao));
					break;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void setValoresComponentes() {
		CentroCustos centroCustoFiltro = new CentroCustos();
		centroCustoFiltro.setCodigo(getTabela().getCodigo());
		
		for (CentroCustos centroCusto: FactoryNegocio.listar(centroCustoFiltro).values()) {
			for(Component componente: getComponentes().getComponents()) {
				switch (componente.getName()) {
				case "textFieldCodigo":
					((JTextField) componente).setText(centroCusto.getCodigo().toString());
					break;
				case "textFieldDescricao":
					((JTextField) componente).setText(centroCusto.getDescricao());
					break;
				case "comboBoxSituacao": {
					@SuppressWarnings("unchecked")
					JComboBox<String> listaSituacao = ((JComboBox<String>) componente);
					for (int i = 0; i < listaSituacao.getItemCount(); i++) {
						if (centroCusto.getSituacao().name().equals(listaSituacao.getItemAt(i))) {
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
		String [] colunas = {"Código", "Descrição", "Situação"};
		return colunas;
	}

	@Override
	public Object[][] getDadosGrid() {
		CentroCustos filtro = new CentroCustos();
		filtro.setCodigo(0);
		filtro.setCodigoUsuario(Usuarios.CODIGO_USUARIO_ATIVO);
		Map<Integer, CentroCustos> centroCustos = FactoryNegocio.listar(filtro);
		Object[][] dados = new Object[centroCustos.values().size()][getColunasGrid().length];
		
		Integer posicao = 0;
		for (CentroCustos centroCusto : centroCustos.values()) {
			dados[posicao][0] = centroCusto.getCodigo().toString();
			dados[posicao][1] = centroCusto.getDescricao();
			dados[posicao][2] = centroCusto.getSituacao().name();
			posicao++;
		}
		return dados;
	}
}
