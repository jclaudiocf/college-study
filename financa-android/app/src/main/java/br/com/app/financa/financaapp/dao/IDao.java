package br.com.app.financa.financaapp.dao;

import java.util.ArrayList;

/**
 * Created by jccf-note on 25/05/2016.
 */
public interface IDao<T> {
    public void inserir(T objeto);
    public void alterar(T objeto);
    public void deletar(T objeto);
    public T consultar(T objeto);
    public ArrayList<T> listar(T objetoFiltro);

    public boolean comErro();
    public String getMensagemErro();
}
