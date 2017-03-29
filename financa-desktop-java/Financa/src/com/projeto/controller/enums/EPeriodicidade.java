package com.projeto.controller.enums;

public enum EPeriodicidade {
	
	SEMANAL(1), MENSAL(2), ANUAL(3);
	
	private Integer periodicidade;
	
	EPeriodicidade(Integer periodicidade) {
		this.periodicidade = periodicidade;
	}
	
	public Integer getCodigo() {
		return periodicidade;
	}
	
	public static String getNome(Integer periodicidade) {
		switch (periodicidade) {
		case 1:
			return "SEMANAL";
		case 2:
			return "MENSAL";
		case 3:
			return "ANUAL";
		default:
			return null;
		}
	}
	
	public static String[] getNomes() {
		Integer count = EPeriodicidade.values().length;
		String[] valores = new String[count];
		for (int i = 0; i < valores.length; i++) {
			valores[i] = EPeriodicidade.getNome(i+1);
		}
		return valores;
	}
}
