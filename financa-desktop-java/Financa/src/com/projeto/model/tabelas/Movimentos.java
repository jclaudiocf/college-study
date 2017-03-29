package com.projeto.model.tabelas;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.projeto.controller.banco.data.FData;
import com.projeto.controller.enums.EDecisao;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoMovimento;
import com.projeto.controller.interfaces.ITabela;

/**
 * 
 * @author Claudio Costa
 * @table MOVIMENTOS
 * 
 */
public class Movimentos implements ITabela {
	
	private Integer codigo;
	private Integer codigoUsuario;
	private Integer codigoCentroCusto;
	private Integer codigoConta;
	private Integer codigoFixo = 0;
	private ETipoMovimento tipoMovimento = ETipoMovimento.DEBITO_A_VISTA;
	private String descricao;
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDateTime dataLancamento = new FData().getData();
	private Double valor = 0.0;
	private EDecisao parcelado = EDecisao.NAO;
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

	public Integer getCodigoCentroCusto() {
		return codigoCentroCusto;
	}
	
	public void setCodigoCentroCusto(Integer codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}
	
	public Integer getCodigoConta() {
		return codigoConta;
	}
	
	public void setCodigoConta(Integer codigoConta) {
		this.codigoConta = codigoConta;
	}
	
	public Integer getCodigoFixo() {
		return codigoFixo;
	}

	public void setCodigoFixo(Integer codigoFixo) {
		this.codigoFixo = codigoFixo;
	}

	public ETipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}
	
	public void setTipoMovimento(ETipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
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
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public EDecisao getParcelado() {
		return parcelado;
	}
	
	public void setParcelado(EDecisao parcelado) {
		this.parcelado = parcelado;
	}
	
	public ESituacao getSituacao() {
		return situacao;
	}
	
	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return String.format("Código: %d\nUsuário: %d\nCentro custo: %d\nConta: %d\nFixo: %d\nTipo movimento: %s\nDescrição: %s\nData lançamento: %s\nValor: %f\nParcelado: %s\nSituação: %s", 
				codigo, codigoUsuario, codigoCentroCusto, codigoConta, codigoFixo, tipoMovimento, descricao, dataLancamento, valor, parcelado, situacao);
	}

}
