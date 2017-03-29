package br.com.app.financa.financaapp.enuns;

/**
 * Created by jccf-note on 05/06/2016.
 */
public enum ETipoConta {
    CAIXA, BANCO, APLICACAO;

    public static String[] getValores() {
        return new String[] {"Caixa", "Banco", "Aplicação"};
    }
}
