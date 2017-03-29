package com.projeto.model.tabelas;

import com.projeto.controller.enums.EPeriodicidade;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoFixo;
import com.projeto.controller.interfaces.ITabela;

/**
 * 
 * @author Claudio Costa
 * @table FIXOS
 *
 */
public class Fixos implements ITabela {
	
	private Integer codigo;
	private Integer codigoUsuario;
	private ETipoFixo tipoFixo = ETipoFixo.DESCONTO;
	private String descricao;
	private Double valor = 0.0;
	private EPeriodicidade periodicidade = EPeriodicidade.MENSAL;
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

	public ETipoFixo getTipoFixo() {
		return tipoFixo;
	}

	public void setTipoFixo(ETipoFixo tipoFixo) {
		this.tipoFixo = tipoFixo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public EPeriodicidade getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(EPeriodicidade periodicidade) {
		this.periodicidade = periodicidade;
	}

	public ESituacao getSituacao() {
		return situacao;
	}
	
	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return String.format("Código: %d\nUsuário: %d\nTipo fixo: %s\nDescrição: %s\nValor: %f\nPeriodicidade: %s\nSituação: %s", 
				codigo, codigoUsuario, tipoFixo, descricao, valor, periodicidade, situacao);
	}

}
