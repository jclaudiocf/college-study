package br.com.app.financa.financaapp.activity;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import br.com.app.financa.financaapp.R;
import br.com.app.financa.financaapp.adapters.ConsultaAdapter;
import br.com.app.financa.financaapp.bean.Usuario;
import br.com.app.financa.financaapp.dao.UsuarioDao;

public class UsuarioActivity extends AppCompatActivity {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private static final int TELA_CONSULTA = 1;
    private static final int TELA_CADASTRO = 2;
    private static View rootView = null;
    private static ListView rootList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        mViewPager.setCurrentItem(TELA_CONSULTA -1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Usuario Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://br.com.app.financa.financaapp.activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);*/
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Usuario Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://br.com.app.financa.financaapp.activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();*/
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

                UsuarioDao usuarioDao = new UsuarioDao(getContext());
                ConsultaAdapter consultaAdapter = new ConsultaAdapter(getContext(),
                        usuarioDao.listar(null),
                        R.layout.fragment_usuario_consulta);
                rootList.setAdapter(consultaAdapter);

                rootList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Usuario usuario = (Usuario) rootList.getAdapter().getItem(position);
                        mostrarUsuario(usuario);
                        mViewPager.setCurrentItem(TELA_CADASTRO -1);
                    }
                });

                return rootList;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == TELA_CADASTRO) {
                rootView = inflater.inflate(R.layout.fragment_usuario_cadastro, container, false);
                final Context contexto = getContext();

                final TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
                final TextView nome = (TextView) rootView.findViewById(R.id.input_nome);
                final TextView email = (TextView) rootView.findViewById(R.id.input_email);
                final TextView senha = (TextView) rootView.findViewById(R.id.input_senha);
                codigo.setEnabled(false);
                codigo.setText("0");

                Button btnExcluir = (Button) rootView.findViewById(R.id.btnExcluir);
                btnExcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Usuario usuario = new Usuario();
                        usuario.setCodigo(Integer.valueOf(codigo.getText().toString()));

                        UsuarioDao usuarioDao = new UsuarioDao(contexto);
                        usuarioDao.deletar(usuario);

                        limparCamposTela();
                        recarregarLista(contexto);

                        Toast.makeText(contexto, "Usuário deletado", Toast.LENGTH_LONG).show();
                    }
                });

                Button btnSalvar = (Button) rootView.findViewById(R.id.btnSalvar);
                btnSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validarDados()) {
                            return;
                        }

                        Usuario usuario = new Usuario();
                        usuario.setCodigo(Integer.valueOf(codigo.getText().toString()));
                        usuario.setNome(nome.getText().toString());
                        usuario.setEmail(email.getText().toString());
                        usuario.setSenha(senha.getText().toString());

                        UsuarioDao usuarioDao = new UsuarioDao(contexto);

                        if (codigo.getText().toString().equals("0")) {
                            usuarioDao.inserir(usuario);
                        } else {
                            usuarioDao.alterar(usuario);
                        }

                        limparCamposTela();
                        recarregarLista(contexto);

                        Toast.makeText(contexto, "Usuário salvo", Toast.LENGTH_LONG).show();
                    }
                });

                return rootView;
            }

            return rootView;
        }
    }

    private static void mostrarUsuario(Usuario usuario) {
        TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
        TextView nome = (TextView) rootView.findViewById(R.id.input_nome);
        TextView email = (TextView) rootView.findViewById(R.id.input_email);
        TextView senha = (TextView) rootView.findViewById(R.id.input_senha);

        codigo.setText(usuario.getCodigo().toString());
        nome.setText(usuario.getNome());
        email.setText(usuario.getEmail());
        senha.setText(usuario.getSenha());
    }

    private static void limparCamposTela() {
        TextView codigo = (TextView) rootView.findViewById(R.id.input_codigo);
        TextView nome = (TextView) rootView.findViewById(R.id.input_nome);
        TextView email = (TextView) rootView.findViewById(R.id.input_email);
        TextView senha = (TextView) rootView.findViewById(R.id.input_senha);

        codigo.setText("0");
        nome.setText("");
        email.setText("");
        senha.setText("");
    }

    private static void recarregarLista(Context contexto) {
        UsuarioDao usuarioDaoRefresh = new UsuarioDao(contexto);
        ConsultaAdapter consultaAdapter = new ConsultaAdapter(contexto,
                usuarioDaoRefresh.listar(null),
                R.layout.fragment_usuario_consulta);
        rootList.setAdapter(consultaAdapter);
    }

    private static boolean validarDados() {
        boolean retorno = true;

        TextView nome = (TextView) rootView.findViewById(R.id.input_nome);
        TextView email = (TextView) rootView.findViewById(R.id.input_email);
        TextView senha = (TextView) rootView.findViewById(R.id.input_senha);

        if (nome.getText().toString().isEmpty()) {
            nome.setError("nome é obrigatório");
            retorno = false;
        }

        if (email.getText().toString().isEmpty()) {
            email.setError("email é obrigatório");
            retorno = false;
        }

        if (senha.getText().toString().isEmpty()) {
            senha.setError("senha é obrigatório");
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
