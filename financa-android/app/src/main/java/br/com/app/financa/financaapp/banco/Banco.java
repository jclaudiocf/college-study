package br.com.app.financa.financaapp.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.app.financa.financaapp.bean.CentroCusto;
import br.com.app.financa.financaapp.bean.Conta;
import br.com.app.financa.financaapp.bean.Fixo;
import br.com.app.financa.financaapp.bean.Movimento;
import br.com.app.financa.financaapp.bean.Usuario;

/**
 * Created by jccf-note on 26/05/2016.
 */
public class Banco extends SQLiteOpenHelper {
    private static final String BANCO_NOME = "financaapp.db";
    public static final Integer BANCO_VERSAO = 2;
    public static Boolean USUARIO_LOGADO = false;

    public Banco(Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria tabelas zeradas
        db.execSQL(Usuario.getSqlCreateZero());
        db.execSQL(Conta.getSqlCreateZero());
        db.execSQL(CentroCusto.getSqlCreateZero());
        db.execSQL(Movimento.getSqlCreateZero());
        db.execSQL(Fixo.getSqlCreateZero());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* Atualizou da versão 1 para 2
         * Rotina de FIXO implementada na versão 2
         **/
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL(Fixo.getSqlCreateZero());
        }
    }
}
