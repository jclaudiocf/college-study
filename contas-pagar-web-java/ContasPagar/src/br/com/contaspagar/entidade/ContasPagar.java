package br.com.contaspagar.entidade;

public class ContasPagar {
	private Integer codigo;
	private Integer codigoCredor;
	private String descricao;
	private Double valorBruto;
	private Double valorDesconto;
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigoCredor() {
		return codigoCredor;
	}

	public void setCodigoCredor(Integer codigoCredor) {
		this.codigoCredor = codigoCredor;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValorBruto() {
		return valorBruto;
	}
	
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	
	public Double getValorDesconto() {
		return valorDesconto;
	}
	
	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	public Double getValorPago() {
		return valorBruto - valorDesconto;
	}
}
