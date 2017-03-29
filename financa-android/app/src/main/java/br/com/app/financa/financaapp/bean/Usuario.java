package br.com.app.financa.financaapp.bean;

/**
 * Created by jccf-note on 25/05/2016.
 */
public class Usuario extends Object implements IBean {
    public static final String NOME_TABELA = "usuario";
    private static Usuario usuarioLogado;
    private Integer codigo = 0;
    private String nome = "";
    private String email = "";
    private String senha = "";

    public Usuario() { }

    public Usuario(Integer codigo, String nome, String email, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        Usuario.usuarioLogado = usuarioLogado;
    }

    public static String getSqlCreateZero() {
        return "create table usuario ( " +
               " idusuario integer primary key autoincrement, " +
               " nome text, " +
               " email text, " +
               " senha text " +
               ")";
    }

    @Override
    public BeanAdapter getBeanAdapterConsulta() {
        BeanAdapter beanAdapter = new BeanAdapter();
        beanAdapter.setTitulo(getNome());
        beanAdapter.setSubTitulo(getEmail());
        beanAdapter.setValor("");

        return beanAdapter;
    }
}
