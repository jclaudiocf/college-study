package com.projeto.view.nativo.ajuda;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.projeto.view.nativo.auxiliar.IconeFinanca;

public class SobreUI {
	JFrame frameSobre = new JFrame();
	JPanel painelSobre = new JPanel(new FlowLayout());
	
	public void mostrarSobre() {
		prepararSobre();
		prepararComponentes();
		
		frameSobre.setVisible(true);
	}
	
	public void prepararSobre() {
		frameSobre.setTitle("Sobre o sistema");
		frameSobre.setIconImage(IconeFinanca.getIconeImage());
		frameSobre.pack();
		frameSobre.setSize(280, 150);
		frameSobre.setResizable(false);
		frameSobre.setLocationRelativeTo(null);
		
		frameSobre.add(painelSobre);
	}
	
	private void prepararComponentes() {
		JLabel labelSobre = new JLabel("Gerenciador de Finança Pessoal");
		labelSobre.setFont(new Font("Serif", Font.BOLD, 15));
		labelSobre.setHorizontalAlignment(SwingConstants.CENTER);
		painelSobre.add(labelSobre);
		
		JLabel labelInformacoes = new JLabel("<html>Alessandra Pereira<br>Jose Claudio Costa Filho<br>Turma: INF26<br><br>Versão 1.0</html>");
		labelInformacoes.setHorizontalAlignment(SwingConstants.CENTER);
		painelSobre.add(labelInformacoes);
		
		JButton botao = new JButton("Ok");
		botao.setPreferredSize(new Dimension(100, 25));
		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameSobre.setVisible(false);
			}
		});
		painelSobre.add(botao);
	}
}
