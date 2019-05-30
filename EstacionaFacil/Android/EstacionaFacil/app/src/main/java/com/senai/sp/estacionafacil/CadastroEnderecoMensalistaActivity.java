package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.senai.sp.model.Cidade;
import com.senai.sp.model.Endereco;
import com.senai.sp.model.Estado;
import com.senai.sp.model.Mensalista;
import com.senai.sp.tasks.ConsultarCidades;
import com.senai.sp.tasks.ConsultarEstados;

public class CadastroEnderecoMensalistaActivity extends AppCompatActivity {

    private Button btnProximo;
    private Button btnVoltar;
    private EditText txtLogradouro;
    private EditText txtBairro;
    private EditText txtNumero;
    private EditText txtDescricao;
    private Mensalista mensalista;
    public static Spinner spinnerEstado;
    public static Spinner spinnerCidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        btnProximo = findViewById(R.id.btn_proximo_endereco);
        btnVoltar = findViewById(R.id.btn_voltar_endereco);

        txtLogradouro = findViewById(R.id.txt_endereco_mensalista);
        txtBairro = findViewById(R.id.txt_bairro_mensalista);
        txtNumero = findViewById(R.id.txt_numero_mensalista);
        txtDescricao = findViewById(R.id.txt_descricao_mensalista);
        spinnerEstado = findViewById(R.id.spinner_estados);
        spinnerCidades = findViewById(R.id.spinner_cidades);

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
            txtDescricao.setText(enderecoIntent.getDescricao());
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent cadastroMensalista = new Intent(CadastroEnderecoMensalistaActivity.this, CadastroMensalistaActivity.class);
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
            endereco.setDescricao(txtDescricao.getText().toString());

            Cidade cidade = (Cidade) spinnerCidades.getSelectedItem();
            Estado estado = (Estado) spinnerEstado.getSelectedItem();

            endereco.setEstado(estado.getCodEstado());
            endereco.setCidade(cidade.getCodCidade());

            Intent cadastrarTelefone = new Intent(CadastroEnderecoMensalistaActivity.this, CadastroTelefoneMensalistaActivity.class);
            cadastrarTelefone.putExtra("mensalista", mensalista);
            cadastrarTelefone.putExtra("endereco", endereco);

            startActivity(cadastrarTelefone);
            }
        });

        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Estado estado = (Estado) parent.getItemAtPosition(position);
                ConsultarCidades consultarCidades = new ConsultarCidades(CadastroEnderecoMensalistaActivity.this, estado.getCodEstado());
                consultarCidades.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConsultarEstados consultarEstados = new ConsultarEstados(CadastroEnderecoMensalistaActivity.this);
        consultarEstados.execute();
    }
}
