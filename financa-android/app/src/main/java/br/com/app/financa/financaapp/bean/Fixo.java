package br.com.app.financa.financaapp.bean;

import br.com.app.financa.financaapp.enuns.EPeriodicidade;
import br.com.app.financa.financaapp.enuns.ETipoMovimento;

/**
 * Created by jccf-note on 09/06/2016.
 */
public class Fixo extends Object implements IBean {
    public static final String NOME_TABELA = "Fixo";
    private Integer codigoFixo = 0;
    private Integer codigoUsuario = 0;
    private String descricao = "";
    private ETipoMovimento tipoMovimento = ETipoMovimento.CREDITO;
    private EPeriodicidade periodicidade = EPeriodicidade.MENSAL;
    private Double valor = 0D;

    public Integer getCodigoFixo() {
        return codigoFixo;
    }

    public void setCodigoFixo(Integer codigoFixo) {
        this.codigoFixo = codigoFixo;
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

    public ETipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(ETipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public EPeriodicidade getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(EPeriodicidade periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public static String getSqlCreateZero() {
        return "create table fixo ( " +
               " idfixo integer primary key autoincrement, " +
               " idusuario integer, " +
               " descricao text, " +
               " tipo_movimento integer, " + /* 0: Crédito, 1: Débito */
               " periodicidade integer, " + /* 0: Semanal, 1: Mensal, 2: Anual */
               " valor numeric " +
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
