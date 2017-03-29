package com.projeto.view.nativo.padrao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.projeto.controller.conexao.TesteConexao;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.view.nativo.acao.AcaoTela;
import com.projeto.view.nativo.auxiliar.IconeFinanca;
import com.projeto.view.nativo.cadastros.BaseAcessoUI;

public class AcessoUI {
	JFrame frameAcesso = new JFrame();
	JPanel painelAcesso = new JPanel();
	JTextField campoLogin = new JTextField(20);
	JPasswordField campoSenha = new JPasswordField(20);

	public void mostrarAcesso() {
		if (!TesteConexao.conexaoEstahAtiva()) {
			new BaseAcessoUI().mostrarBaseAcesso();
			return;
		}
		
		prepararAcesso();
		prepararComponentes();
		
		frameAcesso.setVisible(true);
	}
	
	private void prepararAcesso() {
		frameAcesso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAcesso.setTitle("Identificação");
		frameAcesso.setIconImage(IconeFinanca.getIconeImage());
		frameAcesso.pack();
		frameAcesso.setSize(280, 135);
		frameAcesso.setResizable(false);
		frameAcesso.setLocationRelativeTo(null);
		
		frameAcesso.add(painelAcesso);
	}
	
	private void prepararComponentes() {
		painelAcesso.setLayout(null);
		
		JLabel labelLogin = new JLabel("Login");
		labelLogin.setName("labelLogin");
		labelLogin.setBounds(10, 10, 80, 25);
		painelAcesso.add(labelLogin);
		
		campoLogin.setName("textLogin");
		campoLogin.setBounds(100, 10, 160, 25);
		painelAcesso.add(campoLogin);
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setName("labelSenha");
		labelSenha.setBounds(10, 40, 80, 25);
		painelAcesso.add(labelSenha);
		
		campoSenha.setName("passwordSenha");
		campoSenha.setBounds(100, 40, 160, 25);
		painelAcesso.add(campoSenha);
		
		JButton botaoFechar = new JButton("Fechar");
		botaoFechar.setBounds(10, 80, 90, 25);
		botaoFechar.addActionListener(AcaoTela.acaoSairFrame());
		painelAcesso.add(botaoFechar);
		
		JButton botaoLogin = new JButton("Logar");
		botaoLogin.setBounds(180, 80, 90, 25);
		botaoLogin.addActionListener(acaoBotaoLogin());
		painelAcesso.add(botaoLogin);
	}
	
	private ActionListener acaoBotaoLogin() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				if (usuarioDAO.login(campoLogin.getText(), new String(campoSenha.getPassword()))) {
					frameAcesso.setVisible(false);
					new PrincipalUI().mostrarFinanca();
					return;
				}
				JOptionPane.showMessageDialog(painelAcesso, "Dados de login não conferem.", "Falha de login", JOptionPane.ERROR_MESSAGE);
			}
		};
	}
}
