package com.projeto.controller.enums;

public enum ETipoFixo {

	PROVENTO(1), DESCONTO(2);
	
	private Integer tipo_fixo;
	
	private ETipoFixo(Integer aTipofixo) {
		this.tipo_fixo = aTipofixo;
	}
	
	public Integer getCodigo() {
		return this.tipo_fixo;
	}
	
	public static String getNome(Integer tipoFixo) {
		switch (tipoFixo) {
		default: 
			return "Nenhum";
		case 1: 
			return "PROVENTO";
		case 2:
			return "DESCONTO";
		}
	}
	
	public static String[] getNomes() {
		Integer count = ETipoFixo.values().length;
		String[] valores = new String[count];
		for (int i = 0; i < valores.length; i++) {
			valores[i] = ETipoFixo.getNome(i+1);
		}
		return valores;
	}
}
