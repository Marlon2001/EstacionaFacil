package com.senai.sp.estacionafacil;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.senai.sp.tasks.CadastroEnderecoMensalista;
import com.senai.sp.tasks.ConsultarCidades;
import com.senai.sp.tasks.ConsultarEstados;

import java.util.concurrent.ExecutionException;

public class CadastroNovoEndereco extends AppCompatActivity {

    private Button btnSalvar;
    private Button btnCancelar;
    private EditText txtLogradouro;
    private EditText txtBairro;
    private EditText txtNumero;
    private EditText txtDescricao;
    private int codMensalista;
    private int codEndereco;
    public static Spinner spinnerEstado;
    public static Spinner spinnerCidades;
    private Endereco enderecoIntent;

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

        enderecoIntent = (Endereco) intent.getSerializableExtra("endereco");
        codEndereco = intent.getIntExtra("codEndereco", 0);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Endereco endereco = new Endereco();

                endereco.setLogradouro(txtLogradouro.getText().toString());
                endereco.setBairro(txtBairro.getText().toString());
                endereco.setNumero(txtNumero.getText().toString());
                endereco.setDescricao(txtDescricao.getText().toString());

                Cidade cidade = (Cidade) spinnerCidades.getSelectedItem();
                Estado estado = (Estado) spinnerEstado.getSelectedItem();

                endereco.setEstado(estado.getCodEstado());
                endereco.setCidade(cidade.getCodCidade());

                Mensalista mensalista = new Mensalista();
                mensalista.setCodMensalista(codMensalista);

                if(codEndereco == 0){
                    CadastroEnderecoMensalista cadastroEnderecoMensalista = new CadastroEnderecoMensalista(endereco, mensalista);
                    cadastroEnderecoMensalista.execute();

                    try {
                        cadastroEnderecoMensalista.get();

                        new AlertDialog.Builder(CadastroNovoEndereco.this)
                                .setTitle("Concluído!")
                                .setMessage("Registro cadastrado com sucesso.")
                                .setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    endereco.setCodEndereco(codEndereco);
                }

            }
        });

        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Estado estado = (Estado) parent.getItemAtPosition(position);
                ConsultarCidades consultarCidades = new ConsultarCidades(CadastroNovoEndereco.this, estado.getCodEstado(), "1002");
                consultarCidades.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConsultarEstados consultarEstados = new ConsultarEstados(CadastroNovoEndereco.this, "1002");

        try {
            if(enderecoIntent != null && codEndereco != 0){
                consultarEstados.execute().get();
                txtLogradouro.setText(enderecoIntent.getLogradouro().toString());
                txtBairro.setText(enderecoIntent.getBairro().toString());
                txtNumero.setText(enderecoIntent.getNumero().toString());
                txtDescricao.setText(enderecoIntent.getNumero().toString());
                spinnerEstado.setSelection(enderecoIntent.getEstado());

                ConsultarCidades consultarCidades = new ConsultarCidades(CadastroNovoEndereco.this, enderecoIntent.getEstado(), "1002");
                consultarCidades.execute();
            }else{
                consultarEstados.execute();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
