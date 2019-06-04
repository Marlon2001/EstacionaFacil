package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.senai.sp.model.Veiculo;
import com.senai.sp.tasks.CarregarVeiculoMensalista;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class VeiculoMensalistaActivity extends AppCompatActivity {

    public static ListView listVeiculos;
    private Button btnNovoVeiculo;
    private List<Veiculo> listVeiculo;
    private int codMensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo_mensalista);

        listVeiculos = findViewById(R.id.list_veiculos);
        btnNovoVeiculo = findViewById(R.id.btn_novo_veiculo);

        Intent intent = getIntent();
        codMensalista = intent.getIntExtra("codMensalista", 0);

        btnNovoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCadastroVeiculo = new Intent(VeiculoMensalistaActivity.this, CadastroNovoVeiculo.class);
                abrirCadastroVeiculo.putExtra("codMensalista", codMensalista);

                startActivity(abrirCadastroVeiculo);
            }
        });

        listVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregarVeiculoMensalista carregarVeiculoMensalista = new CarregarVeiculoMensalista(VeiculoMensalistaActivity.this, codMensalista);
        carregarVeiculoMensalista.execute();
    }
}
