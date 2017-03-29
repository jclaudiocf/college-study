package com.projeto.model.tabelas;

import java.time.LocalDateTime;

import com.projeto.controller.banco.data.FData;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoParcela;

/**
 * 
 * @author Claudio Costa
 * @table PARCELAS
 * 
 */
public class Parcelas {
	
	private Integer codigo;
	private Integer codigoMovimento;
	private Integer codigoMovimentoQuitacao = 0;
	private Integer codigoCentroCusto;
	private Integer codigoConta;
	private ETipoParcela tipoParcela = ETipoParcela.DEBITO_A_PRAZO;
	private String descricao;
	private LocalDateTime dataLancamento = new FData().getData();
	private LocalDateTime dataVencimento = new FData().getData();
	private LocalDateTime dataQuitacao = FData.getDataMinima();
	private Double valor = 0.0;
	private Integer numeroParcela = 0;
	private Integer totalParcela = 0;
	private ESituacao situacao = ESituacao.ATIVO;
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigoMovimento() {
		return codigoMovimento;
	}
	
	public void setCodigoMovimento(Integer codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}
	
	public Integer getCodigoMovimentoQuitacao() {
		return codigoMovimentoQuitacao;
	}
	
	public void setCodigoMovimentoQuitacao(Integer codigoMovimentoQuitacao) {
		this.codigoMovimentoQuitacao = codigoMovimentoQuitacao;
	}
	
	public Integer getCentroCCusto() {
		return codigoCentroCusto;
	}
	
	public void setCodigoCCusto(Integer codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}
	
	public Integer getCodigoConta() {
		return codigoConta;
	}
	
	public void setCodigoConta(Integer codigoConta) {
		this.codigoConta = codigoConta;
	}
	
	public ETipoParcela getTipoParcela() {
		return tipoParcela;
	}
	
	public void setTipoParcela(ETipoParcela tipoParcela) {
		this.tipoParcela = tipoParcela;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}
	
	public void setDataLancamento(LocalDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public LocalDateTime getDataQuitacao() {
		return dataQuitacao;
	}
	
	public void setDataQuitacao(LocalDateTime dataQuitacao) {
		this.dataQuitacao = dataQuitacao;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Integer getNumeroParcela() {
		return numeroParcela;
	}
	
	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	
	public Integer getTotalParcela() {
		return totalParcela;
	}
	
	public void setTotalParcela(Integer totalParcela) {
		this.totalParcela = totalParcela;
	}
	
	public ESituacao getSituacao() {
		return situacao;
	}
	
	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return String.format("Código: %d\nMovimento: %d\nMovimento quitação: %d\nCentro custo: %d\nConta: %d\nTipo parcela: %s\nDescrição: %s\nData lançamento: %s\nData vencimento: %s\nData quitação: %s\nValor: %f\nNúmero parcela: %d\nTotal parcela: %d\nSituação: %s", 
				codigo, codigoMovimento, codigoMovimentoQuitacao, codigoCentroCusto, codigoConta, tipoParcela, descricao, 
				dataLancamento, dataVencimento, dataQuitacao, valor, numeroParcela, totalParcela, situacao);
	}

}
