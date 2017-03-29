package com.projeto.controller.factorys;

import java.sql.Connection;

import com.projeto.controller.conexao.ConexaoFirebird;
import com.projeto.controller.conexao.ConexaoMySQL;
import com.projeto.controller.conexao.configuracao.ArquivoPropriedades;
import com.projeto.controller.enums.EBancosDeDados;

public class FactoryConexao {
	
	ArquivoPropriedades propriedades;
	EBancosDeDados banco;
	
	public FactoryConexao() {
		propriedades = new ArquivoPropriedades();
		banco = EBancosDeDados.MY_SQL;
	}
	
	public Connection getConexao() {
		switch (banco) {
			default: return null;
			case MY_SQL: return new ConexaoMySQL().getConexao(propriedades.getPropriedades());
			case FIREBIRD: return new ConexaoFirebird().getConexao(propriedades.getPropriedades());
		}
	}

}
