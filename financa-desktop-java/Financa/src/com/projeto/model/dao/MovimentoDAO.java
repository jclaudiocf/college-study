package com.projeto.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.projeto.controller.banco.AbstractComando;
import com.projeto.controller.banco.data.FData;
import com.projeto.controller.enums.EBancoErro;
import com.projeto.controller.enums.EDecisao;
import com.projeto.controller.enums.ESituacao;
import com.projeto.controller.enums.ETipoMovimento;
import com.projeto.controller.interfaces.IDao;
import com.projeto.controller.throwables.BancoException;
import com.projeto.model.tabelas.Movimentos;

public class MovimentoDAO extends AbstractComando implements IDao<Movimentos> {

	@Override
	public void adicionar(Movimentos aMovimento) throws BancoException {
		setComando("INSERT INTO MOVIMENTOS(IDUSUARIO,IDCCUSTO,IDCONTA,IDFIXO,TIPO_MOVIMENTO,DESCRICAO,DATA_LANCAMENTO,VALOR,PARCELADO) VALUES(?,?,?,?,?,?,?,?,?)");
		setParametro(aMovimento.getCodigoUsuario());
		setParametro(aMovimento.getCodigoCentroCusto());
		setParametro(aMovimento.getCodigoConta());
		setParametro(aMovimento.getCodigoFixo());
		setParametro(aMovimento.getTipoMovimento().getCodigo());
		setParametro(aMovimento.getDescricao());
		setParametro(new FData(aMovimento.getDataLancamento()).getDataSQL());
		setParametro(aMovimento.getValor());
		setParametro(aMovimento.getParcelado().getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_INSERIR, getMensagemErro());
		}
	}

	@Override
	public void alterar(Movimentos aMovimento) throws BancoException {
		setComando("UPDATE MOVIMENTOS SET IDCCUSTO=?, IDCONTA=?, TIPO_MOVIMENTO=?, DESCRICAO=?, DATA_LANCAMENTO=?, VALOR=?, PARCELADO=?, ST=? WHERE IDMOVIMENTO=?");
		setParametro(aMovimento.getCodigoCentroCusto());
		setParametro(aMovimento.getCodigoConta());
		setParametro(aMovimento.getTipoMovimento().getCodigo());
		setParametro(aMovimento.getDescricao());
		setParametro(new FData(aMovimento.getDataLancamento()).getDataSQL());
		setParametro(aMovimento.getValor());
		setParametro(aMovimento.getParcelado().getCodigo());
		setParametro(aMovimento.getSituacao().getCodigo());
		setParametro(aMovimento.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_ALTERAR, getMensagemErro());
		}
	}

	@Override
	public void deletar(Movimentos aMovimento) throws BancoException {
		setComando("DELETE FROM MOVIMENTOS WHERE IDMOVIMENTO=?");
		setParametro(aMovimento.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_DELETAR, getMensagemErro());
		}
	}

	@Override
	public Map<Integer, Movimentos> listar() throws BancoException {
		return listar(null);
	}

	@Override
	public Map<Integer, Movimentos> listar(Movimentos aMovimentoFiltro) throws BancoException {
		Map<Integer, Movimentos> xLista = new HashMap<>();
		String xSqlWhere = " WHERE 1 = 1 ";
		
		if (aMovimentoFiltro instanceof Movimentos) {
			if (aMovimentoFiltro.getCodigo() > 0) {
				xSqlWhere += "AND IDMOVIMENTO=".concat(aMovimentoFiltro.getCodigo().toString());
			}
		}
		
		setComando("SELECT * FROM MOVIMENTOS".concat(xSqlWhere));
		ResultSet xConsulta = executarConsulta();
		
		try {
			while (xConsulta.next()) {
				Movimentos xMovimento = new Movimentos();
				xMovimento.setCodigo(xConsulta.getInt("IDMOVIMENTO"));
				xMovimento.setCodigoUsuario(xConsulta.getInt("IDUSUARIO"));
				xMovimento.setCodigoCentroCusto(xConsulta.getInt("IDCCUSTO"));
				xMovimento.setCodigoConta(xConsulta.getInt("IDCONTA"));
				xMovimento.setCodigoFixo(xConsulta.getInt("IDFIXO"));
				xMovimento.setTipoMovimento(ETipoMovimento.valueOf(ETipoMovimento.getNome(xConsulta.getInt("TIPO_MOVIMENTO"))));
				xMovimento.setDescricao(xConsulta.getString("DESCRICAO"));
				xMovimento.setDataLancamento(FData.toLocalDateTime(xConsulta.getDate("DATA_LANCAMENTO")));
				xMovimento.setValor(xConsulta.getDouble("VALOR"));
				xMovimento.setParcelado(EDecisao.valueOf(EDecisao.getNome(xConsulta.getInt("PARCELADO"))));
				xMovimento.setSituacao(ESituacao.valueOf(ESituacao.getNome(xConsulta.getInt("ST"))));
				
				xLista.put(xMovimento.getCodigo(), xMovimento);
			}
			
			xConsulta.close();
			return xLista;
		} catch (SQLException e) {
			throw new BancoException(EBancoErro.ERRO_AO_DELETAR, getMensagemErro());
		}
	}
}
