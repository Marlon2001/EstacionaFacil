package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CadastroVeiculo extends AppCompatActivity implements View.OnClickListener {

    Button btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);
        btnProximo = findViewById(R.id.btn_salvar_endereco_mensalista);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_salvar_mensalista:
                Intent intent = new Intent(this,CadastroTelefone.class);
                startActivity(intent);
                Toast.makeText(this, "Clicou", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
