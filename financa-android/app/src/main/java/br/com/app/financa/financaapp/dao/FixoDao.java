package br.com.app.financa.financaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.app.financa.financaapp.bean.Fixo;
import br.com.app.financa.financaapp.enuns.EPeriodicidade;
import br.com.app.financa.financaapp.enuns.ETipoMovimento;

/**
 * Created by jccf-note on 09/06/2016.
 */
public class FixoDao extends AbstractDao implements IDao<Fixo> {

    public FixoDao(Context contexto) {
        super(contexto);
    }

    @Override
    public void inserir(Fixo objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("idusuario", objeto.getCodigoUsuario());
        valores.put("tipo_movimento", objeto.getTipoMovimento().ordinal());
        valores.put("periodicidade", objeto.getPeriodicidade().ordinal());
        valores.put("descricao", objeto.getDescricao());
        valores.put("valor", objeto.getValor());

        resultado = db.insert(Fixo.NOME_TABELA, null, valores);
        db.close();
    }

    @Override
    public void alterar(Fixo objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("tipo_movimento", objeto.getTipoMovimento().ordinal());
        valores.put("periodicidade", objeto.getPeriodicidade().ordinal());
        valores.put("descricao", objeto.getDescricao());
        valores.put("valor", objeto.getValor());

        String where = String.format("idfixo=%d", objeto.getCodigoFixo());

        db.update(Fixo.NOME_TABELA, valores, where, null);
        db.close();
    }

    @Override
    public void deletar(Fixo objeto) {
        db = banco.getReadableDatabase();

        String where = String.format("idfixo=%d", objeto.getCodigoFixo());

        db.delete(Fixo.NOME_TABELA, where, null);
        db.close();
    }

    @Override
    public Fixo consultar(Fixo objeto) {
        return listar(objeto).get(0);
    }

    @Override
    public ArrayList<Fixo> listar(Fixo objetoFiltro) {
        Cursor cursor;
        String[] camposRetorno = {"idfixo", "idusuario", "tipo_movimento", "periodicidade", "descricao", "valor"};
        String camposWhere = "";
        String[] valoresWhere =  null;
        ArrayList<Fixo> lstFixos = new ArrayList<>();

        if (objetoFiltro instanceof Fixo) {
            if (objetoFiltro.getCodigoFixo() > 0) {
                camposWhere = "idconta=? and idusuario=?";
                valoresWhere = new String[] {objetoFiltro.getCodigoFixo().toString(), objetoFiltro.getCodigoUsuario().toString()};
            }
        }

        db = banco.getReadableDatabase();
        cursor = db.query(Fixo.NOME_TABELA, camposRetorno, camposWhere, valoresWhere, null, null, "descricao asc");

        while (cursor.moveToNext()) {
            Fixo fixo = new Fixo();
            fixo.setCodigoFixo(cursor.getInt(cursor.getColumnIndex("idfixo")));
            fixo.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex("idusuario")));
            fixo.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            fixo.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));

            switch (cursor.getInt(cursor.getColumnIndex("tipo_movimento"))) {
                case 0:
                    fixo.setTipoMovimento(ETipoMovimento.CREDITO);
                    break;
                case 1:
                    fixo.setTipoMovimento(ETipoMovimento.DEBITO);
                    break;
            }

            switch (cursor.getInt(cursor.getColumnIndex("periodicidade"))) {
                case 0: fixo.setPeriodicidade(EPeriodicidade.SEMANAL);
                    break;
                case 1: fixo.setPeriodicidade(EPeriodicidade.MENSAL);
                    break;
                case 2: fixo.setPeriodicidade(EPeriodicidade.ANUAL);
                    break;
            }

            lstFixos.add(fixo);
        }

        db.close();
        return lstFixos;
    }

    @Override
    public boolean comErro() {
        // -1 significa que houve erro ao inserir o usuário
        return resultado == -1;
    }

    @Override
    public String getMensagemErro() {
        return "Erro ao gravar fixo";
    }
}
