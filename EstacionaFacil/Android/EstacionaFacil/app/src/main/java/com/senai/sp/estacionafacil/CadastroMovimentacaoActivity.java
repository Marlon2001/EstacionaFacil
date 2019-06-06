package com.senai.sp.estacionafacil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.senai.sp.tasks.CadastrarEntrada;

public class CadastroMovimentacaoActivity extends AppCompatActivity {

    private Button btnCadastro;
    private EditText txtPlaca;
    private EditText txtModelo;
    private CheckBox cbDiarista;
    private CheckBox cbAvulso;
    private CheckBox cbMensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        btnCadastro = findViewById(R.id.btn_nova_movimentacao);
        txtPlaca = findViewById(R.id.txt_placa_cadastro);
        txtModelo = findViewById(R.id.txt_modelo_cadastro);
        cbDiarista = findViewById(R.id.cb_diariasta);
        cbAvulso = findViewById(R.id.cb_avulso);
        cbMensalista = findViewById(R.id.cb_mensalista);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipo = "";
                if(cbDiarista.isSelected())
                    tipo = "D";
                else if(cbAvulso.isSelected())
                    tipo = "A";
                else if(cbMensalista.isSelected())
                    tipo = "M";

                CadastrarEntrada cadastrarEntrada = new CadastrarEntrada(txtPlaca.getText().toString(), txtModelo.getText().toString(), tipo);
                cadastrarEntrada.execute();
                finish();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        switch(view.getId()) {
            case R.id.cb_avulso:
                cbMensalista.setChecked(false);
                cbDiarista.setChecked(false);
                break;
            case R.id.cb_mensalista:
                cbAvulso.setChecked(false);
                cbDiarista.setChecked(false);
                break;
            case R.id.cb_diariasta:
                cbMensalista.setChecked(false);
                cbAvulso.setChecked(false);
                break;
        }
    }
}
