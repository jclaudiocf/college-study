package com.projeto.controller.enums;

public enum EDecisao {
	
	SIM(1), NAO(2);
	
	private Integer decisao;
	
	EDecisao(Integer decisao) {
		this.decisao = decisao;
	}
	
	public Integer getCodigo() {
		return decisao;
	}
	
	public static String getNome(Integer decisao) {
		switch (decisao) {
		case 1:
			return "SIM";
		case 2:
			return "NAO";
		default:
			return null;
		}
	}
	
	public static String[] getNomes() {
		Integer count = EDecisao.values().length;
		String[] valores = new String[count];
		for (int i = 0; i < valores.length; i++) {
			valores[i] = EDecisao.getNome(i+1);
		}
		return valores;
	}
}
