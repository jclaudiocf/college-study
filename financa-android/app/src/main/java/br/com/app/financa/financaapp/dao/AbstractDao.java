package br.com.app.financa.financaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.app.financa.financaapp.banco.Banco;

/**
 * Created by jccf-note on 26/05/2016.
 */
public abstract class AbstractDao {
    protected Context contexto;
    protected Banco banco;
    protected SQLiteDatabase db;
    protected ContentValues valores;
    protected Long resultado;

    public AbstractDao(Context contexto) {
        this.contexto = contexto;
        banco = new Banco(contexto);
    }
}
