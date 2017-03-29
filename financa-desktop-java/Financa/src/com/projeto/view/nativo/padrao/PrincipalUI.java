package com.projeto.view.nativo.padrao;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.projeto.controller.enums.EAbas;
import com.projeto.view.nativo.acao.AcaoMenu;
import com.projeto.view.nativo.auxiliar.IconeFinanca;
import com.projeto.view.nativo.auxiliar.SingletonAba;

public class PrincipalUI {
	JFrame framePrincipal = new JFrame();
	JPanel painelPrincipal = new JPanel();
	
	public void mostrarFinanca() {
		prepararFramePrincipal();
		prepararPainelPrincipal();
		
		preparaMenu();
		prepararAbas();
		
		framePrincipal.setVisible(true);
	}
	
	private void prepararFramePrincipal() {
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setTitle("Finança pessoal");
		framePrincipal.setIconImage(IconeFinanca.getIconeImage());
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(framePrincipal);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		framePrincipal.pack();
		framePrincipal.setSize(450, 500);
		framePrincipal.setResizable(false);
		framePrincipal.setLocationRelativeTo(null);
	}

	private void prepararPainelPrincipal() {
		painelPrincipal.setLayout(new CardLayout());
		framePrincipal.add(painelPrincipal);
	}
	
	private void preparaMenu() {
		JMenuBar menuPrincipal = new JMenuBar();
		
		JMenu menuCadastros = new JMenu("Cadastros");
		
		JMenuItem menuItemCadastroUsuario = new JMenuItem(EAbas.USUARIOS.getNome());
		menuItemCadastroUsuario.addActionListener(AcaoMenu.adicionarMenuUsuario());
		menuCadastros.add(menuItemCadastroUsuario);
		
		JMenuItem menuItemCadastroCentroCusto = new JMenuItem(EAbas.CENTRO_DE_CUSTOS.getNome());
		menuItemCadastroCentroCusto.addActionListener(AcaoMenu.adicionarMenuCentroCusto());
		menuCadastros.add(menuItemCadastroCentroCusto);
		
		JMenuItem menuItemCadastroConta = new JMenuItem(EAbas.CONTAS.getNome());
		menuItemCadastroConta.addActionListener(AcaoMenu.adicionarMenuConta());
		menuCadastros.add(menuItemCadastroConta);
		
		JMenuItem menuItemCadastroFixo = new JMenuItem(EAbas.FIXOS.getNome());
		menuItemCadastroFixo.addActionListener(AcaoMenu.adicionarMenuFixo());
		menuCadastros.add(menuItemCadastroFixo);
		
		JMenu menuLancamentos = new JMenu("Lançamentos");
		
		JMenuItem menuItemMovimento = new JMenuItem(EAbas.MOVIMENTO.getNome());
		menuItemMovimento.addActionListener(AcaoMenu.adicionarMenuMovimento());
		menuLancamentos.add(menuItemMovimento);
		
		JMenuItem menuItemQuitacao = new JMenuItem(EAbas.QUITAR_PARCELAS.getNome());
		//menuItemQuitacao.addActionListener(AcaoMenu.adicionarMenuMovimento());
		menuLancamentos.add(menuItemQuitacao);
		
		JMenuItem menuItemGerarFixos = new JMenuItem(EAbas.GERAR_FIXOS.getNome());
		//menuItemGerarFixos.addActionListener(AcaoMenu.adicionarMenuMovimento());
		menuLancamentos.add(menuItemGerarFixos);
		
		JMenu menuAjuda = new JMenu("Ajuda");
		
		JMenuItem menuItemManual = new JMenuItem(EAbas.MANUAL.getNome());
		menuItemManual.addActionListener(AcaoMenu.abrirManual());
		menuAjuda.add(menuItemManual);
		
		JMenuItem menuItemSobre = new JMenuItem(EAbas.SOBRE.getNome());
		menuItemSobre.addActionListener(AcaoMenu.abrirTelaSobre());
		menuAjuda.add(menuItemSobre);
		
		JMenu menuRelatorios = new JMenu("Relatórios");
		JMenu menuRelatoriosCadastros = new JMenu("Cadastros");
		JMenu menuRelatoriosLancamentos = new JMenu("Lançamentos");
		JMenu menuRelatoriosFinanceiro = new JMenu("Financeiro");
		
		menuRelatorios.add(menuRelatoriosCadastros);
		menuRelatorios.add(menuRelatoriosLancamentos);
		menuRelatorios.add(menuRelatoriosFinanceiro);
		
		JMenuItem menuItemRelatorioSaldo = new JMenuItem(EAbas.RELATORIO_SALDO.getNome());
		menuItemRelatorioSaldo.addActionListener(AcaoMenu.adicionarMenuRelatorioSaldo());
		menuRelatoriosFinanceiro.add(menuItemRelatorioSaldo);
		
		JMenuItem menuItemRelatorioExtrato = new JMenuItem(EAbas.RELATORIO_EXTRATO.getNome());
		//menuItemRelatorioExtrato.addActionListener(AcaoMenu.adicionarMenuMovimento());
		menuRelatoriosFinanceiro.add(menuItemRelatorioExtrato);
		
		JMenu menuSair = new JMenu("Sair");
		
		JMenuItem menuItemSair = new JMenuItem("Sair do sistema");
		menuItemSair.addActionListener(AcaoMenu.adicionarMenuSair());
		menuSair.add(menuItemSair);
		
		menuPrincipal.add(menuCadastros);
		menuPrincipal.add(menuLancamentos);
		menuPrincipal.add(menuRelatorios);
		menuPrincipal.add(menuAjuda);
		menuPrincipal.add(menuSair);
		
		framePrincipal.setJMenuBar(menuPrincipal);
	}
	
	private void prepararAbas() {
		painelPrincipal.add(SingletonAba.getAbas());
	}
}
