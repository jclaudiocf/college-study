package com.projeto.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.projeto.controller.banco.AbstractComando;
import com.projeto.controller.banco.data.FData;
import com.projeto.controller.enums.EBancoErro;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoParcela;
import com.projeto.controller.interfaces.IDao;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.tabelas.Parcelas;

public class ParcelaDAO extends AbstractComando implements IDao<Parcelas> {

	@Override
	public void adicionar(Parcelas aParcela) throws BancoException {
		setComando("INSERT INTO PARCELAS(IDMOVIMENTO,IDMOVIMENTO_QUITACAO,IDCCUSTO,IDCONTA,TIPO_PARCELA,DESCRICAO,DATA_LANCAMENTO,DATA_VENCIMENTO,DATA_QUITACAO,VALOR,NUMERO_PARCELA,TOTAL_PARCELA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		setParametro(aParcela.getCodigoMovimento());
		setParametro(aParcela.getCodigoMovimentoQuitacao());
		setParametro(aParcela.getCentroCCusto());
		setParametro(aParcela.getCodigoConta());
		setParametro(aParcela.getTipoParcela().getCodigo());
		setParametro(aParcela.getDescricao());
		setParametro(new FData(aParcela.getDataLancamento()).getDataSQL());
		setParametro(new FData(aParcela.getDataVencimento()).getDataSQL());
		setParametro(new FData(aParcela.getDataQuitacao()).getDataSQL());
		setParametro(aParcela.getValor());
		setParametro(aParcela.getNumeroParcela());
		setParametro(aParcela.getTotalParcela());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_INSERIR, getMensagemErro());
		}
	}

	@Override
	public void alterar(Parcelas aParcela) throws BancoException {
		setComando("UPDATE PARCELAS SET IDCCUSTO=?, IDCONTA=?, TIPO_PARCELA=?, DESCRICAO=?, DATA_LANCAMENTO=?, DATA_VENCIMENTO=?, DATA_QUITACAO=?, VALOR=?, NUMERO_PARCELA=?, TOTAL_PARCELA=?, ST=?, WHERE IDPARCELA=?");
		setParametro(aParcela.getCentroCCusto());
		setParametro(aParcela.getCodigoConta());
		setParametro(aParcela.getTipoParcela().getCodigo());
		setParametro(aParcela.getDescricao());
		setParametro(new FData(aParcela.getDataLancamento()).getDataSQL());
		setParametro(new FData(aParcela.getDataVencimento()).getDataSQL());
		setParametro(new FData(aParcela.getDataQuitacao()).getDataSQL());
		setParametro(aParcela.getValor());
		setParametro(aParcela.getNumeroParcela());
		setParametro(aParcela.getTotalParcela());
		setParametro(aParcela.getSituacao().getCodigo());
		setParametro(aParcela.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_ALTERAR, getMensagemErro());
		}
	}

	@Override
	public void deletar(Parcelas aParcela) throws BancoException {
		setComando("DELETE FROM PARCELAS WHERE IDPARCELA=?");
		setParametro(aParcela.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_DELETAR, getMensagemErro());
		}
	}

	@Override
	public Map<Integer, Parcelas> listar() throws BancoException {
		return listar(null);
	}

	@Override
	public Map<Integer, Parcelas> listar(Parcelas aParcelaFiltro) throws BancoException {
		Map<Integer, Parcelas> xLista = new HashMap<>();
		String xSqlWhere = " WHERE 1 = 1 ";
		
		if (aParcelaFiltro instanceof Parcelas) {
			if (aParcelaFiltro.getCodigo() > 0) {
				xSqlWhere += "AND IDPARCELA=".concat(aParcelaFiltro.getCodigo().toString());
			}
		}
		
		setComando("SELECT * FROM PARCELAS".concat(xSqlWhere));
		ResultSet xConsulta = executarConsulta();
		
		try {
			while (xConsulta.next()) {
				Parcelas xCentroCusto = new Parcelas();
				xCentroCusto.setCodigo(xConsulta.getInt("IDPARCELA"));
				xCentroCusto.setCodigoMovimento(xConsulta.getInt("IDMOVIMENTO"));
				xCentroCusto.setCodigoMovimentoQuitacao(xConsulta.getInt("IDMOVIMENTO_QUITACAO"));
				xCentroCusto.setCodigoCCusto(xConsulta.getInt("IDCCUSTO"));
				xCentroCusto.setCodigoConta(xConsulta.getInt("IDCONTA"));
				xCentroCusto.setCodigoCCusto(xConsulta.getInt("IDCCUSTO"));
				xCentroCusto.setTipoParcela(ETipoParcela.valueOf(ETipoParcela.getNome(xConsulta.getInt("TIPO_PARCELA"))));
				xCentroCusto.setDescricao(xConsulta.getString("DESCRICAO"));
				xCentroCusto.setDataLancamento(FData.toLocalDateTime(xConsulta.getDate("DATA_LANCAMENTO")));
				xCentroCusto.setDataVencimento(FData.toLocalDateTime(xConsulta.getDate("DATA_VENCIMENTO")));
				xCentroCusto.setDataQuitacao(FData.toLocalDateTime(xConsulta.getDate("DATA_QUITACAO")));
				xCentroCusto.setValor(xConsulta.getDouble("VALOR"));
				xCentroCusto.setNumeroParcela(xConsulta.getInt("NUMERO_PARCELA"));
				xCentroCusto.setTotalParcela(xConsulta.getInt("TOTAL_PARCELA"));
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
