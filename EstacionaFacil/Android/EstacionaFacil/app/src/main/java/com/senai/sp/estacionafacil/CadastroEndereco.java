package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.senai.sp.model.Endereco;
import com.senai.sp.model.Mensalista;

public class CadastroEndereco extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;
    private EditText txtLogradouro;
    private EditText txtBairro;
    private EditText txtNumero;
    private EditText txtEstado;
    private EditText txtCidade;
    private EditText txtDescricao;
    private Mensalista mensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        btnProximo = findViewById(R.id.btn_proximo_endereco);
        btnVoltar = findViewById(R.id.btn_voltar_endereco);
        txtLogradouro = findViewById(R.id.txt_endereco_mensalista);
        txtBairro = findViewById(R.id.txt_bairro_mensalista);
        txtNumero = findViewById(R.id.txt_numero_mensalista);

        // Por enquanto, vamos manter o estado e a cidade como um EditText em vez de cidade
        txtEstado = findViewById(R.id.txt_estado_mensalista);
        txtCidade = findViewById(R.id.txt_cidade_mensalista);
        txtDescricao = findViewById(R.id.txt_descricao_mensalista);

        Intent intent = getIntent();
        Mensalista mensalistaIntent = (Mensalista) intent.getSerializableExtra("mensalista");
        Endereco enderecoIntent = (Endereco) intent.getSerializableExtra("endereco");

        if(mensalistaIntent != null){
            this.mensalista = mensalistaIntent;
        }
        if(enderecoIntent != null){
            txtLogradouro.setText(enderecoIntent.getLogradouro());
            txtBairro.setText(enderecoIntent.getBairro());
            txtNumero.setText(enderecoIntent.getNumero());
            txtEstado.setText(enderecoIntent.getEstado());
            txtCidade.setText(enderecoIntent.getCidade());
            txtDescricao.setText(enderecoIntent.getDescricao());
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent cadastroMensalista = new Intent(CadastroEndereco.this, CadastroMensalistaActivity.class);
            cadastroMensalista.putExtra("mensalista", mensalista);

            startActivity(cadastroMensalista);
            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Endereco endereco = new Endereco();

            // Populando o objeto endereco, para podermos coloca-lo no putExtra
            endereco.setLogradouro(txtLogradouro.getText().toString());
            endereco.setBairro(txtBairro.getText().toString());
            endereco.setNumero(txtNumero.getText().toString());
            endereco.setEstado(txtEstado.getText().toString());
            endereco.setCidade(txtCidade.getText().toString());
            endereco.setDescricao(txtDescricao.getText().toString());

            Intent cadastrarTelefone = new Intent(CadastroEndereco.this, CadastroTelefone.class);
            cadastrarTelefone.putExtra("mensalista", mensalista);
            cadastrarTelefone.putExtra("endereco", endereco);

            startActivity(cadastrarTelefone);
            }
        });
    }
}
