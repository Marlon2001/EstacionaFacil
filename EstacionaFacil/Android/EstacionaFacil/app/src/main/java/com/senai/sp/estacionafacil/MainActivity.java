package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.senai.sp.tasks.CarregarListaMovimentacao;
import com.senai.sp.tasks.ConsultarSaida;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static final String ipServidor = "10.107.144.29";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public  boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.menu_movimentacao:
                Intent listarMovimentacao = new Intent(MainActivity.this, MovimentacaoActivity.class);
                startActivity(listarMovimentacao);
                break;
            case R.id.menu_entrada:
                Intent cadastrarMovimentacao = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(cadastrarMovimentacao);
                break;
            case R.id.menu_saida:
//                ConsultarSaida consultarSaida = new ConsultarSaida(MainActivity.this, movimentacao);
//                consultarSaida.execute();
                break;
            case R.id.visualizar_mensalista:
                break;
            case R.id.cadastrar_mensalista:
                Intent cadastrarMensalista = new Intent(this, CadastroMensalistaActivity.class);
                startActivity(cadastrarMensalista);
                break;
            case R.id.receber_mensalidade:
                break;
            case R.id.relatorio_dia:
                break;
            case R.id.relatorio_mes:
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_movimentacao, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_precos:
                Intent abrirCadastroPreco = new Intent(MainActivity.this, CadastrarPrecoActivity.class);
                startActivity(abrirCadastroPreco);
                break;
            case R.id.menu_configuracoes:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
