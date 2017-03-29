package com.projeto.controller.enums;

public enum EAbas {
	
	PRINCIPAL("Principal"), USUARIOS("Usuários"), CENTRO_DE_CUSTOS("Centro de custos"), CONTAS("Contas"), FIXOS("Fixos"),
	MOVIMENTO("Movimentos"), QUITAR_PARCELAS("Quitar parcelas"), GERAR_FIXOS("Gerar fixos"),
	MANUAL("Manual"), SOBRE("Sobre o sistema"),
	RELATORIO_SALDO("Saldo"), RELATORIO_EXTRATO("Extrato");
	
	final String nome;
	
	private EAbas(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}
