package com.projeto.model.tabelas;

import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.interfaces.ITabela;

/**
 * 
 * @author Claudio Costa
 * @table CCUSTOS
 * 
 */
public class CentroCustos implements ITabela {
	
	private Integer codigo;
	private Integer codigoUsuario;
	private String descricao;
	private ESituacao situacao = ESituacao.ATIVO;
	
	@Override
	public Integer getCodigo() {
		return codigo;
	}
	
	@Override
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}
	
	@Override
	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public ESituacao getSituacao() {
		return situacao;
	}
	
	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return String.format("Código: %d\nUsuário: %d\nDescrição: %s\nSituação: %s", 
				codigo, codigoUsuario, descricao, situacao);
	}

}
