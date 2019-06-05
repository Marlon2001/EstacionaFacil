package com.senai.sp.estacionafacil;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.senai.sp.model.Endereco;
import com.senai.sp.model.Mensalista;
import com.senai.sp.model.Veiculo;
import com.senai.sp.tasks.CadastroVeiculoMensalista;
import com.senai.sp.tasks.EditarVeiculoMensalista;

import java.util.concurrent.ExecutionException;

public class CadastroNovoVeiculo extends AppCompatActivity {

    private TextView txtPlaca;
    private TextView txtModelo;
    private TextView txtAno;
    private TextView txtFabricante;
    private Button btnCancelar;
    private Button btnSalvar;
    private int codMensalista;
    private int codVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_veiculo);

        txtPlaca = findViewById(R.id.txt_placa_veiculo2);
        txtModelo = findViewById(R.id.txt_modelo_veiculo2);
        txtAno = findViewById(R.id.txt_ano_veiculo2);
        txtFabricante = findViewById(R.id.txt_fabricante_veiculo);
        btnCancelar = findViewById(R.id.btn_cancelar_veiculo2);
        btnSalvar = findViewById(R.id.btn_salvar_veiculo);

        Intent intent = getIntent();
        codMensalista = intent.getIntExtra("codMensalista", 0);

        Veiculo veiculoIntent = (Veiculo) intent.getSerializableExtra("veiculo");
        codVeiculo = intent.getIntExtra("codVeiculo", 0);

        if(veiculoIntent != null && codVeiculo != 0){
            txtPlaca.setText(veiculoIntent.getPlaca());
            txtModelo.setText(veiculoIntent.getModelo());
            txtAno.setText(veiculoIntent.getAnoVeiculo());
            txtFabricante.setHint("Não estamos cadastrando o fabricante");
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(txtPlaca.getText().toString());
                veiculo.setModelo(txtModelo.getText().toString());
                veiculo.setAnoVeiculo(txtAno.getText().toString());
                veiculo.setCodFabricante(1);

                Mensalista mensalista = new Mensalista();
                mensalista.setCodMensalista(codMensalista);

                if(codVeiculo == 0) {
                    CadastroVeiculoMensalista cadastroVeiculoMensalista = new CadastroVeiculoMensalista(veiculo, mensalista);
                    cadastroVeiculoMensalista.execute();

                    try {
                        cadastroVeiculoMensalista.get();

                        new AlertDialog.Builder(CadastroNovoVeiculo.this)
                                .setTitle("Concluído!")
                                .setMessage("Registro cadastrado com sucesso.")
                                .setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
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
                }else{
                    veiculo.setCodVeiculo(codVeiculo);

                    EditarVeiculoMensalista editarVeiculoMensalista = new EditarVeiculoMensalista(veiculo, mensalista);
                    editarVeiculoMensalista.execute();

                    try {
                        editarVeiculoMensalista.get();

                        new AlertDialog.Builder(CadastroNovoVeiculo.this)
                                .setTitle("Concluído!")
                                .setMessage("Registro atualizado com sucesso.")
                                .setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
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
