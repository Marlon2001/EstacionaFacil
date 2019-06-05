package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.senai.sp.tasks.CarregarEnderecoMensalista;
import com.senai.sp.tasks.CarregarTelefoneMensalista;

public class EnderecosMensalistaActivity extends AppCompatActivity {

    public static ListView listEndereco;
    private Button btnNovoEndereco;
    private int codMensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enderecos_mensalista);

        listEndereco = findViewById(R.id.list_endereco);
        btnNovoEndereco = findViewById(R.id.btn_novo_endereco);

        Intent intent = getIntent();
        codMensalista = intent.getIntExtra("codMensalista", 0);

        btnNovoEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // COLOCAR A ACTIVITY DE CADASTRO DO TELEFONE
                Intent abrirCadastroEndereco = new Intent(EnderecosMensalistaActivity.this, CadastroNovoTelefone.class);
                abrirCadastroEndereco.putExtra("codMensalista", codMensalista);

                startActivity(abrirCadastroEndereco);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregarEnderecoMensalista carregarEnderecoMensalista = new CarregarEnderecoMensalista(this, codMensalista);
        carregarEnderecoMensalista.execute();
    }
}
