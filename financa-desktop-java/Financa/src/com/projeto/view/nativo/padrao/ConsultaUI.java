package com.projeto.view.nativo.padrao;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.projeto.view.nativo.auxiliar.IconeFinanca;

public class ConsultaUI {
	JFrame frameConsulta = new JFrame();
	JPanel painelFiltro = new JPanel(new BorderLayout());
	JPanel painelGrid = new JPanel(new BorderLayout());
	JPanel painelConsulta = new JPanel(new BorderLayout());
	
	public void mostrarTelaConsulta(Object[][] dados, String[] colunas, AbstractPadraoUI padraoUI, JTextField campoRetorno) {
		prepararConsulta(padraoUI);
		prepararComponentes();
		prepararGrid(dados, colunas, padraoUI, campoRetorno);
		
		frameConsulta.setVisible(true);
	}
	
	private void prepararConsulta(AbstractPadraoUI padraoUI) {
		frameConsulta.setTitle(String.format("Consulta %s", padraoUI.getNomeTela()));
		frameConsulta.setIconImage(IconeFinanca.getIconeImage());
		frameConsulta.pack();
		frameConsulta.setSize(900, 500);
		frameConsulta.setResizable(false);
		frameConsulta.setLocationRelativeTo(null);
		
		frameConsulta.add(painelConsulta);
	}
	
	private void prepararComponentes() {
		JLabel labelFiltro = new JLabel("Descrição: ");
		labelFiltro.setName("labelLabel");
		painelFiltro.add(labelFiltro, BorderLayout.WEST);
		
		JTextField campoFiltro = new JTextField("");
		campoFiltro.setName("textCampo");
		painelFiltro.add(campoFiltro, BorderLayout.CENTER);
		
		painelConsulta.add(painelFiltro, BorderLayout.NORTH);
	}
	
	private void prepararGrid(Object[][] dados, String[] colunas, AbstractPadraoUI padraoUI, JTextField campoRetorno) {
	    TableModel modelo = new DefaultTableModel(dados, colunas){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;  
            }  
        };
        
	    JTable tabela = new JTable(dados, colunas);
	    tabela.setModel(modelo);
	    tabela.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {  
	            if (e.getClickCount() == 2) {
	                Integer codigo = Integer.parseInt((String) tabela.getValueAt(tabela.getSelectedRow(), 0));
	                
	                if (campoRetorno != null) {
	                	campoRetorno.setText(codigo.toString());
	                } else {
		                padraoUI.getTabela().setCodigo(codigo);
		                padraoUI.setValoresComponentes();
		                padraoUI.ativarTodosComponentes(true);
		                padraoUI.ativarBotaoExcluir(true);
	                }
	                
	                frameConsulta.setVisible(false);
	            }
	        }
		});
	    
	    JScrollPane barraRolagem = new JScrollPane(tabela);
	    painelGrid.add(barraRolagem, BorderLayout.CENTER);
	    
	    painelConsulta.add(painelGrid, BorderLayout.CENTER);
	}
}
