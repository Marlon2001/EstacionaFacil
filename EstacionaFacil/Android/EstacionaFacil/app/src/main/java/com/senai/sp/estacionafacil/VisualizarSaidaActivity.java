package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.senai.sp.model.Movimentacao;
import com.senai.sp.tasks.CadastrarSaida;
import com.senai.sp.utils.Data;

public class VisualizarSaidaActivity extends AppCompatActivity {

    private EditText txtPlaca;
    private EditText txtModelo;
    private EditText txtDataEntrada;
    private EditText txtDataSaida;
    private EditText txtValorPago;
    private EditText txtTempoPermanencia;
    private Button btnConfirmar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_saida);

        txtPlaca = findViewById(R.id.txt_placa_comprovante);
        txtModelo = findViewById(R.id.txt_modelo_comprovante);
        txtDataEntrada = findViewById(R.id.txt_data_entrada_comprovante);
        txtDataSaida = findViewById(R.id.txt_data_saida_comprovante);
        txtValorPago = findViewById(R.id.txt_valor_pago_comprovante);
        txtTempoPermanencia = findViewById(R.id.txt_tempo_permanencia_comprovante);
        btnConfirmar = findViewById(R.id.btn_confirmar_saida);
        btnCancelar = findViewById(R.id.btn_cancelar);

        Intent visualizarSaida = getIntent();
        final Movimentacao movimentacao = (Movimentacao) visualizarSaida.getSerializableExtra("movimentacao");
        Toast.makeText(VisualizarSaidaActivity.this, String.valueOf(movimentacao.getId()), Toast.LENGTH_LONG).show();
        if(movimentacao != null){
            try {
                txtPlaca.setText(movimentacao.getPlaca());
                txtModelo.setText(movimentacao.getModelo());
                txtDataEntrada.setText(Data.converterParaPortugues(movimentacao.getDataEntrada()));
                txtDataSaida.setText(Data.converterParaPortugues(movimentacao.getDataSaida()));
                txtValorPago.setText("R$ " + movimentacao.getValorPago().toString());
                txtTempoPermanencia.setText(movimentacao.getTempoPermanencia().toString() + " minutos.");
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            CadastrarSaida cadastrarSaida = new CadastrarSaida(VisualizarSaidaActivity.this, movimentacao);
            cadastrarSaida.execute();
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
