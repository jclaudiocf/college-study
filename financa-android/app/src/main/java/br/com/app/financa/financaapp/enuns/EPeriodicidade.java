package br.com.app.financa.financaapp.enuns;

/**
 * Created by jccf-note on 09/06/2016.
 */
public enum EPeriodicidade {
    SEMANAL, MENSAL, ANUAL;

    public static String[] getValores() {
        return new String[] {"Semanal", "Mensal", "Anual"};
    }
}
