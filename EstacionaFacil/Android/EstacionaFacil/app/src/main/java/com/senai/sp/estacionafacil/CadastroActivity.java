package com.senai.sp.estacionafacil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.senai.sp.tasks.CadastrarEntrada;

public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastro;
    private EditText txtPlaca;
    private EditText txtModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        btnCadastro = findViewById(R.id.btn_nova_movimentacao);
        txtPlaca = findViewById(R.id.txt_placa_cadastro);
        txtModelo = findViewById(R.id.txt_modelo_cadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            CadastrarEntrada cadastrarEntrada = new CadastrarEntrada(txtPlaca.getText().toString(), txtModelo.getText().toString());
            cadastrarEntrada.execute();
            finish();
            }
        });
    }
}
