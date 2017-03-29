package com.projeto.model.tabelas;

import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoConta;
import com.projeto.controller.interfaces.ITabela;

/**
 * 
 * @author Claudio Costa
 * @table CONTAS
 * 
 */
public class Contas implements ITabela {
	private Integer codigo;
	private Integer codigoUsuario;
	private String descricao;
	private ETipoConta tipoConta = ETipoConta.BANCO;
	private String agencia;
	private String digitoAgencia;
	private String conta;
	private String digitoConta;
	private String enderecoAgencia;
	private Double saldo = 0.0;
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
	
	public ETipoConta getTipoConta() {
		return tipoConta;
	}
	
	public void setTipoConta(ETipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public String getAgencia() {
		return agencia;
	}
	
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	public String getDigitoAgencia() {
		return digitoAgencia;
	}
	
	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}
	
	public String getConta() {
		return conta;
	}
	
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	public String getDigitoConta() {
		return digitoConta;
	}
	
	public void setDigitoConta(String digitoConta) {
		this.digitoConta = digitoConta;
	}
	
	public String getEnderecoAgencia() {
		return enderecoAgencia;
	}
	
	public void setEnderecoAgencia(String enderecoAgencia) {
		this.enderecoAgencia = enderecoAgencia;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public ESituacao getSituacao() {
		return situacao;
	}
	
	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return String.format("Código: %d\nUsuário: %d\nDescrição: %s\nTipo conta: %s\nAgência: %s\nDigito agência: %s\nConta: %s\nDigito conta: %s\nEndereço: %s\nSaldo: %f\nSituação: %s", 
				codigo, codigoUsuario, descricao, tipoConta, agencia, digitoAgencia, conta, digitoConta, enderecoAgencia, saldo, situacao);
	}
}
