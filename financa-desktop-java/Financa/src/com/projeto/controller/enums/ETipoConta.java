package com.projeto.controller.enums;

public enum ETipoConta {	
	
	BANCO(1), CAIXA(2), APLICACAO(3);
	
	private Integer tipoConta;
	
	ETipoConta(Integer aTipoConta) {
		this.tipoConta = aTipoConta;
	}
	
	public Integer getCodigo() {
		return tipoConta;
	}
	
	public static String getNome(Integer aTipoConta) {
		switch (aTipoConta) {
		case 1:
			return "BANCO";
		case 2:
			return "CAIXA";
		case 3:
			return "APLICACAO";
		default:
			return null;
		}
	}
	
	public static String[] getNomes() {
		Integer count = ETipoConta.values().length;
		String[] valores = new String[count];
		for (int i = 0; i < valores.length; i++) {
			valores[i] = ETipoConta.getNome(i+1);
		}
		return valores;
	}
}
