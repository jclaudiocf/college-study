package com.projeto.view.nativo.padrao;

import com.projeto.controller.interfaces.ITabela;

public interface IPadraoUI {
	void prepararComponentes();
	String getNomeTela();
	ITabela getTabela();
	void setValoresObjeto();
	void setValoresComponentes();
	String[] getColunasGrid();
	Object[][] getDadosGrid();
}
