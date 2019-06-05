package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.senai.sp.model.Mensalista;

public class CadastroNovoEnderecoMensalista extends AppCompatActivity {

    private Button btnSalvar;
    private Button btnCancelar;
    private EditText txtLogradouro;
    private EditText txtBairro;
    private EditText txtNumero;
    private EditText txtDescricao;
    private int codMensalista;
    public static Spinner spinnerEstado;
    public static Spinner spinnerCidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_endereco);

        btnSalvar = findViewById(R.id.btn_salvar_endereco);
        btnCancelar = findViewById(R.id.btn_cancelar_endereco);

        txtLogradouro = findViewById(R.id.txt_logradouro);
        txtBairro = findViewById(R.id.txt_bairro);
        txtNumero = findViewById(R.id.txt_numero);
        txtDescricao = findViewById(R.id.txt_descricao);
        spinnerEstado = findViewById(R.id.spinner_estados2);
        spinnerCidades = findViewById(R.id.spinner_cidades2);

        Intent intent = getIntent();
        codMensalista = intent.getIntExtra("codMensalista", 0);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
