package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.senai.sp.model.Movimentacao;
import com.senai.sp.tasks.CarregarListaMovimentacao;

public class MovimentacaoActivity extends AppCompatActivity {

    private Button btnNovo;
    public static ListView listaMovimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimentacao);

        btnNovo = findViewById(R.id.bt_novo);
        listaMovimentacao = findViewById(R.id.list_movimentacao);

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent cadastrarMovimentacao = new Intent(MovimentacaoActivity.this, CadastroActivity.class);
            startActivity(cadastrarMovimentacao);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregarListaMovimentacao carregarListaMovimentacao = new CarregarListaMovimentacao(MovimentacaoActivity.this);
        carregarListaMovimentacao.execute();
    }
}