package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.senai.sp.model.Endereco;
import com.senai.sp.model.Mensalista;
import com.senai.sp.model.Telefone;

public class CadastroTelefoneMensalistaActivity extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;
    private EditText txtTelefone;
    private EditText txtTipotelefone;
    private Mensalista mensalista;
    private Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_telefone);

        btnProximo = findViewById(R.id.btn_salvar_telefone_mensalista);
        btnVoltar = findViewById(R.id.btn_voltar_telefone);
        txtTelefone = findViewById(R.id.txt_telefone_mensalista);
        txtTipotelefone = findViewById(R.id.txt_tipo_telefone_mensalista);

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
            txtTelefone.setText(telefoneIntent.getTelefone());
            txtTipotelefone.setText(telefoneIntent.getTipoTelefone());
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent cadastroEndereco = new Intent(CadastroTelefoneMensalistaActivity.this, CadastroEnderecoMensalistaActivity.class);
            cadastroEndereco.putExtra("endereco", endereco);
            startActivity(cadastroEndereco);
            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Telefone telefone = new Telefone();

            // Populando o objeto telefone, para podermos coloca-lo no putExtra
            telefone.setTelefone(txtTelefone.getText().toString());
            telefone.setTipoTelefone(txtTipotelefone.getText().toString());

            Intent cadastrarVeiculo = new Intent(CadastroTelefoneMensalistaActivity.this, CadastroVeiculoMensalistaActivity.class);
            cadastrarVeiculo.putExtra("mensalista", mensalista);
            cadastrarVeiculo.putExtra("endereco", endereco);
            cadastrarVeiculo.putExtra("telefone", telefone);

            startActivity(cadastrarVeiculo);
            }
        });
    }
}
