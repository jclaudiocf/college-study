package com.projeto.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.projeto.controller.banco.AbstractComando;
import com.projeto.controller.enums.EBancoErro;
import com.projeto.controller.enums.EPeriodicidade;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoFixo;
import com.projeto.controller.interfaces.IDao;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.tabelas.Fixos;

public class FixoDAO extends AbstractComando implements IDao<Fixos> {

	@Override
	public void adicionar(Fixos aFixo) throws BancoException {
		setComando("INSERT INTO FIXOS(IDUSUARIO,TIPO_FIXO,DESCRICAO,VALOR,PERIODICIDADE,ST) VALUES(?,?,?,?,?,?)");
		setParametro(aFixo.getCodigoUsuario());
		setParametro(aFixo.getTipoFixo().getCodigo());
		setParametro(aFixo.getDescricao());
		setParametro(aFixo.getValor());
		setParametro(aFixo.getPeriodicidade().getCodigo());
		setParametro(aFixo.getSituacao().getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_INSERIR, getMensagemErro());
		}
	}

	@Override
	public void alterar(Fixos aFixo) throws BancoException {
		setComando("UPDATE FIXOS SET DESCRICAO=?, TIPO_FIXO=?, VALOR=?, PERIODICIDADE=?, ST=? WHERE IDFIXO=?");
		setParametro(aFixo.getDescricao());
		setParametro(aFixo.getTipoFixo().getCodigo());
		setParametro(aFixo.getValor());
		setParametro(aFixo.getPeriodicidade().getCodigo());
		setParametro(aFixo.getSituacao().getCodigo());
		setParametro(aFixo.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_ALTERAR, getMensagemErro());
		}
	}

	@Override
	public void deletar(Fixos aFixo) throws BancoException {
		setComando("DELETE FROM FIXOS WHERE IDFIXO =?");
		setParametro(aFixo.getCodigo());

		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_DELETAR, getMensagemErro());
		}
	}

	@Override
	public Map<Integer, Fixos> listar() throws BancoException {
		return listar(null);
	}

	@Override
	public Map<Integer, Fixos> listar(Fixos aFixoFiltro) throws BancoException {
		Map<Integer, Fixos> xLista = new HashMap<>();
		String xSqlWhere = " WHERE 1 = 1 ";
		
		if (aFixoFiltro instanceof Fixos) {
			if (aFixoFiltro.getCodigo() > 0) {
				xSqlWhere += "AND IDFIXO=".concat(aFixoFiltro.getCodigo().toString());
			} else if (aFixoFiltro.getCodigoUsuario() > 0) {
				xSqlWhere += "AND IDUSUARIO=".concat(aFixoFiltro.getCodigoUsuario().toString());
			}
		}
		
		setComando("SELECT * FROM FIXOS".concat(xSqlWhere));
		ResultSet xConsulta = executarConsulta();
		
		try {
			while (xConsulta.next()) {
				Fixos xCentroCusto = new Fixos();
				xCentroCusto.setCodigo(xConsulta.getInt("IDFIXO"));
				xCentroCusto.setCodigoUsuario(xConsulta.getInt("IDUSUARIO"));
				xCentroCusto.setDescricao(xConsulta.getString("DESCRICAO"));
				xCentroCusto.setTipoFixo(ETipoFixo.valueOf(ETipoFixo.getNome(xConsulta.getInt("TIPO_FIXO"))));
				xCentroCusto.setValor(xConsulta.getDouble("VALOR"));
				xCentroCusto.setPeriodicidade(EPeriodicidade.valueOf(EPeriodicidade.getNome(xConsulta.getInt("PERIODICIDADE"))));
				xCentroCusto.setSituacao(ESituacao.valueOf(ESituacao.getNome(xConsulta.getInt("ST"))));
				
				xLista.put(xCentroCusto.getCodigo(), xCentroCusto);
			}
			
			xConsulta.close();
			return xLista;
		} catch (SQLException e) {
			throw new BancoException(EBancoErro.ERRO_AO_DELETAR, getMensagemErro());
		}
	}
}
