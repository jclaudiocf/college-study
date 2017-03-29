package com.projeto.view.nativo;

import javax.swing.SwingUtilities;

import com.projeto.view.nativo.padrao.AcessoUI;

public class Financa {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
            public void run() {
				new AcessoUI().mostrarAcesso();
            }
        });
	}
}
