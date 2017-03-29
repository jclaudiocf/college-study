package com.projeto.model.tabelas;

import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.interfaces.ITabela;

/**
 * 
 * @table USUARIOS
 * 
 */
public class Usuarios implements ITabela {
	
	private Integer codigo;
	private String nome;
	private String login;
	private String senha;
	private ESituacao situacao = ESituacao.ATIVO;
	public static Integer CODIGO_USUARIO_ATIVO;
	
	public Integer getCodigo() {
		return (codigo == null) ? 0 : codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public Integer getCodigoUsuario() {
		return this.getCodigo();
	}

	@Override
	public void setCodigoUsuario(Integer codigoUsuario) {
		this.setCodigo(codigoUsuario);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public ESituacao getSituacao() {
		return situacao;
	}
	
	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}
	
	@Override
	public String toString() {
		return String.format("Código: %d\nNome: %s\nLogin: %s\nSenha: %s\nSituação: %s", 
				codigo, nome, login, senha, situacao);
	}
}
