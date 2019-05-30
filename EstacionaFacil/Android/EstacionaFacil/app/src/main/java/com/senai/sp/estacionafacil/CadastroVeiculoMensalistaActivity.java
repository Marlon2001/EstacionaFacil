package com.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.senai.sp.model.Endereco;
import com.senai.sp.model.Mensalista;
import com.senai.sp.model.Telefone;
import com.senai.sp.model.Veiculo;
import com.senai.sp.tasks.CadastroEnderecoMensalista;
import com.senai.sp.tasks.CadastroMensalista;
import com.senai.sp.tasks.CadastroTelefoneMensalista;
import com.senai.sp.tasks.CadastroVeiculoMensalista;

import java.util.concurrent.ExecutionException;

public class CadastroVeiculoMensalistaActivity extends AppCompatActivity {

    private Button btnFinalizar;
    private Button btnCancelar;
    private EditText txtPlaca;
    private EditText txtModelo;
    private EditText txtAno;
    private Mensalista mensalista;
    private Endereco endereco;
    private Telefone telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        btnFinalizar = findViewById(R.id.btn_salvar_veiculo_mensalista);
        btnCancelar = findViewById(R.id.btn_cancelar_veiculo);
        txtPlaca = findViewById(R.id.txt_placa_mensalista);
        txtModelo = findViewById(R.id.txt_modelo_mensalista);
        txtAno = findViewById(R.id.txt_ano_mensalista);

        Intent intent = getIntent();
        Mensalista mensalistaIntent = (Mensalista) intent.getSerializableExtra("mensalista");
        Endereco enderecoIntent = (Endereco) intent.getSerializableExtra("endereco");
        Telefone telefoneIntent = (Telefone) intent.getSerializableExtra("telefone");

        if(mensalistaIntent != null){
            this.mensalista = mensalistaIntent;
        }
        if(enderecoIntent != null){
            this.endereco = enderecoIntent;
        }
        if(telefoneIntent != null){
            this.telefone = telefoneIntent;
        }

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent abrirMainActivtity = new Intent(CadastroVeiculoMensalistaActivity.this, MainActivity.class);
            startActivity(abrirMainActivtity);
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Veiculo veiculo = new Veiculo();
            // Populando o objeto veiculo, e chamandos as tasks de cadastro
            veiculo.setPlaca(txtPlaca.getText().toString());
            veiculo.setModelo(txtModelo.getText().toString());
            veiculo.setAnoVeiculo(txtAno.getText().toString());
            veiculo.setCodFabricante(2);

            CadastroMensalista cadastroMensalista = new CadastroMensalista(CadastroVeiculoMensalistaActivity.this, mensalista);

            try {
                Mensalista mensalista = (Mensalista) cadastroMensalista.execute().get();

                CadastroEnderecoMensalista cadastroEnderecoMensalista = new CadastroEnderecoMensalista(endereco, mensalista);
                cadastroEnderecoMensalista.execute();

                CadastroTelefoneMensalista cadastroTelefoneMensalista = new CadastroTelefoneMensalista(telefone, mensalista);
                cadastroTelefoneMensalista.execute();

                CadastroVeiculoMensalista cadastroVeiculoMensalista = new CadastroVeiculoMensalista(veiculo, mensalista);
                cadastroVeiculoMensalista.execute();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Toast.makeText(CadastroVeiculoMensalistaActivity.this, "Cadastrado", Toast.LENGTH_SHORT).show();
                Intent abrirMainActivity = new Intent(CadastroVeiculoMensalistaActivity.this, MainActivity.class);
                startActivity(abrirMainActivity);
            }
            }
        });
    }
}
