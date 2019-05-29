package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.senai.sp.model.Mensalista;
import com.senai.sp.tasks.CadastroMensalista;

public class CadastroMensalistaActivity extends AppCompatActivity {

    private Button btnProximo;
    private Button btnCancelar;
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtCpf;
    public static Mensalista mensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mensalista);

        btnProximo = findViewById(R.id.btn_salvar_mensalista);
        btnCancelar = findViewById(R.id.btn_cancelar_mensalista);
        txtNome = findViewById(R.id.cadatro_nome_mensalista);
        txtEmail = findViewById(R.id.cadastro_email_mensalista);
        txtCpf = findViewById(R.id.cadastro_cpf_mensalista);

        Intent intent = getIntent();
        Mensalista mensalistaIntent = (Mensalista) intent.getSerializableExtra("mensalista");
        if(mensalistaIntent != null){
            txtNome.setText(mensalistaIntent.getNome());
            txtEmail.setText(mensalistaIntent.getEmail());
            txtCpf.setText(mensalistaIntent.getCpf());
        }

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent abrirMainActivity = new Intent(CadastroMensalistaActivity.this, MainActivity.class);
            startActivity(abrirMainActivity);
            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Mensalista mensalista = new Mensalista();
            // Populando o objeto mensalista, para podermos coloca-lo no putExtra
            mensalista.setNome(txtNome.getText().toString());
            mensalista.setEmail(txtEmail.getText().toString());
            mensalista.setCpf(txtCpf.getText().toString());

            Intent cadastroEndereco = new Intent(CadastroMensalistaActivity.this, CadastroEndereco.class);
            cadastroEndereco.putExtra("mensalista", mensalista);

            startActivity(cadastroEndereco);
            }
        });
    }
}
