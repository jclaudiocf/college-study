package com.projeto.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.projeto.controller.banco.AbstractComando;
import com.projeto.controller.enums.EBancoErro;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoConta;
import com.projeto.controller.interfaces.IDao;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.tabelas.Contas;

public class ContaDAO extends AbstractComando implements IDao<Contas> {

	@Override
	public void adicionar(Contas conta) throws BancoException {
		setComando("INSERT INTO CONTAS(IDUSUARIO,DESCRICAO,TIPO_CONTA,AGENCIA,DIGITO_AGENCIA,CONTA,DIGITO_CONTA,ENDERECO_AGENCIA,SALDO,ST) VALUES(?,?,?,?,?,?,?,?,?,?)");
		setParametro(conta.getCodigoUsuario());
		setParametro(conta.getDescricao());
		setParametro(conta.getTipoConta().getCodigo());
		setParametro(conta.getAgencia());
		setParametro(conta.getDigitoAgencia());
		setParametro(conta.getConta());
		setParametro(conta.getDigitoConta());
		setParametro(conta.getEnderecoAgencia());
		setParametro(conta.getSaldo());
		setParametro(conta.getSituacao().getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_INSERIR, getMensagemErro());
		}
	}
	
	@Override
	public void alterar(Contas conta) throws BancoException {
		setComando("UPDATE CONTAS SET DESCRICAO=?, TIPO_CONTA=?, AGENCIA=?, DIGITO_AGENCIA=?, CONTA=?, DIGITO_CONTA=?, ENDERECO_AGENCIA=?, SALDO=?, ST=? WHERE IDCONTA=?");
		setParametro(conta.getDescricao());
		setParametro(conta.getTipoConta().getCodigo());
		setParametro(conta.getAgencia());
		setParametro(conta.getDigitoAgencia());
		setParametro(conta.getConta());
		setParametro(conta.getDigitoConta());
		setParametro(conta.getEnderecoAgencia());
		setParametro(conta.getSaldo());
		setParametro(conta.getSituacao().getCodigo());
		setParametro(conta.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_ALTERAR, getMensagemErro());
		}
	}
	
	@Override
	public void deletar(Contas conta) throws BancoException {
		setComando("DELETE FROM CONTAS WHERE IDCONTA=?");
		setParametro(conta.getCodigo());

		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_DELETAR, getMensagemErro());
		}
	}
	
	@Override
	public Map<Integer, Contas> listar() throws BancoException {
		return listar(null);
	}
	
	@Override
	public Map<Integer, Contas> listar(Contas aContasFiltro) throws BancoException {
		Map<Integer, Contas> xLista = new HashMap<>();
		String xSqlWhere = " WHERE 1 = 1 ";
		
		if (aContasFiltro instanceof Contas) {
			if (aContasFiltro.getCodigo() > 0) {
				xSqlWhere += "AND IDCONTA=".concat(aContasFiltro.getCodigo().toString());
			} else if (aContasFiltro.getCodigoUsuario() > 0) {
				xSqlWhere += "AND IDUSUARIO=".concat(aContasFiltro.getCodigoUsuario().toString());
			}
		}
		
		setComando("SELECT * FROM CONTAS".concat(xSqlWhere));
		ResultSet xConsulta = executarConsulta();
		
		try {
			while (xConsulta.next()) {
				Contas xConta = new Contas();
				xConta.setCodigo(xConsulta.getInt("IDCONTA"));
				xConta.setCodigoUsuario(xConsulta.getInt("IDUSUARIO"));
				xConta.setDescricao(xConsulta.getString("DESCRICAO"));
				xConta.setTipoConta(ETipoConta.valueOf(ETipoConta.getNome(xConsulta.getInt("TIPO_CONTA"))));
				xConta.setAgencia(xConsulta.getString("AGENCIA"));
				xConta.setDigitoAgencia(xConsulta.getString("DIGITO_AGENCIA"));
				xConta.setConta(xConsulta.getString("CONTA"));
				xConta.setDigitoConta(xConsulta.getString("DIGITO_CONTA"));
				xConta.setEnderecoAgencia(xConsulta.getString("ENDERECO_AGENCIA"));
				xConta.setSaldo(xConsulta.getDouble("SALDO"));
				xConta.setSituacao(ESituacao.valueOf(ESituacao.getNome(xConsulta.getInt("ST"))));
				
				xLista.put(xConta.getCodigo(), xConta);
			}
			
			xConsulta.close();
			return xLista;
		} catch (SQLException e) {
			throw new BancoException(EBancoErro.ERRO_AO_CONSULTAR, getMensagemErro());
		}
	}
}
