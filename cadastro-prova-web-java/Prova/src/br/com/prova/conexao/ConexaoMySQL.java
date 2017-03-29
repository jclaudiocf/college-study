package br.com.prova.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
	
	private final String URL_CONEXAO = "jdbc:mysql://%s:%s/%s";
	Connection conexao;

	public synchronized Connection getConexao() {
		String xUrl = String.format(URL_CONEXAO, "192.168.25.8", "3306", "prova");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(xUrl, "root", "123456");
			return conexao;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized void fecharConexao() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
