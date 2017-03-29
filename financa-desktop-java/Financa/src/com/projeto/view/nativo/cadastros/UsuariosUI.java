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
import com.projeto.model.tabelas.Usuarios;
import com.projeto.view.nativo.padrao.AbstractPadraoUI;
import com.projeto.view.nativo.padrao.IPadraoUI;

public class UsuariosUI extends AbstractPadraoUI implements IPadraoUI {
	// Nome text, Titulo label, Valor padrão, Tipo do componente
	String[][] componentes = {{"Codigo", "Código", "0", "text"},
							  {"Nome", "Nome", "", "text"},
							  {"Login", "Login", "", "text"},
							  {"Senha", "Senha", "", "text"},
							  {"Situacao", "Situação", "", "situacao"}};
	
	Usuarios usuario = new Usuarios();
	
	@Override
	public void prepararComponentes() {
		adicionarComponente(componentes);
	}
	
	@Override
	public String getNomeTela() {
		return EAbas.USUARIOS.getNome();
	}

	@Override
	public ITabela getTabela() {
		return usuario;
	}

	@Override
	public void setValoresObjeto() {
		JPanel componentes = getComponentes();
		for (int i = 0; i < componentes.getComponentCount(); i++) {
			try {
				switch (componentes.getComponent(i).getName()) {
				case "textFieldCodigo":
					usuario.setCodigo(Integer.valueOf(((JTextField) componentes.getComponent(i)).getText()));
					break;
				case "textFieldNome":
					usuario.setNome(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "textFieldLogin":
					usuario.setLogin(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "textFieldSenha":
					usuario.setSenha(((JTextField) componentes.getComponent(i)).getText());
					break;
				case "comboBoxSituacao":
					@SuppressWarnings("unchecked")
					String valorSituacao = ((JComboBox<String>) componentes.getComponent(i)).getSelectedItem().toString();
					usuario.setSituacao(ESituacao.valueOf(valorSituacao));
					break;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void setValoresComponentes() {
		Usuarios usuarioFiltro = new Usuarios();
		usuarioFiltro.setCodigo(getTabela().getCodigo());
		
		for (Usuarios usuario: FactoryNegocio.listar(usuarioFiltro).values()) {
			for(Component componente: getComponentes().getComponents()) {
				switch (componente.getName()) {
					case "textFieldCodigo": 
						((JTextField) componente).setText(usuario.getCodigo().toString());
						break;
					case "textFieldNome": 
						((JTextField) componente).setText(usuario.getNome());
						break;
					case "textFieldLogin": 
						((JTextField) componente).setText(usuario.getLogin());
						break;
					case "textFieldSenha": 
						((JTextField) componente).setText(usuario.getSenha());
						break;
					case "comboBoxSituacao": {
						@SuppressWarnings("unchecked")
						JComboBox<String> listaSituacao = ((JComboBox<String>) componente);
						for (int i = 0; i < listaSituacao.getItemCount(); i++) {
							if (usuario.getSituacao().name().equals(listaSituacao.getItemAt(i))) {
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
		String [] colunas = {"Código", "Nome", "Login", "Situação"};
		return colunas;
	}

	@Override
	public Object[][] getDadosGrid() {
		Usuarios filtro = null;
		Map<Integer, Usuarios> usuarios = FactoryNegocio.listar(filtro);
		Object[][] dados = new Object[usuarios.values().size()][getColunasGrid().length];
		
		Integer posicao = 0;
		for (Usuarios usuario : usuarios.values()) {
			dados[posicao][0] = usuario.getCodigo().toString();
			dados[posicao][1] = usuario.getNome();
			dados[posicao][2] = usuario.getLogin();
			dados[posicao][3] = usuario.getSituacao().name();
			posicao++;
		}
		return dados;
	}
}
