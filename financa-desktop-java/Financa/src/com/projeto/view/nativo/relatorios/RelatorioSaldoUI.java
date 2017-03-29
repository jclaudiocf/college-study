package com.projeto.view.nativo.relatorios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.projeto.controller.throwables.BancoException;
import com.projeto.model.relatorios.ViewSaldo;
import com.projeto.view.nativo.auxiliar.IconeFinanca;

public class RelatorioSaldoUI {
	JFrame frameSaldo = new JFrame();
	JPanel painelFiltro = new JPanel(new FlowLayout());
	JPanel painelGrid = new JPanel(new BorderLayout());
	JPanel painelSaldo = new JPanel(new BorderLayout());
	ViewSaldo viewSaldo = new ViewSaldo();
	JScrollPane barraRolagem;
	
	public void mostrarTelaSaldo() {
		prepararSaldo();
		prepararComponentes();
		
		frameSaldo.setVisible(true);
	}
	
	private void prepararSaldo() {
		frameSaldo.setTitle("Relatório de saldo");
		frameSaldo.setIconImage(IconeFinanca.getIconeImage());
		frameSaldo.pack();
		frameSaldo.setSize(900, 500);
		frameSaldo.setLocationRelativeTo(null);
		
		frameSaldo.add(painelSaldo);
	}
	
	private void prepararComponentes() {
		JLabel labelConta = new JLabel("Conta: ");
		labelConta.setName("labelLabel");
		painelFiltro.add(labelConta);
		
		NumberFormat mascaraNumero = NumberFormat.getIntegerInstance();
		NumberFormatter mascaraNumerica = new NumberFormatter(mascaraNumero);
		mascaraNumerica.setAllowsInvalid(false);
		
		JFormattedTextField campoConta = new JFormattedTextField(0);
		campoConta.setPreferredSize(new Dimension(100, 25));
		campoConta.setFormatterFactory(new DefaultFormatterFactory(mascaraNumerica));
		painelFiltro.add(campoConta);
		
		JButton botaoMostrar = new JButton("Mostrar");
		botaoMostrar.setName("btnMostrar");
		botaoMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer codigoConta = Integer.parseInt(campoConta.getText());
				try {
					viewSaldo.consultar(codigoConta);
				} catch(BancoException be) {
					JOptionPane.showMessageDialog(frameSaldo, "Erro ao consultar saldo.", "Mensagem de erro", JOptionPane.OK_OPTION);
				}
				mostrarConsultaGrid();
			}
		});
		
		painelFiltro.add(botaoMostrar);
		
		painelSaldo.add(painelFiltro, BorderLayout.NORTH);
	}

	private void mostrarConsultaGrid() {
		TableModel modelo = new DefaultTableModel(viewSaldo.getDados(), viewSaldo.getColunas()){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        
        if (barraRolagem != null) {
        	painelGrid.remove(barraRolagem);
        }
        
        JTable tabela = new JTable(viewSaldo.getDados(), viewSaldo.getColunas());
	    tabela.setModel(modelo);

	    barraRolagem = new JScrollPane(tabela);
	    painelGrid.add(barraRolagem, BorderLayout.CENTER);
	    painelSaldo.add(painelGrid, BorderLayout.CENTER);
	    frameSaldo.setVisible(true);
	}
}
