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
import com.projeto.model.tabelas.Usuarios;

public class UsuarioDAO extends AbstractComando implements IDao<Usuarios> {
	
	@Override
	public void adicionar(Usuarios usuario) throws BancoException {
		setComando("INSERT INTO USUARIOS(NOME,LOGIN,SENHA,ST) VALUES(?,?,?,?)");
		setParametro(usuario.getNome());
		setParametro(usuario.getLogin());
		setParametro(usuario.getSenha());
		setParametro(usuario.getSituacao().getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_INSERIR, getMensagemErro());
		}
	}

	@Override
	public void alterar(Usuarios usuario) throws BancoException {
		setComando("UPDATE USUARIOS SET NOME=?,LOGIN=?,SENHA=?,ST=? WHERE IDUSUARIO=?");
		setParametro(usuario.getNome());
		setParametro(usuario.getLogin());
		setParametro(usuario.getSenha());
		setParametro(usuario.getSituacao().getCodigo());
		setParametro(usuario.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_ALTERAR, getMensagemErro());
		}
	}

	@Override
	public void deletar(Usuarios usuario) throws BancoException {
		setComando("DELETE FROM USUARIOS WHERE IDUSUARIO=?");
		setParametro(usuario.getCodigo());
		
		if (!executarComando()) {
			throw new BancoException(EBancoErro.ERRO_AO_DELETAR, getMensagemErro());
		}
	}

	@Override
	public Map<Integer, Usuarios> listar() throws BancoException {
		return listar(null);
	}

	@Override
	public Map<Integer, Usuarios> listar(Usuarios usuarioFiltro) throws BancoException {
		Map<Integer, Usuarios> xLista = new HashMap<>();
		String xSqlWhere = " WHERE 1 = 1 ";
		
		if (usuarioFiltro instanceof Usuarios) {
			if (usuarioFiltro.getCodigo() > 0) {
				xSqlWhere += "AND IDUSUARIO=".concat(usuarioFiltro.getCodigo().toString());
			}
		}
		
		setComando("SELECT * FROM USUARIOS".concat(xSqlWhere));
		ResultSet xConsulta = executarConsulta();
		
		try {
			while (xConsulta.next()) {
				Usuarios xUsuario = new Usuarios();
				xUsuario.setCodigo(xConsulta.getInt("IDUSUARIO"));
				xUsuario.setNome(xConsulta.getString("NOME"));
				xUsuario.setLogin(xConsulta.getString("LOGIN"));
				xUsuario.setSenha(xConsulta.getString("SENHA"));
				xUsuario.setSituacao(ESituacao.valueOf(ESituacao.getNome(xConsulta.getInt("ST"))));
				
				xLista.put(xUsuario.getCodigo(), xUsuario);
			}
			xConsulta.close();
			return xLista;
		} catch (Exception e) {
			throw new BancoException(EBancoErro.ERRO_AO_CONSULTAR, getMensagemErro());
		}
	}
	
	public Boolean login(String login, String senha) {
		String comandoSQl = String.format("SELECT IDUSUARIO FROM USUARIOS WHERE LOGIN='%s' AND SENHA='%s' AND ST=1", login, senha);
		
		setComando(comandoSQl);
		ResultSet xConsulta = executarConsulta();
		
		try {
			while (xConsulta.next()) {
				Usuarios.CODIGO_USUARIO_ATIVO = xConsulta.getInt("IDUSUARIO");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				xConsulta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
