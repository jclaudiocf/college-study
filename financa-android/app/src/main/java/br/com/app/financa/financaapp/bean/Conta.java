package br.com.app.financa.financaapp.bean;

import br.com.app.financa.financaapp.enuns.ESimNao;
import br.com.app.financa.financaapp.enuns.ETipoConta;

/**
 * Created by jccf-note on 05/06/2016.
 */
public class Conta extends Object implements IBean {
    public static final String NOME_TABELA = "Conta";
    private Integer codigoConta = 0;
    private Integer codigoUsuario = 0;
    private String descricao = "";
    private ETipoConta tipoConta = ETipoConta.BANCO;
    private Double saldo = 0D;

    public Integer getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(Integer codigoConta) {
        this.codigoConta = codigoConta;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ETipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(ETipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public static String getSqlCreateZero() {
        return "create table conta ( " +
                " idconta integer primary key autoincrement, " +
                " idusuario integer, " +
                " descricao text, " +
                " tipo_conta integer, " + /* 0: Caixa, 1: Banco, 2: Aplicação */
                " saldo numeric " +
                ")";
    }

    @Override
    public BeanAdapter getBeanAdapterConsulta() {
        BeanAdapter beanAdapter = new BeanAdapter();
        beanAdapter.setTitulo(getDescricao());
        beanAdapter.setSubTitulo(getTipoConta().toString());
        beanAdapter.setValor(getSaldo().toString());

        return beanAdapter;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", getCodigoConta(), getDescricao());
    }
}
