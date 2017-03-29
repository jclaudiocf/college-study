package br.com.contaspagar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.contaspagar.conexao.ConexaoMySQL;
import br.com.contaspagar.entidade.Credor;

public class CredorDAO {
ConexaoMySQL conexaoMySQL;
	
	public CredorDAO() {
		conexaoMySQL = new ConexaoMySQL();
	}
	
	public Boolean inserir(Credor credor) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("INSERT INTO credor(nome,endereco) values(?,?)");
			preparar.setString(1, credor.getNome());
			preparar.setString(2, credor.getEndereco());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean alterar(Credor credor) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("UPDATE credor SET nome=?, endereco=? WHERE idcredor=?");
			preparar.setString(1, credor.getNome());
			preparar.setString(2, credor.getEndereco());
			preparar.setInt(3, credor.getCodigo());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean deletar(Credor credor) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("UPDATE credor set status='D' WHERE idcredor=?");
			preparar.setInt(1, credor.getCodigo());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Credor> getLista() {
		return getLista(0);
	}
	
	public List<Credor> getLista(Integer codigo) {
		List<Credor> lista = new ArrayList<>();
		Connection conexao = conexaoMySQL.getConexao();
		
		String where = "";
		if (codigo > 0) {
			where = String.format("AND idcredor = %d", codigo);
		}
		String comando = String.format("SELECT * FROM credor WHERE status='A' %s", where);
		
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			ResultSet consulta = preparar.executeQuery();
			
			while (consulta.next()) {
				Credor conta = new Credor();
				conta.setCodigo(consulta.getInt("idcredor"));
				conta.setNome(consulta.getString("nome"));
				conta.setEndereco(consulta.getString("endereco"));
				
				lista.add(conta);
			}
			
			conexaoMySQL.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}
