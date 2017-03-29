package br.com.prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.prova.conexao.ConexaoMySQL;
import br.com.prova.entidade.Prova;

public class ProvaDAO {
	ConexaoMySQL conexaoMySQL;
	
	public ProvaDAO() {
		conexaoMySQL = new ConexaoMySQL();
	}
	
	public Boolean inserir(Prova prova) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("INSERT INTO cadastros(nome,data,chave,prova,observacao,imagem) values(?,?,?,?,?,?)");
			preparar.setString(1, prova.getNome());
			preparar.setTimestamp(2, Timestamp.valueOf(prova.getData()));
			preparar.setString(3, prova.getChave());
			preparar.setString(4, prova.getProva());
			preparar.setString(5, prova.getObservacao());
			preparar.setString(6, prova.getImagem());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean alterar(Prova prova) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("UPDATE cadastros SET nome=?, chave=?, prova=?, observacao=? WHERE idcadastro=?");
			preparar.setString(1, prova.getNome());
			preparar.setString(2, prova.getChave());
			preparar.setString(3, prova.getProva());
			preparar.setString(4, prova.getObservacao());
			preparar.setInt(5, prova.getCodigo());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean deletar(Prova prova) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("UPDATE cadastros set status='D' WHERE idcadastro=?");
			preparar.setInt(1, prova.getCodigo());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Prova> getLista() {
		return getLista(0);
	}
	
	public List<Prova> getLista(Integer codigoCadastro) {
		List<Prova> lista = new ArrayList<>();
		Connection conexao = conexaoMySQL.getConexao();
		
		String where = "";
		if (codigoCadastro > 0) {
			where = String.format("AND idcadastro = %d", codigoCadastro);
		}
		String comando = String.format("SELECT * FROM cadastros WHERE status='A' %s", where);
		
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			ResultSet consulta = preparar.executeQuery();
			
			while (consulta.next()) {
				Prova prova = new Prova();
				prova.setCodigo(consulta.getInt("idcadastro"));
				prova.setNome(consulta.getString("nome"));
				prova.setData(consulta.getTimestamp("data").toLocalDateTime());
				prova.setChave(consulta.getString("chave"));
				prova.setProva(consulta.getString("prova"));
				prova.setObservacao(consulta.getString("observacao"));
				prova.setImagem(consulta.getString("imagem"));
				
				lista.add(prova);
			}
			
			conexaoMySQL.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}
