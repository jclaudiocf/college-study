package com.projeto.controller.enums;

public enum EBancoErro {
	
	ERRO_AO_INSERIR("Erro ao inserir."),
	ERRO_AO_ALTERAR("Erro ao atualizar."),
	ERRO_AO_DELETAR("Erro ao deletar."),
	ERRO_AO_CONSULTAR("Erro ao consultar.");
	
	final String descricao;
	
	private EBancoErro(String mensagem) {
		this.descricao = mensagem;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
