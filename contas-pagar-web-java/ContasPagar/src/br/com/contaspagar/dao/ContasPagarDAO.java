package br.com.contaspagar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.contaspagar.conexao.ConexaoMySQL;
import br.com.contaspagar.entidade.ContasPagar;

public class ContasPagarDAO {
	ConexaoMySQL conexaoMySQL;
	
	public ContasPagarDAO() {
		conexaoMySQL = new ConexaoMySQL();
	}
	
	public Boolean inserir(ContasPagar conta) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("INSERT INTO contas_pagar(idcredor,descricao,valor_bruto,valor_desconto) values(?,?,?,?)");
			preparar.setInt(1, conta.getCodigoCredor());
			preparar.setString(2, conta.getDescricao());
			preparar.setDouble(3, conta.getValorBruto());
			preparar.setDouble(4, conta.getValorDesconto());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean alterar(ContasPagar conta) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("UPDATE contas_pagar SET idcredor=?, descricao=?, valor_bruto=?, valor_desconto=? WHERE idcontas_pagar=?");
			preparar.setInt(1, conta.getCodigoCredor());
			preparar.setString(2, conta.getDescricao());
			preparar.setDouble(3, conta.getValorBruto());
			preparar.setDouble(4, conta.getValorDesconto());
			preparar.setInt(5, conta.getCodigo());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean deletar(ContasPagar conta) {
		Connection conexao = conexaoMySQL.getConexao();
		try {
			PreparedStatement preparar = conexao.prepareStatement("UPDATE contas_pagar set status='D' WHERE idcontas_pagar=?");
			preparar.setInt(1, conta.getCodigo());
			
			preparar.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<ContasPagar> getLista() {
		return getLista(0);
	}
	
	public List<ContasPagar> getLista(Integer codigo) {
		List<ContasPagar> lista = new ArrayList<>();
		Connection conexao = conexaoMySQL.getConexao();
		
		String where = "";
		if (codigo > 0) {
			where = String.format("AND idcontas_pagar = %d", codigo);
		}
		String comando = String.format("SELECT * FROM contas_pagar WHERE status='A' %s", where);
		
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			ResultSet consulta = preparar.executeQuery();
			
			while (consulta.next()) {
				ContasPagar conta = new ContasPagar();
				conta.setCodigo(consulta.getInt("idcontas_pagar"));
				conta.setCodigoCredor(consulta.getInt("idcredor"));
				conta.setDescricao(consulta.getString("descricao"));
				conta.setValorBruto(consulta.getDouble("valor_bruto"));
				conta.setValorDesconto(consulta.getDouble("valor_desconto"));
				
				lista.add(conta);
			}
			
			conexaoMySQL.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}
