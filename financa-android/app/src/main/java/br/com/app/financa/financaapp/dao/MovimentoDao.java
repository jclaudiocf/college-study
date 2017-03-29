package br.com.app.financa.financaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.app.financa.financaapp.bean.Movimento;
import br.com.app.financa.financaapp.enuns.ETipoMovimento;

/**
 * Created by jccf-note on 07/06/2016.
 */
public class MovimentoDao extends AbstractDao implements IDao<Movimento> {

    public MovimentoDao(Context contexto) {
        super(contexto);
    }

    @Override
    public void inserir(Movimento objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("idusuario", objeto.getCodigoUsuario());
        valores.put("idconta", objeto.getCodigoConta());
        valores.put("idcentrocusto", objeto.getCodigoCentroCusto());
        valores.put("descricao", objeto.getDescricao());
        valores.put("valor", objeto.getValor());
        valores.put("tipo_movimento", objeto.getTipoMovimento().ordinal());

        resultado = db.insert(Movimento.NOME_TABELA, null, valores);
        db.close();
    }

    @Override
    public void alterar(Movimento objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("idconta", objeto.getCodigoConta());
        valores.put("idcentrocusto", objeto.getCodigoCentroCusto());
        valores.put("descricao", objeto.getDescricao());
        valores.put("valor", objeto.getValor());
        valores.put("tipo_movimento", objeto.getTipoMovimento().ordinal());

        String where = String.format("idmovimento=%d", objeto.getCodigoMovimento());

        db.update(Movimento.NOME_TABELA, valores, where, null);
        db.close();
    }

    @Override
    public void deletar(Movimento objeto) {
        db = banco.getReadableDatabase();

        String where = String.format("idmovimento=%d", objeto.getCodigoMovimento());

        db.delete(Movimento.NOME_TABELA, where, null);
        db.close();
    }

    @Override
    public Movimento consultar(Movimento objeto) {
        return listar(objeto).get(0);
    }

    @Override
    public ArrayList<Movimento> listar(Movimento objetoFiltro) {
        Cursor cursor;
        String[] camposRetorno = {"idmovimento", "idusuario", "idconta", "idcentrocusto", "descricao", "valor", "tipo_movimento"};
        String camposWhere = "";
        String[] valoresWhere = null;
        ArrayList<Movimento> lstMovimentos = new ArrayList<>();

        if (objetoFiltro instanceof Movimento) {
            if (objetoFiltro.getCodigoMovimento() > 0) {
                camposWhere = "idmovimento=%d and idusuario=%d";
                valoresWhere = new String[]{objetoFiltro.getCodigoMovimento().toString(), objetoFiltro.getCodigoUsuario().toString()};
            }
        }

        db = banco.getReadableDatabase();
        cursor = db.query(Movimento.NOME_TABELA, camposRetorno, camposWhere, valoresWhere, null, null, "descricao asc");

        while (cursor.moveToNext()) {
            Movimento movimento = new Movimento();
            movimento.setCodigoMovimento(cursor.getInt(cursor.getColumnIndex("idmovimento")));
            movimento.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex("idusuario")));
            movimento.setCodigoConta(cursor.getInt(cursor.getColumnIndex("idconta")));
            movimento.setCodigoCentroCusto(cursor.getInt(cursor.getColumnIndex("idcentrocusto")));
            movimento.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            movimento.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));

            switch (cursor.getInt(cursor.getColumnIndex("tipo_movimento"))) {
                case 0: movimento.setTipoMovimento(ETipoMovimento.CREDITO);
                    break;
                case 1: movimento.setTipoMovimento(ETipoMovimento.DEBITO);
                    break;
            }

            lstMovimentos.add(movimento);
        }

        db.close();
        return lstMovimentos;
    }

    @Override
    public boolean comErro() {
        // -1 significa que houve erro ao inserir o usuário
        return resultado == -1;
    }

    @Override
    public String getMensagemErro() {
        return "Erro ao gravar movimento";
    }
}
