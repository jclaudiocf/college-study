package br.com.app.financa.financaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.app.financa.financaapp.bean.Conta;
import br.com.app.financa.financaapp.bean.Usuario;
import br.com.app.financa.financaapp.enuns.ETipoConta;

/**
 * Created by jccf-note on 05/06/2016.
 */
public class ContaDao extends AbstractDao implements IDao<Conta> {

    public ContaDao(Context contexto) {
        super(contexto);
    }

    @Override
    public void inserir(Conta objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("idusuario", objeto.getCodigoUsuario());
        valores.put("descricao", objeto.getDescricao());
        valores.put("tipo_conta", objeto.getTipoConta().ordinal());
        valores.put("saldo", objeto.getSaldo().toString());

        resultado = db.insert(Conta.NOME_TABELA, null, valores);
        db.close();
    }

    @Override
    public void alterar(Conta objeto) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("descricao", objeto.getDescricao());
        valores.put("tipo_conta", objeto.getTipoConta().ordinal());
        valores.put("saldo", objeto.getSaldo());

        String where = String.format("idconta=%d", objeto.getCodigoConta());

        db.update(Conta.NOME_TABELA, valores, where, null);
        db.close();
    }

    @Override
    public void deletar(Conta objeto) {
        db = banco.getReadableDatabase();

        String where = String.format("idconta=%s", objeto.getCodigoConta());

        db.delete(Conta.NOME_TABELA, where, null);
        db.close();
    }

    @Override
    public Conta consultar(Conta objeto) {
        return listar(objeto).get(0);
    }

    @Override
    public ArrayList<Conta> listar(Conta objetoFiltro) {
        Cursor cursor;
        String[] camposRetorno = {"idconta", "idusuario", "descricao", "tipo_conta", "saldo"};
        String camposWhere = "";
        String[] valoresWhere = null;
        ArrayList<Conta> lstContas = new ArrayList<>();

        if (objetoFiltro instanceof Conta) {
            if (objetoFiltro.getCodigoConta() > 0) {
                camposWhere = "idconta=? and idusuario=?";
                valoresWhere = new String[]{objetoFiltro.getCodigoConta().toString(), objetoFiltro.getCodigoUsuario().toString()};
            }
        }

        db = banco.getReadableDatabase();
        cursor = db.query(Conta.NOME_TABELA, camposRetorno, camposWhere, valoresWhere, null, null, "descricao asc");

        while (cursor.moveToNext()) {
            Conta conta = new Conta();
            conta.setCodigoConta(cursor.getInt(cursor.getColumnIndex("idconta")));
            conta.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex("idusuario")));
            conta.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            conta.setSaldo(cursor.getDouble(cursor.getColumnIndex("saldo")));

            switch (cursor.getInt(cursor.getColumnIndex("tipo_conta"))) {
                case 0: conta.setTipoConta(ETipoConta.CAIXA);
                    break;
                case 1: conta.setTipoConta(ETipoConta.BANCO);
                    break;
                case 2: conta.setTipoConta(ETipoConta.APLICACAO);
                    break;
            }

            lstContas.add(conta);
        }

        db.close();
        return lstContas;
    }

    @Override
    public boolean comErro() {
        // -1 significa que houve erro ao inserir o usuário
        return resultado == -1;
    }

    @Override
    public String getMensagemErro() {
        return "Erro ao gravar conta";
    }
}
