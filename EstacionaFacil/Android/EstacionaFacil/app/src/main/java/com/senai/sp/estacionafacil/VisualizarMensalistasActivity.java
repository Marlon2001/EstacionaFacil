package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.senai.sp.tasks.CadastroMensalista;
import com.senai.sp.tasks.CarregarListaMensalistas;

public class VisualizarMensalistasActivity extends AppCompatActivity {

    private Button btnNovoMensalista;
    public static ListView listMensalistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_mensalistas);

        btnNovoMensalista = findViewById(R.id.btn_novo_mensalista);
        listMensalistas = findViewById(R.id.list_mensalistas);

        btnNovoMensalista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent abrirCadastroMensalista = new Intent(VisualizarMensalistasActivity.this, CadastroMensalistaActivity.class);
            startActivity(abrirCadastroMensalista);
            }
        });
    }

    @Override
    protected void onResume() {
        CarregarListaMensalistas carregarListaMensalistas = new CarregarListaMensalistas(this);
        carregarListaMensalistas.execute();
        super.onResume();
    }
}