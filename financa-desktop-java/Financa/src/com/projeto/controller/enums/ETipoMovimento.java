package com.projeto.controller.enums;

public enum ETipoMovimento {
	
	CREDITO_A_VISTA(1), DEBITO_A_VISTA(2), CREDITO_A_PRAZO(3), DEBITO_A_PRAZO(4), QUITACAO_DE_CREDITO(5), QUITACAO_DE_DEBITO(6);
	
	private Integer tipoMovimento;
	
	ETipoMovimento(Integer tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}
	
	public Integer getCodigo() {
		return tipoMovimento;
	}
	
	public static String getNome(Integer tipoMovimento) {
		switch (tipoMovimento) {
		case 1:
			return "CREDITO_A_VISTA";
		case 2:
			return "DEBITO_A_VISTA";
		case 3:
			return "CREDITO_A_PRAZO";
		case 4:
			return "DEBITO_A_PRAZO";
		case 5:
			return "QUITACAO_DE_CREDITO";
		case 6:
			return "QUITACAO_DE_DEBITO";
		}
		return null;
	}
	
	public static String[] getNomes() {
		Integer count = ETipoMovimento.values().length;
		String[] valores = new String[count];
		for (int i = 0; i < valores.length; i++) {
			valores[i] = ETipoMovimento.getNome(i+1);
		}
		return valores;
	}
}
