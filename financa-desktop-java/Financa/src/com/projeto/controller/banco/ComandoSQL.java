package com.projeto.controller.banco;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.projeto.controller.factorys.FactoryConexao;

public class ComandoSQL {
	
	Connection conexao;
	PreparedStatement stmt;
	Boolean comErro = false;
	String mensagemErro;
	
	public ComandoSQL() {
		if (conexao == null) {
			conexao = new FactoryConexao().getConexao();
		}
	}
	
	public void setComando(String aSql) {
		try {
			stmt = conexao.prepareStatement(aSql);
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
	}
	
	public void setParametro(Integer aIndex, Integer aValor) {
		try {
			stmt.setInt(aIndex, aValor);
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
	}
	
	
	public void setParametro(Integer aIndex, Double aValor) {
		try {
			stmt.setDouble(aIndex, aValor);
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
	}

	public void setParametro(Integer aIndex, Character aValor) {
		try {
			stmt.setString(aIndex, aValor.toString());
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
	}
	
	public void setParametro(Integer aIndex, String aValor) {
		try {
			stmt.setString(aIndex, aValor);
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
	}
	
	public void setParametro(Integer aIndex, Date aValor) {
		try {
			stmt.setDate(aIndex, aValor);
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
	}
	
	public void setParametro(Integer aIndex, Timestamp aValor) {
		try {
			stmt.setTimestamp(aIndex, aValor);
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
	}
	
	public void execute() {
		try {
			stmt.execute();
			stmt.close();			
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
	}
	
	public ResultSet executeConsulta() {
		try {
			if (!comErro()) {
				return stmt.executeQuery();
			}
		} catch (SQLException e) {
			mensagemErro = e.getMessage();
			comErro = true;
		}
		return null;		
	}
	
	public Boolean comErro() {
		return comErro;
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}
}
