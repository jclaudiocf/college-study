package br.com.app.financa.financaapp.enuns;

/**
 * Created by jccf-note on 07/06/2016.
 */
public enum ETipoMovimento {
    CREDITO, DEBITO;

    public static String[] getValores() {
        return new String[] {"Crédito", "Débito"};
    }
}
