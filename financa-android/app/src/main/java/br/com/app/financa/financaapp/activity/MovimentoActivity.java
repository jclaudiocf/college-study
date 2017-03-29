package br.com.app.financa.financaapp.activity;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import br.com.app.financa.financaapp.R;
import br.com.app.financa.financaapp.adapters.ConsultaAdapter;
import br.com.app.financa.financaapp.bean.CentroCusto;
import br.com.app.financa.financaapp.bean.Conta;
import br.com.app.financa.financaapp.bean.Movimento;
import br.com.app.financa.financaapp.bean.Usuario;
import br.com.app.financa.financaapp.dao.CentroCustoDao;
import br.com.app.financa.financaapp.dao.ContaDao;
import br.com.app.financa.financaapp.dao.MovimentoDao;
import br.com.app.financa.financaapp.enuns.ETipoMovimento;

public class MovimentoActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;

    private static final int TELA_CONSULTA = 1;
    private static final int TELA_CADASTRO = 2;
    private static View rootView = null;
    private static ListView rootList = null;
    private static ArrayAdapter<Conta> arrayAdapterConta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimento);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(TELA_CONSULTA -1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movimento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (getArguments().getInt(ARG_SECTION_NUMBER) == TELA_CONSULTA) {
                rootList = new ListView(getContext());
                carregarLista(getContext());

                rootList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Movimento movimento = (Movimento) rootList.getAdapter().getItem(position);
                        mostrarDados(movimento);
                        mViewPager.setCurrentItem(TELA_CADASTRO -1);
                    }
                });

                return rootList;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == TELA_CADASTRO) {
                rootView = inflater.inflate(R.layout.fragment_movimento_cadastro, container, false);
                final Context contexto = getContext();

                final TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
                final TextView descricao = (TextView) rootView.findViewById(R.id.input_descricao);
                final TextView valor = (TextView) rootView.findViewById(R.id.input_valor);
                codigo.setEnabled(false);
                codigo.setText("0");

                ContaDao contaDao = new ContaDao(contexto);
                CentroCustoDao centroCustoDao = new CentroCustoDao(contexto);

                final Spinner conta = (Spinner) rootView.findViewById(R.id.spinner_conta);
                ArrayAdapter<Conta> arrayAdapterConta = new ArrayAdapter<Conta>(contexto,
                        android.R.layout.simple_spinner_dropdown_item, contaDao.listar(null));
                arrayAdapterConta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                conta.setAdapter(arrayAdapterConta);

                final Spinner centrocusto = (Spinner) rootView.findViewById(R.id.spinner_centro_custo);
                ArrayAdapter<CentroCusto> arrayAdapterCentroCusto = new ArrayAdapter<CentroCusto>(contexto,
                        android.R.layout.simple_spinner_dropdown_item, centroCustoDao.listar(null));
                arrayAdapterCentroCusto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                centrocusto.setAdapter(arrayAdapterCentroCusto);

                final Spinner tipoMovimento = (Spinner) rootView.findViewById(R.id.spinner_tipo_movimento);
                ArrayAdapter<String> arrayAdapterTipoMovimento = new ArrayAdapter<String>(contexto,
                        android.R.layout.simple_spinner_dropdown_item, ETipoMovimento.getValores());
                arrayAdapterTipoMovimento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tipoMovimento.setAdapter(arrayAdapterTipoMovimento);

                Button btnExcluir = (Button) rootView.findViewById(R.id.btnExcluir);
                btnExcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Movimento movimento = new Movimento();
                        movimento.setCodigoMovimento(Integer.valueOf(codigo.getText().toString()));

                        MovimentoDao movimentoDao = new MovimentoDao(contexto);
                        movimentoDao.deletar(movimento);

                        limparCamposTela();
                        carregarLista(contexto);

                        Toast.makeText(contexto, "Movimento deletado", Toast.LENGTH_LONG).show();
                    }
                });

                Button btnSalvar = (Button) rootView.findViewById(R.id.btnSalvar);
                btnSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validarDados()) {
                            return;
                        }

                        Integer codigoConta = ((Conta) conta.getAdapter().getItem(conta.getSelectedItemPosition())).getCodigoConta();
                        Integer codigoCusto = ((CentroCusto) centrocusto.getAdapter().getItem(centrocusto.getSelectedItemPosition())).getCodigoCentroCusto();

                        Movimento movimento = new Movimento();
                        movimento.setCodigoUsuario(Usuario.getUsuarioLogado().getCodigo());
                        movimento.setCodigoConta(codigoConta);
                        movimento.setCodigoCentroCusto(codigoCusto);
                        movimento.setDescricao(descricao.getText().toString());
                        movimento.setValor(Double.valueOf(valor.getText().toString()));

                        switch (tipoMovimento.getSelectedItemPosition()) {
                            case 0: movimento.setTipoMovimento(ETipoMovimento.CREDITO);
                                break;
                            case 1: movimento.setTipoMovimento(ETipoMovimento.DEBITO);
                                break;
                        }

                        MovimentoDao movimentoDao = new MovimentoDao(contexto);
                        if (codigo.getText().toString().equals("0")) {
                            movimentoDao.inserir(movimento);
                        } else {
                            movimentoDao.alterar(movimento);
                        }

                        limparCamposTela();
                        carregarLista(contexto);

                        Toast.makeText(contexto, "Movimento salvo", Toast.LENGTH_LONG).show();
                    }
                });

                return rootView;
            }

            return rootView;
        }
    }

    private static void mostrarDados(Movimento movimento) {
        TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
        TextView descricao = (TextView) rootView.findViewById(R.id.input_descricao);
        TextView valor = (TextView) rootView.findViewById(R.id.input_valor);
        Spinner tipoMovimento = (Spinner) rootView.findViewById(R.id.spinner_tipo_movimento);

        codigo.setText(movimento.getCodigoMovimento().toString());
        descricao.setText(movimento.getDescricao());
        valor.setText(movimento.getValor().toString());

        tipoMovimento.setSelection(movimento.getTipoMovimento().ordinal());
    }

    private static void carregarLista(Context contexto) {
        MovimentoDao movimentoDao = new MovimentoDao(contexto);
        ConsultaAdapter consultaAdapter = new ConsultaAdapter(contexto,
                movimentoDao.listar(null),
                R.layout.fragment_movimento_consulta);
        rootList.setAdapter(consultaAdapter);
    }

    private static void limparCamposTela() {
        TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
        TextView descricao = (TextView) rootView.findViewById(R.id.input_descricao);
        TextView valor = (TextView) rootView.findViewById(R.id.input_valor);
        Spinner tipoMovimento = (Spinner) rootView.findViewById(R.id.spinner_tipo_movimento);

        codigo.setText("0");
        descricao.setText("");
        valor.setText("");
        tipoMovimento.setSelection(0);
    }

    private static boolean validarDados() {
        boolean retorno = true;

        TextView descricao = (TextView) rootView.findViewById(R.id.input_descricao);
        TextView valor = (TextView) rootView.findViewById(R.id.input_valor);

        if (descricao.getText().toString().isEmpty()) {
            descricao.setError("descrição é obrigatória");
            retorno = false;
        }

        if (valor.getText().toString().isEmpty()) {
            valor.setError("valor é obrigatório");
            retorno = false;
        }

        return retorno;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Consulta";
                case 1:
                    return "Cadastro";
            }
            return null;
        }
    }
}
