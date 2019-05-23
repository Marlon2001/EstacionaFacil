package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CadastroMensalistaActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mensalista);

        btnProximo = findViewById(R.id.btn_salvar_mensalista);
        btnProximo.setOnClickListener(this);
//        btnProximo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CadastroMensalistaActivity.this,CadastroEndereco.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_salvar_mensalista:
                Intent intent = new Intent(this,CadastroEndereco.class);
                startActivity(intent);
                Toast.makeText(this, "Clicou", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
