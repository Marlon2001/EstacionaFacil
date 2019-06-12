package com.senai.sp.estacionafacil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.senai.sp.model.Preco;
import com.senai.sp.tasks.CadastrarPreco;
import com.senai.sp.tasks.ConsultarPreco;

public class CadastrarPrecoActivity extends AppCompatActivity {

    private Button btnCadastrarPreco;
    private EditText txtPrimeiraHoraCadastro;
    private EditText txtDemaisHorasCadastro;
    private EditText txtVagaCadastro;
    private EditText txtDiariaCadastro;

    private TextView txtVIewDiaria;
    private TextView txtViewVaga;
    private TextView textViewPrimeiraHora;
    private TextView textViewDemaisHoras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_preco);

        btnCadastrarPreco = findViewById(R.id.btn_cadastro_preco);
        txtPrimeiraHoraCadastro = findViewById(R.id.txt_hora_1_cadastro);
        txtDemaisHorasCadastro = findViewById(R.id.txt_demais_horas_cadastro);
        textViewDemaisHoras = findViewById(R.id.txt_demais_horas_valor);
        textViewPrimeiraHora = findViewById(R.id.text_primeira_hora_valor);

        txtVagaCadastro = findViewById(R.id.txt_vaga_cadastro);
        txtDiariaCadastro = findViewById(R.id.txt_diaria_cadastro);

        txtVIewDiaria = findViewById(R.id.txt_diaria);
        txtViewVaga = findViewById(R.id.txt_vaga);


        btnCadastrarPreco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Preco preco = new Preco();
            preco.setValorHora1(Double.parseDouble(txtPrimeiraHoraCadastro.getText().toString()));
            preco.setValorDemaisHoras(Double.parseDouble(txtDemaisHorasCadastro.getText().toString()));
            preco.setValorDiaria(Double.parseDouble(txtDiariaCadastro.getText().toString()));
            preco.setValorVaga(Double.parseDouble(txtVagaCadastro.getText().toString()));


            CadastrarPreco cadastrarPreco = new CadastrarPreco(CadastrarPrecoActivity.this, preco);
            cadastrarPreco.execute();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConsultarPreco consultarPreco = new ConsultarPreco(textViewPrimeiraHora, textViewDemaisHoras, txtVIewDiaria, txtViewVaga);
        consultarPreco.execute();
    }
}
