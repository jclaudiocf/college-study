package com.projeto.model.relatorios;

public class GridSaldo {
	private String codigo;
	private String descricao;
	private String valor;
	
	public GridSaldo() {
		this.codigo = "";
		this.descricao = "";
		this.valor = "";
	}
	
	public GridSaldo(String codigo, String descricao, String valor) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return String.format("Código: %s, descrição: %s, Valor: %s", 
				this.codigo, this.descricao, this.valor);
	}
}
