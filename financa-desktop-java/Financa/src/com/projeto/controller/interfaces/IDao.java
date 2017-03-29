package com.projeto.controller.interfaces;

import java.util.Map;

import com.projeto.controller.throwables.BancoException;

public interface IDao<T> {
	public void adicionar(T aObjeto) throws BancoException;
	public void alterar(T aObjeto) throws BancoException;
	public void deletar(T aObjeto) throws BancoException;
	public Map<Integer, T> listar() throws BancoException;
	public Map<Integer, T> listar(T aObjetoFiltro) throws BancoException;
}
