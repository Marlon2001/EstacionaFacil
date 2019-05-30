package com.senai.sp.estacionafacil;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.senai.sp.model.Telefone;
import com.senai.sp.tasks.CadastroTelefoneMensalista;

import java.util.concurrent.ExecutionException;

public class CadastroNovoTelefone extends AppCompatActivity {

    private TextView txtTelefone;
    private TextView txtTipotelefone;
    private Button btnCancelar;
    private Button btnSalvar;
    private int codMensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_telefone);

        txtTelefone = findViewById(R.id.txt_novo_telefone);
        txtTipotelefone = findViewById(R.id.txt_tipo_novo_telefone);
        btnSalvar = findViewById(R.id.btn_salvar_telefone);
        btnCancelar = findViewById(R.id.btn_cancelar_cadastro);

        Intent intent = getIntent();
        codMensalista = intent.getIntExtra("codMensalista", 0);
        Log.d("COD MENSALISTA", codMensalista+"");

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Telefone telefone = new Telefone();
                telefone.setTelefone(txtTelefone.getText().toString());
                telefone.setTipoTelefone(txtTipotelefone.getText().toString());

                CadastroTelefoneMensalista cadastroTelefoneMensalista = new CadastroTelefoneMensalista(telefone, codMensalista);
                cadastroTelefoneMensalista.execute();

                try {
                    cadastroTelefoneMensalista.get();

                    new AlertDialog.Builder(CadastroNovoTelefone.this)
                        .setTitle("Concluído!")
                        .setMessage("Cadastro realizado com sucesso.")
                        .setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
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
