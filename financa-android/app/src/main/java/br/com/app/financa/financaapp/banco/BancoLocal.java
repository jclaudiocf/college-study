package br.com.app.financa.financaapp.banco;

import android.content.SharedPreferences;

/**
 * Created by jccf-note on 09/06/2016.
 */
public class BancoLocal {
    public static final String NOME_APP = "PreferencesFinancaApp";
    public static final int MODE_MULTI_PROCESS = 4;
    private static SharedPreferences sharedPreferences;
    private static BancoLocal bancoLocal;

    public static void setInstancia(BancoLocal bancoLocal) {
        BancoLocal.bancoLocal = bancoLocal;
    }

    public static BancoLocal getInstancia() {
        return bancoLocal;
    }

    public BancoLocal(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void gravar(String chave, String valor) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(chave, valor);
        editor.commit();
    }

    public String consultar(String chave) {
        return sharedPreferences.getString(chave, " ");
    }
}
