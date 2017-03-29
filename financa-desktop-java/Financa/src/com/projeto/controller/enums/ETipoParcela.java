package com.projeto.controller.enums;

public enum ETipoParcela {
	
	QUITADO(1), CREDITO_A_PRAZO(3), DEBITO_A_PRAZO(4);
	
	private Integer tipoParcela;
	
	ETipoParcela(Integer tipoParcela) {
		this.tipoParcela = tipoParcela;
	}
	
	public Integer getCodigo() {
		return tipoParcela;
	}

	public static String getNome(Integer tipoParcela) {
		switch (tipoParcela) {
		case 1:
			return "QUITADO";
		case 3:
			return "CREDITO_A_PRAZO";
		case 4:
			return "DEBITO_A_PRAZO";
		default:
			return null;
		}
	}
	
}
