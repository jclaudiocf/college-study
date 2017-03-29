package com.projeto.controller.enums;


public enum ESituacao {
	
	ATIVO(1), CANCELADO(2);
	
	private Integer situacao;
	
	ESituacao(Integer situacao) {
		this.situacao = situacao;
	}
	
	public Integer getCodigo() {
		return situacao;
	}
	
	public static String getNome(Integer situacao) {
		switch (situacao) {
		case 1:
			return "ATIVO";
		case 2:
			return "CANCELADO";
		default:
			return null;
		}
	}
	
	public static String[] getNomes() {
		Integer count = ESituacao.values().length;
		String[] valores = new String[count];
		for (int i = 0; i < valores.length; i++) {
			valores[i] = ESituacao.getNome(i+1);
		}
		return valores;
	}
}
