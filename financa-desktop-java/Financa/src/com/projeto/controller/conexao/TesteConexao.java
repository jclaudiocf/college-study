package com.projeto.controller.conexao;

import java.sql.Connection;
import java.sql.SQLException;

import com.projeto.controller.factorys.FactoryConexao;

public class TesteConexao {

	public static void main(String[] args) {
		if (conexaoEstahAtiva()) {
			System.out.println("Banco conectado.");
		} else {
			System.out.println("Banco não conectado.");
		}
	}
	
	public static Boolean conexaoEstahAtiva() {
		Connection conexao = new FactoryConexao().getConexao();
		Boolean retorno = false;
		try {
			retorno = conexao != null;
			if (retorno) {
				conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
