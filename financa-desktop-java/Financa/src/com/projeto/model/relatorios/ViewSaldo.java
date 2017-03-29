package com.projeto.model.relatorios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.projeto.controller.banco.AbstractComando;
import com.projeto.controller.enums.EBancoErro;
import com.projeto.controller.throwables.BancoException;

public class ViewSaldo extends AbstractComando {
	String[] colunas = {"Código", "Descrição", "Saldo"};
	Object[][] dados;
	
	public void consultar(Integer codigoConta) throws BancoException {
		String condicao = " WHERE 1=1";
		
		if (codigoConta > 0) {
			condicao += String.format(" AND CODIGO = %d", codigoConta);
		}
		
		setComando(String.format("SELECT CODIGO, DESCRICAO, SALDO FROM V_SALDO_CONTA %s", condicao));
		ResultSet consulta = executarConsulta();
		
		try {
			consulta.last();
			dados = new Object[consulta.getRow()][colunas.length];
			consulta.beforeFirst();
			
			while (consulta.next()) {
				Integer posicao = consulta.getRow() -1;
				dados[posicao][0] = consulta.getInt("CODIGO");
				dados[posicao][1] = consulta.getString("DESCRICAO");
				dados[posicao][2] = consulta.getString("SALDO");
			}
			
			consulta.close();
		} catch (SQLException e) {
			throw new BancoException(EBancoErro.ERRO_AO_CONSULTAR, getMensagemErro());
		}
	}
	
	public String[] getColunas() {
		return colunas;
	}
	
	public Object[][] getDados() {
		return dados;
	}
	
	public Map<String, GridSaldo> listar(Integer codigoConta) {
		Map<String, GridSaldo> lista = new HashMap<>();
		String condicao = " WHERE 1=1";
		
		if (codigoConta > 0) {
			condicao += String.format(" AND CODIGO = %d", codigoConta);
		}
		
		setComando(String.format("SELECT CODIGO, DESCRICAO, SALDO FROM V_SALDO_CONTA %s", condicao));
		ResultSet consulta = executarConsulta();
		
		try {
			while (consulta.next()) {
				GridSaldo grid = new GridSaldo(consulta.getString("CODIGO"), 
						consulta.getString("DESCRICAO"), 
						consulta.getString("SALDO"));
				
				lista.put(consulta.getString("CODIGO"), grid);
			}
			
			consulta.close();
			return lista;
		} catch (SQLException e) {
			return null;
		}
	}
}
