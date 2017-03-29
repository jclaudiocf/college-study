package com.projeto.view.nativo.auxiliar;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SingletonAba {
	private static JTabbedPane abas = new JTabbedPane();
	
	public static void adicionarAba(JPanel painel, String nomeTela) {
		for (int i = 0; i < abas.getTabCount(); i++) {
			if (nomeTela.equals(abas.getTitleAt(i))) {
				abas.setSelectedIndex(i);
				return;
			}
		}
		abas.addTab(nomeTela, painel);
		adicionarAba(painel, nomeTela);
	}
	
	public static void removerAba(String nomeTela) {
		for (int i = 0; i < abas.getTabCount(); i++) {
			if (nomeTela.equals(abas.getTitleAt(i))) {
				abas.removeTabAt(i);
				return;
			}
		}
	}
	
	public static JTabbedPane getAbas() {
		return abas;
	}
}
