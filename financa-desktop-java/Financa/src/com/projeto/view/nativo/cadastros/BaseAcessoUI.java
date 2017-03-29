package com.projeto.view.nativo.cadastros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.projeto.controller.conexao.TesteConexao;
import com.projeto.controller.conexao.configuracao.ArquivoPropriedades;
import com.projeto.controller.conexao.configuracao.Configuracoes;
import com.projeto.view.nativo.acao.AcaoTela;
import com.projeto.view.nativo.auxiliar.IconeFinanca;
import com.projeto.view.nativo.padrao.AcessoUI;
import com.projeto.view.nativo.populador.PopuladorBase;

public class BaseAcessoUI {
	JFrame frameBaseAcesso = new JFrame();
	JPanel painelBaseAcesso = new JPanel();
	JPasswordField campoSenha = new JPasswordField(20);
	JTextField campoUsuario = new JTextField(20);
	JTextField campoPorta = new JTextField(20);
	JTextField campoHost = new JTextField(20);

	public void mostrarBaseAcesso() {
		prepararSistema();
		prepararComponentes();
		frameBaseAcesso.setVisible(true);
	}
	
	private void prepararSistema() {
		frameBaseAcesso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBaseAcesso.setTitle("Configurações da base");
		frameBaseAcesso.setIconImage(IconeFinanca.getIconeImage());
		frameBaseAcesso.setSize(280, 200);
		frameBaseAcesso.setResizable(false);
		frameBaseAcesso.setLocationRelativeTo(null);
		frameBaseAcesso.add(painelBaseAcesso);
	}
	
	private void prepararComponentes() {
		painelBaseAcesso.setLayout(null);
		
		JLabel labelUsuario = new JLabel("Usuário");
		labelUsuario.setName("labelLogin");
		labelUsuario.setBounds(10, 10, 80, 25);
		painelBaseAcesso.add(labelUsuario);
		
		campoUsuario.setName("textUsuario");
		campoUsuario.setText(Configuracoes.BANCO_USUARIO);
		campoUsuario.setBounds(100, 10, 160, 25);
		painelBaseAcesso.add(campoUsuario);
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setName("labelSenha");
		labelSenha.setBounds(10, 40, 80, 25);
		painelBaseAcesso.add(labelSenha);
		
		campoSenha.setName("passwordSenha");
		campoSenha.setText(Configuracoes.BANCO_SENHA);
		campoSenha.setBounds(100, 40, 160, 25);
		painelBaseAcesso.add(campoSenha);
		
		JLabel labelPorta = new JLabel("Porta");
		labelPorta.setName("labelPorta");
		labelPorta.setBounds(10, 70, 80, 25);
		painelBaseAcesso.add(labelPorta);
		
		campoPorta.setName("textUsuario");
		campoPorta.setText("3306");
		campoPorta.setBounds(100, 70, 160, 25);
		painelBaseAcesso.add(campoPorta);
		
		JLabel labelHost = new JLabel("Host");
		labelHost.setName("labelPorta");
		labelHost.setBounds(10, 100, 80, 25);
		painelBaseAcesso.add(labelHost);
		
		campoHost.setName("textUsuario");
		campoHost.setText(Configuracoes.BANCO_IP);
		campoHost.setBounds(100, 100, 160, 25);
		painelBaseAcesso.add(campoHost);
		
		JButton botaoFechar = new JButton("Fechar");
		botaoFechar.setBounds(10, 140, 90, 25);
		botaoFechar.addActionListener(AcaoTela.acaoSairFrame());
		painelBaseAcesso.add(botaoFechar);
		
		JButton botaoLogin = new JButton("Salvar e Testar");
		botaoLogin.setBounds(120, 140, 150, 25);
		botaoLogin.addActionListener(acaoBotaoSalvarTestar());
		painelBaseAcesso.add(botaoLogin);
	}
	
	private ActionListener acaoBotaoSalvarTestar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Properties propriedades = new Properties();
				propriedades.put("servidor.banco.homologado", "mysql");
				propriedades.put("servidor.banco.dados.ip", campoHost.getText());
				propriedades.put("servidor.banco.dados.porta", campoPorta.getText());
				propriedades.put("banco.dados.nome", "financa");
				propriedades.put("banco.dados.usuario.nome", campoUsuario.getText());
				propriedades.put("banco.dados.usuario.senha", new String(campoSenha.getPassword()));
				
				ArquivoPropriedades.salvarConfiguracoes(propriedades);
				if (TesteConexao.conexaoEstahAtiva()) {
					JOptionPane.showMessageDialog(painelBaseAcesso, "Conexão realizada com sucesso.", "Teste de conexão", JOptionPane.INFORMATION_MESSAGE);
					
					if (JOptionPane.showConfirmDialog(painelBaseAcesso, "Deseja popular a base de dados com informações de homologação?", "Populador de base", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						new PopuladorBase().popular();
						JOptionPane.showMessageDialog(painelBaseAcesso, "Base populada com sucesso.\nO usuário 'adm' com senha 'adm' foi criado para logon.", "Populador de base", JOptionPane.INFORMATION_MESSAGE);
					}
					
					SwingUtilities.invokeLater(new Runnable() {
						@Override
			            public void run() {
							frameBaseAcesso.setVisible(false);
							new AcessoUI().mostrarAcesso();
							return;
			            }
			        });
				}
				JOptionPane.showMessageDialog(painelBaseAcesso, "Conexão falhou, favor revise as configurações.", "Teste de conexão", JOptionPane.ERROR_MESSAGE);
			}
		};
	}
}
