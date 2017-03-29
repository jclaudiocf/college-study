package com.projeto.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.projeto.controller.banco.AbstractComando;
import com.projeto.controller.enums.EBancoErro;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.interfaces.IDao;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.tabelas.CentroCustos;

public class CentroCustoDAO extends AbstractComando implements IDao<CentroCustos> {
	
	@Override
	public void adicionar(CentroCustos centroCusto) throws BancoException {
		setComando("INSERT INTO CCUSTOS(IDUSUARIO,DESCRICAO,ST) VALUES(?,?,?)");
		setParametro(centroCusto.getCodigoUsuario());
		setParametro(centroCusto.getDescricao());
		setParametro(centroCusto.getSituacao().getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_INSERIR, getMensagemErro());
		}
	}
	
	@Override
	public void alterar(CentroCustos centroCusto) throws BancoException {
		setComando("UPDATE CCUSTOS SET DESCRICAO=?, ST=? WHERE IDCCUSTO=?");
		setParametro(centroCusto.getDescricao());
		setParametro(centroCusto.getSituacao().getCodigo());
		setParametro(centroCusto.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_ALTERAR, getMensagemErro());
		}
	}
	
	@Override
	public void deletar(CentroCustos centroCusto) throws BancoException {
		setComando("DELETE FROM CCUSTOS WHERE IDCCUSTO=?");
		setParametro(centroCusto.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_DELETAR, getMensagemErro());
		}		
	}
	
	@Override
	public Map<Integer, CentroCustos> listar() throws BancoException {
		return listar(null);
	}

	@Override
	public Map<Integer, CentroCustos> listar(CentroCustos aCentroCustoFiltro) throws BancoException {
		Map<Integer, CentroCustos> xLista = new HashMap<>();
		String xSqlWhere = " WHERE 1 = 1 ";
		
		if (aCentroCustoFiltro instanceof CentroCustos) {
			if (aCentroCustoFiltro.getCodigo() > 0) {
				xSqlWhere += "AND IDCCUSTO=".concat(aCentroCustoFiltro.getCodigo().toString());
			} else if (aCentroCustoFiltro.getCodigoUsuario() > 0) {
				xSqlWhere += "AND IDUSUARIO=".concat(aCentroCustoFiltro.getCodigoUsuario().toString());
			}
		}
		
		setComando("SELECT * FROM CCUSTOS".concat(xSqlWhere));
		ResultSet xConsulta = executarConsulta();
		
		try {
			while (xConsulta.next()) {
				CentroCustos xCentroCusto = new CentroCustos();
				xCentroCusto.setCodigo(xConsulta.getInt("IDCCUSTO"));
				xCentroCusto.setCodigoUsuario(xConsulta.getInt("IDUSUARIO"));
				xCentroCusto.setDescricao(xConsulta.getString("DESCRICAO"));
				xCentroCusto.setSituacao(ESituacao.valueOf(ESituacao.getNome(xConsulta.getInt("ST"))));
				
				xLista.put(xCentroCusto.getCodigo(), xCentroCusto);
			}
			
			xConsulta.close();
			return xLista;
		} catch (SQLException e) {
			throw new BancoException(EBancoErro.ERRO_AO_CONSULTAR, getMensagemErro());
		}
	}
}
