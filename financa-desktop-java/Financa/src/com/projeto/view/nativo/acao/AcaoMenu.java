package com.projeto.view.nativo.acao;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JOptionPane;

import com.projeto.controller.enums.EAbas;
import com.projeto.view.nativo.ajuda.SobreUI;
import com.projeto.view.nativo.auxiliar.SingletonAba;
import com.projeto.view.nativo.cadastros.CentroCustosUI;
import com.projeto.view.nativo.cadastros.ContasUI;
import com.projeto.view.nativo.cadastros.FixosUI;
import com.projeto.view.nativo.cadastros.UsuariosUI;
import com.projeto.view.nativo.lancamentos.MovimentoUI;
import com.projeto.view.nativo.relatorios.RelatorioSaldoUI;

public class AcaoMenu {

	public static ActionListener adicionarMenuUsuario() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsuariosUI usuarioUI = new UsuariosUI();
				SingletonAba.adicionarAba(usuarioUI.getPainel(), EAbas.USUARIOS.getNome());
			}
		};
	}
	
	public static ActionListener adicionarMenuCentroCusto() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CentroCustosUI usuarioUI = new CentroCustosUI();
				SingletonAba.adicionarAba(usuarioUI.getPainel(), EAbas.CENTRO_DE_CUSTOS.getNome());
			}
		};
	}
	
	public static ActionListener adicionarMenuConta() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ContasUI contaUI = new ContasUI();
				SingletonAba.adicionarAba(contaUI.getPainel(), EAbas.CONTAS.getNome());
			}
		};
	}
	
	public static ActionListener adicionarMenuFixo() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FixosUI fixoUI = new FixosUI();
				SingletonAba.adicionarAba(fixoUI.getPainel(), EAbas.FIXOS.getNome());
			}
		};
	}
	
	public static ActionListener adicionarMenuMovimento() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MovimentoUI movimentoUI = new MovimentoUI();
				SingletonAba.adicionarAba(movimentoUI.getPainel(), EAbas.MOVIMENTO.getNome());
			}
		};
	}
	
	public static ActionListener abrirManual() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = null;  
				//Primeiro verificamos se é possível a integração com o desktop  
				if (!Desktop.isDesktopSupported()) {
				    JOptionPane.showMessageDialog(null, "Opção de manual não disponível.", "Mensagem erro", JOptionPane.OK_OPTION);
				    return;
				}
				  
				desktop = Desktop.getDesktop();  
				//Agora vemos se é possível disparar o browser default.  
				if (!desktop.isSupported(Desktop.Action.BROWSE)) {
					JOptionPane.showMessageDialog(null, "Navegador padrão não definido.", "Mensagem erro", JOptionPane.OK_OPTION);
				}
				  
				try {
					//Pega a URI de um componente de texto.
					URI link = new URI("https://docs.google.com/document/d/1RUCDtxuvswTeqTchd0uUSneF6IgflfnJH1aTg5jlOiQ/edit?usp=sharing");
					
					//Dispara o browser default, que pode ser o Explorer, Firefox ou outro.  
					desktop.browse(link); 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};
	}
	
	public static ActionListener abrirTelaSobre() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SobreUI().mostrarSobre();
			}
		};
	}
	
	public static ActionListener adicionarMenuRelatorioSaldo() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RelatorioSaldoUI().mostrarTelaSaldo();
			}
		};
	}
	
	public static ActionListener adicionarMenuSair() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
	}
}
