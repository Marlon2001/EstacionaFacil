package com.senai.sp.estacionafacil;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.senai.sp.model.Endereco;
import com.senai.sp.model.Mensalista;
import com.senai.sp.model.Telefone;
import com.senai.sp.model.Veiculo;
import com.senai.sp.tasks.CadastrarSaida;
import com.senai.sp.tasks.CadastroMensalista;

public class CadastroVeiculo extends AppCompatActivity {

    private Button btnFinalizar;
    private Button btnCancelar;
    private EditText txtPlaca;
    private EditText txtModelo;
    private EditText txtAno;
    private Mensalista mensalista;
    private Endereco endereco;
    private Telefone telefone;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        btnFinalizar = findViewById(R.id.btn_salvar_veiculo_mensalista);
        btnCancelar = findViewById(R.id.btn_cancelar_veiculo);
        txtPlaca = findViewById(R.id.txt_placa_mensalista);
        txtModelo = findViewById(R.id.txt_modelo_mensalista);
        txtAno = findViewById(R.id.txt_ano_mensalista);

        Intent intent = getIntent();
        Mensalista mensalistaIntent = (Mensalista) intent.getSerializableExtra("mensalista");
        Endereco enderecoIntent = (Endereco) intent.getSerializableExtra("endereco");
        Telefone telefoneIntent = (Telefone) intent.getSerializableExtra("telefone");

        if(mensalistaIntent != null){
            this.mensalista = mensalistaIntent;
        }
        if(enderecoIntent != null){
            this.endereco = enderecoIntent;
        }
        if(telefoneIntent != null){
            this.telefone = telefoneIntent;
        }

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent abrirMainActivtity = new Intent(CadastroVeiculo.this, MainActivity.class);

            startActivity(abrirMainActivtity);
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veiculo = new Veiculo();

                // Populando o objeto veiculo, e chamandos as tasks de cadastro
                veiculo.setPlaca(txtPlaca.getText().toString());
                veiculo.setModelo(txtModelo.getText().toString());
                veiculo.setAnoVeiculo(txtAno.getText().toString());

                CadastroMensalista cadastroMensalista = new CadastroMensalista(CadastroVeiculo.this,  mensalista,  telefone,   veiculo,   endereco);
                cadastroMensalista.execute();

                Log.d("----------- Mensalista", mensalista.getNome());
                Log.d("----------- Endereco", endereco.getLogradouro());
                Log.d("----------- Telefone", telefone.getTelefone());
                Log.d("----------- Veiculo", veiculo.getPlaca());
            }
        });
    }
}
