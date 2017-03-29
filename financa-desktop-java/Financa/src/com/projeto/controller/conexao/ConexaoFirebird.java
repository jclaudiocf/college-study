package com.projeto.controller.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.projeto.controller.interfaces.IConexao;

public class ConexaoFirebird implements IConexao {
	
	// "jdbc:firebirdsql:localhost/3050:C:/Personal/Workspaces/Financa/Financa/db/base.fdb"
	private final String URL_CONEXAO = "jdbc:firebirdsql:%s/%s:%s";
	Connection conexao;
	
	@Override	
	public Connection getConexao(Properties propriedades) {
		String xUrl = String.format(URL_CONEXAO, 
				propriedades.getProperty("servidor.banco.dados.ip"),
				propriedades.getProperty("servidor.banco.dados.porta"), 
				propriedades.getProperty("banco.dados.nome"));
		
		Properties xPropriedades = new Properties();
		xPropriedades.setProperty("user", propriedades.getProperty("banco.dados.usuario.nome"));
		xPropriedades.setProperty("password", propriedades.getProperty("banco.dados.usuario.senha"));
		
		try {
			conexao = DriverManager.getConnection(xUrl, xPropriedades);
			return conexao;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
