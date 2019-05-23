package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.senai.sp.model.Movimentacao;
import com.senai.sp.utils.Data;

public class ComprovanteActivity extends AppCompatActivity {

    private EditText txtPlaca;
    private EditText txtModelo;
    private EditText txtDataEntrada;
    private EditText txtDataSaida;
    private EditText txtValorPago;
    private EditText txtTempoPermanencia;
    private Button btnEnviarSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        txtPlaca = findViewById(R.id.txt_placa_comprovante);
        txtModelo = findViewById(R.id.txt_modelo_comprovante);
        txtDataEntrada = findViewById(R.id.txt_data_entrada_comprovante);
        txtDataSaida = findViewById(R.id.txt_data_saida_comprovante);
        txtValorPago = findViewById(R.id.txt_valor_pago_comprovante);
        txtTempoPermanencia = findViewById(R.id.txt_tempo_permanencia_comprovante);
        btnEnviarSms = findViewById(R.id.btn_enviar_sms);

        Intent intentComprovante = getIntent();
        Movimentacao movimentacao = (Movimentacao) intentComprovante.getSerializableExtra("movimentacao");
        if(movimentacao != null){
            try {
                txtPlaca.setText(movimentacao.getPlaca());
                txtModelo.setText(movimentacao.getModelo());
                txtDataEntrada.setText(Data.converterParaPortugues(movimentacao.getDataEntrada()));
                txtDataSaida.setText(Data.converterParaPortugues(movimentacao.getDataSaida()));
                txtValorPago.setText("R$ "+movimentacao.getValorPago().toString());
                txtTempoPermanencia.setText(movimentacao.getTempoPermanencia() + " minutos.");
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        btnEnviarSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirMainActivity = new Intent(ComprovanteActivity.this, MainActivity.class);
                startActivity(abrirMainActivity);
            }
        });
    }
}
