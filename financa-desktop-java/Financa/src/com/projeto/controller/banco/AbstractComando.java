package com.projeto.controller.banco;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;

public abstract class AbstractComando {
	private ComandoSQL comando;
	private Integer indexParametro = 1;
	
	public AbstractComando() {
		comando = new ComandoSQL();
	}
	
	protected void setComando(String aComando) {
		comando.setComando(aComando);
	}
	
	protected void setParametro(Integer aValor) {
		comando.setParametro(indexParametro++, aValor);
	}
	
	protected void setParametro(Double aValor) {
		comando.setParametro(indexParametro++, aValor);
	}
	
	protected void setParametro(Character aValor) {
		comando.setParametro(indexParametro++, aValor);
	}
	
	protected void setParametro(String aValor) {
		comando.setParametro(indexParametro++, aValor);
	}
	
	protected void setParametro(Date aValor) {
		comando.setParametro(indexParametro++, aValor);
	}
	
	protected void setParametro(Timestamp aValor) {
		comando.setParametro(indexParametro++, aValor);
	}
	
	protected Boolean executarComando() {
		comando.execute();
		return !comando.comErro();
	}
	
	protected ResultSet executarConsulta() {
		return comando.executeConsulta();
	}
	
	protected String getMensagemErro() {
		return comando.getMensagemErro();
	}
	
}
