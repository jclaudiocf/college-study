package br.com.app.financa.financaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.app.financa.financaapp.bean.Usuario;

/**
 * Created by jccf-note on 25/05/2016.
 */
public class UsuarioDao extends AbstractDao implements IDao<Usuario> {

    public UsuarioDao(Context contexto) {
        super(contexto);
    }

    @Override
    public void inserir(Usuario objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", objeto.getNome());
        valores.put("email", objeto.getEmail());
        valores.put("senha", objeto.getSenha());

        resultado = db.insert(Usuario.NOME_TABELA, null, valores);
        db.close();
    }

    @Override
    public void alterar(Usuario objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", objeto.getNome());
        valores.put("email", objeto.getEmail());
        valores.put("senha", objeto.getSenha());

        String where = String.format("idusuario=%d", objeto.getCodigo());

        db.update(Usuario.NOME_TABELA, valores, where, null);
        db.close();
    }

    @Override
    public void deletar(Usuario objeto) {
        db = banco.getReadableDatabase();

        String where = String.format("idusuario=%d", objeto.getCodigo());

        db.delete(Usuario.NOME_TABELA, where, null);
        db.close();
    }

    @Override
    public Usuario consultar(Usuario objeto) {
        return listar(objeto).get(0);
    }

    @Override
    public ArrayList<Usuario> listar(Usuario objetoFiltro) {
        Cursor cursor;
        String[] camposRetorno = {"idusuario", "nome", "email", "senha"};
        String camposWhere = "";
        String[] valoresWhere = null;
        ArrayList<Usuario> lstUsuarios = new ArrayList<>();

        if (objetoFiltro instanceof Usuario) {
            if (objetoFiltro.getCodigo() > 0) {
                camposWhere = "idusuario=? ";
                valoresWhere = new String[]{objetoFiltro.getCodigo().toString()};
            }
        }

        db = banco.getReadableDatabase();
        cursor = db.query(Usuario.NOME_TABELA, camposRetorno, camposWhere, valoresWhere, null, null, "idusuario asc");

        while (cursor.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setCodigo(cursor.getInt(cursor.getColumnIndex("idusuario")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));

            lstUsuarios.add(usuario);
        }

        cursor.close();
        return lstUsuarios;
    }

    @Override
    public boolean comErro() {
        // -1 significa que houve erro ao inserir o usuário
        return resultado == -1;
    }

    @Override
    public String getMensagemErro() {
        return "Erro ao gravar usuário";
    }

    public Boolean logar(Usuario usuario) {
        Boolean retorno = false;
        Cursor cursor;
        String[] camposRetorno = {"idusuario", "email", "nome", "senha"};
        String camposWhere = "email=? and senha=?";
        String[] valoresWhere = {usuario.getEmail(), usuario.getSenha()};

        db = banco.getReadableDatabase();
        cursor = db.query(Usuario.NOME_TABELA, camposRetorno, camposWhere, valoresWhere, null, null, "nome asc");

        while (cursor.moveToNext()) {
            Usuario usuarioLogado = new Usuario();
            usuarioLogado.setCodigo(cursor.getInt(cursor.getColumnIndex("idusuario")));
            usuarioLogado.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            usuarioLogado.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            usuarioLogado.setSenha(cursor.getString(cursor.getColumnIndex("senha")));

            Usuario.setUsuarioLogado(usuarioLogado);
            retorno = true;
        }

        db.close();
        return retorno;
    }
}
