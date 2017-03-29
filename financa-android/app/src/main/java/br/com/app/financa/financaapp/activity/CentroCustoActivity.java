package br.com.app.financa.financaapp.activity;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.app.financa.financaapp.R;
import br.com.app.financa.financaapp.adapters.ConsultaAdapter;
import br.com.app.financa.financaapp.bean.CentroCusto;
import br.com.app.financa.financaapp.bean.Usuario;
import br.com.app.financa.financaapp.dao.CentroCustoDao;

public class CentroCustoActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centro_custo);

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
        getMenuInflater().inflate(R.menu.menu_centro_custo, menu);
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
                        CentroCusto centroCusto = (CentroCusto) rootList.getAdapter().getItem(position);
                        mostrarCentroCusto(centroCusto);
                        mViewPager.setCurrentItem(TELA_CADASTRO -1);
                    }
                });

                return rootList;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == TELA_CADASTRO) {
                rootView = inflater.inflate(R.layout.fragment_centro_custo_cadastro, container, false);
                final Context contexto = getContext();

                final TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
                final TextView descricao = (TextView) rootView.findViewById(R.id.input_descricao);
                codigo.setEnabled(false);
                codigo.setText("0");

                Button btnExcluir = (Button) rootView.findViewById(R.id.btnExcluir);
                btnExcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CentroCusto centroCusto = new CentroCusto();
                        centroCusto.setCodigoCentroCusto(Integer.valueOf(codigo.getText().toString()));

                        CentroCustoDao centroCustoDao = new CentroCustoDao(contexto);
                        centroCustoDao.deletar(centroCusto);

                        limparCamposTela();
                        carregarLista(contexto);

                        Toast.makeText(contexto, "Centro de custo deletado", Toast.LENGTH_LONG).show();
                    }
                });

                Button btnSalvar = (Button) rootView.findViewById(R.id.btnSalvar);
                btnSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validarDados()) {
                            return;
                        }

                        CentroCusto centroCusto = new CentroCusto();
                        centroCusto.setCodigoCentroCusto(Integer.valueOf(codigo.getText().toString()));
                        centroCusto.setCodigoUsuario(Usuario.getUsuarioLogado().getCodigo());
                        centroCusto.setDescricao(descricao.getText().toString());

                        CentroCustoDao centroCustoDao = new CentroCustoDao(contexto);
                        if (codigo.getText().toString().equals("0")) {
                            centroCustoDao.inserir(centroCusto);
                        } else {
                            centroCustoDao.alterar(centroCusto);
                        }

                        limparCamposTela();
                        carregarLista(contexto);

                        Toast.makeText(contexto, "Centro de custo salvo", Toast.LENGTH_LONG).show();
                    }
                });

                return rootView;
            }

            return rootView;
        }
    }

    private static void carregarLista(Context contexto) {
        CentroCustoDao centroCustoDao = new CentroCustoDao(contexto);
        ConsultaAdapter consultaAdapter = new ConsultaAdapter(contexto,
                centroCustoDao.listar(null),
                R.layout.fragment_centro_custo_consulta);
        rootList.setAdapter(consultaAdapter);
    }

    private static void mostrarCentroCusto(CentroCusto centroCusto) {
        TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
        TextView descricao = (TextView) rootView.findViewById(R.id.input_descricao);

        codigo.setText(centroCusto.getCodigoCentroCusto().toString());
        descricao.setText(centroCusto.getDescricao());
    }

    private static void limparCamposTela() {
        TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
        TextView descricao = (TextView) rootView.findViewById(R.id.input_descricao);

        codigo.setText("0");
        descricao.setText("");
    }

    private static boolean validarDados() {
        boolean retorno = true;

        TextView descricao = (TextView) rootView.findViewById(R.id.input_descricao);

        if (descricao.getText().toString().isEmpty()) {
            descricao.setError("descrição é obrigatória");
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
