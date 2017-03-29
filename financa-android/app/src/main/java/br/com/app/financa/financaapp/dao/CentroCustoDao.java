package br.com.app.financa.financaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.app.financa.financaapp.bean.CentroCusto;

/**
 * Created by jccf-note on 07/06/2016.
 */
public class CentroCustoDao extends AbstractDao implements IDao<CentroCusto> {

    public CentroCustoDao(Context contexto) {
        super(contexto);
    }

    @Override
    public void inserir(CentroCusto objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("idusuario", objeto.getCodigoUsuario());
        valores.put("descricao", objeto.getDescricao());

        resultado = db.insert(CentroCusto.NOME_TABELA, null, valores);
        db.close();
    }

    @Override
    public void alterar(CentroCusto objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("descricao", objeto.getDescricao());

        String where = String.format("idcentrocusto=%d", objeto.getCodigoCentroCusto());

        db.update(CentroCusto.NOME_TABELA, valores, where, null);
        db.close();
    }

    @Override
    public void deletar(CentroCusto objeto) {
        db = banco.getReadableDatabase();

        String where = String.format("idcentrocusto=%d", objeto.getCodigoCentroCusto());

        db.delete(CentroCusto.NOME_TABELA, where, null);
        db.close();
    }

    @Override
    public CentroCusto consultar(CentroCusto objeto) {
        return listar(objeto).get(0);
    }

    @Override
    public ArrayList<CentroCusto> listar(CentroCusto objetoFiltro) {
        Cursor cursor;
        String[] camposRetorno = {"idcentrocusto", "idusuario", "descricao"};
        String camposWhere = "";
        String[] valoresWhere = null;
        ArrayList<CentroCusto> lstCentroCustos = new ArrayList<>();

        if (objetoFiltro instanceof CentroCusto) {
            if (objetoFiltro.getCodigoCentroCusto() > 0) {
                camposWhere = "idcentrocusto=? and idusuario=?";
                valoresWhere = new String[] {objetoFiltro.getCodigoCentroCusto().toString(), objetoFiltro.getCodigoUsuario().toString()};
            }
        }

        db = banco.getReadableDatabase();
        cursor = db.query(CentroCusto.NOME_TABELA, camposRetorno, camposWhere, valoresWhere, null, null, "descricao asc");

        while (cursor.moveToNext()) {
            CentroCusto centroCusto = new CentroCusto();
            centroCusto.setCodigoCentroCusto(cursor.getInt(cursor.getColumnIndex("idcentrocusto")));
            centroCusto.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex("idusuario")));
            centroCusto.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));

            lstCentroCustos.add(centroCusto);
        }

        db.close();
        return lstCentroCustos;
    }

    @Override
    public boolean comErro() {
        // -1 significa que houve erro ao inserir o usuário
        return resultado == -1;
    }

    @Override
    public String getMensagemErro() {
        return "Erro ao gravar centro de custo";
    }
}
