package br.com.app.financa.financaapp.bean;

import br.com.app.financa.financaapp.enuns.ETipoMovimento;

/**
 * Created by jccf-note on 07/06/2016.
 */
public class Movimento extends Object implements IBean {
    public static final String NOME_TABELA = "Movimento";
    private Integer codigoMovimento = 0;
    private Integer codigoUsuario = 0;
    private Integer codigoConta = 0;
    private Integer codigoCentroCusto = 0;
    private String descricao = "";
    private Double valor = 0D;
    private ETipoMovimento tipoMovimento = ETipoMovimento.CREDITO;

    public Integer getCodigoMovimento() {
        return codigoMovimento;
    }

    public void setCodigoMovimento(Integer codigoMovimento) {
        this.codigoMovimento = codigoMovimento;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Integer getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(Integer codigoConta) {
        this.codigoConta = codigoConta;
    }

    public Integer getCodigoCentroCusto() {
        return codigoCentroCusto;
    }

    public void setCodigoCentroCusto(Integer codigoCentroCusto) {
        this.codigoCentroCusto = codigoCentroCusto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ETipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(ETipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public static String getSqlCreateZero() {
        return "create table movimento ( " +
               " idmovimento integer primary key autoincrement, " +
               " idusuario integer, " +
               " idconta integer, " +
               " idcentrocusto, " +
               " descricao text, " +
               " valor numeric, " +
               " tipo_movimento " +
               ")";
    }

    @Override
    public BeanAdapter getBeanAdapterConsulta() {
        BeanAdapter beanAdapter = new BeanAdapter();
        beanAdapter.setTitulo(getDescricao());
        beanAdapter.setSubTitulo(getTipoMovimento().toString());
        beanAdapter.setValor(getValor().toString());

        return beanAdapter;
    }
}
