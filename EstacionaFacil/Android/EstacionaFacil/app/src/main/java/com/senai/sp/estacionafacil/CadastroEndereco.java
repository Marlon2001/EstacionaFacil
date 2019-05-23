package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CadastroEndereco extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_salvar_mensalista:
                Intent intent = new Intent(this,CadastroVeiculo.class);
                startActivity(intent);
                Toast.makeText(this, "Clicou", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
