package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.senai.sp.model.Telefone;
import com.senai.sp.tasks.CadastroTelefoneMensalista;
import com.senai.sp.tasks.CarregarTelefoneMensalista;

public class TelefoneMensalistaActivity extends AppCompatActivity {

    public static ListView listTelefone;
    private Button btnNovoTelefone;
    private int codMensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefone_mensalista);

        listTelefone = findViewById(R.id.list_telefone);
        btnNovoTelefone = findViewById(R.id.btn_telefone_mensalista);

        Intent intent = getIntent();
        codMensalista = intent.getIntExtra("codMensalista", 0);

        btnNovoTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCadastroTelefone = new Intent(TelefoneMensalistaActivity.this, CadastroNovoTelefone.class);
                abrirCadastroTelefone.putExtra("codMensalista", codMensalista);

                startActivity(abrirCadastroTelefone);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregarTelefoneMensalista carregarTelefoneMensalista = new CarregarTelefoneMensalista(this, codMensalista);
        carregarTelefoneMensalista.execute();
    }
}
