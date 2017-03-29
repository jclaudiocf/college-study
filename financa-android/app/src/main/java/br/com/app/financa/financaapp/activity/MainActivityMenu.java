package br.com.app.financa.financaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import br.com.app.financa.financaapp.R;
import br.com.app.financa.financaapp.banco.Banco;
import br.com.app.financa.financaapp.banco.BancoLocal;
import br.com.app.financa.financaapp.bean.Usuario;

public class MainActivityMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Direciona para a tela de login caso o usuário não esteja logado
        if (!Banco.USUARIO_LOGADO) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        BancoLocal bancoLocal = new BancoLocal(getSharedPreferences(BancoLocal.NOME_APP, BancoLocal.MODE_MULTI_PROCESS));
        BancoLocal.setInstancia(bancoLocal);

        TextView nomeLogado = (TextView) findViewById(R.id.tvNomeLogado);
        TextView emailLogado = (TextView) findViewById(R.id.tvEmailLogado);

        if (Usuario.getUsuarioLogado() instanceof Usuario) {
            //nomeLogado.setText(Usuario.getUsuarioLogado().getNome());
            //emailLogado.setText(Usuario.getUsuarioLogado().getEmail());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
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

        if (id == R.id.action_sobre) {
            Intent intent = new Intent(this, SobreActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.action_sair) {
            Banco.USUARIO_LOGADO = false;
            finish();
            System.exit(0);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_usuario) {
            intent = new Intent(this, UsuarioActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_conta) {
            intent = new Intent(this, ContaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_centrocusto) {
            intent = new Intent(this, CentroCustoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_fixo) {
            intent = new Intent(this, FixoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_movimento) {
            intent = new Intent(this, MovimentoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_saldo || id == R.id.nav_extrato) {
            intent = new Intent(this, EmbreveActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
