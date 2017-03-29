package br.com.app.financa.financaapp.bean;

/**
 * Created by jccf-note on 07/06/2016.
 */
public class CentroCusto extends Object implements IBean {
    public static final String NOME_TABELA = "CentroCusto";
    private Integer codigoCentroCusto = 0;
    private Integer codigoUsuario = 0;
    private String descricao = "";

    public Integer getCodigoCentroCusto() {
        return codigoCentroCusto;
    }

    public void setCodigoCentroCusto(Integer codigoCentroCusto) {
        this.codigoCentroCusto = codigoCentroCusto;
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

    public static String getSqlCreateZero() {
        return "create table centrocusto ( " +
               " idcentrocusto integer primary key autoincrement, " +
               " idusuario integer, " +
               " descricao text " +
               ")";
    }

    @Override
    public BeanAdapter getBeanAdapterConsulta() {
        BeanAdapter beanAdapter = new BeanAdapter();
        beanAdapter.setTitulo(getDescricao());
        beanAdapter.setSubTitulo("");
        beanAdapter.setValor("");

        return beanAdapter;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", getCodigoCentroCusto(), getDescricao());
    }
}
