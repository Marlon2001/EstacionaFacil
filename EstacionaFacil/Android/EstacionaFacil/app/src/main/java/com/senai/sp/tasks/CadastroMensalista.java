package com.senai.sp.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.senai.sp.estacionafacil.CadastroTelefone;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Endereco;
import com.senai.sp.model.Mensalista;
import com.senai.sp.model.Telefone;
import com.senai.sp.model.Veiculo;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CadastroMensalista extends AsyncTask {
    Context context;
    Mensalista mensalista;
    Telefone telefone;
    Veiculo veiculo;
    Endereco endereco;
    Mensalista mensalistaResposta;
    String resposta;
    String tipoTelefone;

    public  CadastroMensalista(Context context, Mensalista mensalista, Telefone telefone, Veiculo  veiculo, Endereco  endereco){
        this.context = context;
        this.mensalista = mensalista;
        this.telefone = telefone;
        this.veiculo = veiculo;
        this.endereco = endereco;
        this.tipoTelefone = tipoTelefone;
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsMovimento = new JSONStringer();

        try {
            jsMovimento.object();
            jsMovimento.key("nome").value(mensalista.getNome());
            jsMovimento.key("email").value(mensalista.getEmail());
            jsMovimento.key("cpf").value(mensalista.getCpf());
            jsMovimento.endObject();

            URL url = new URL("http://"+MainActivity.ipServidor+":8080/mensalista");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsMovimento);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            resposta = scanner.nextLine();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Object o) {
        JSONObject jsonObject = null;
        Mensalista mensalistaResposta = new Mensalista();
        try {
            jsonObject = new JSONObject(resposta);
            mensalistaResposta.setCodMensalista(jsonObject.getInt("codMensalista"));
            mensalistaResposta.setCpf(jsonObject.getString("cpf"));
            mensalistaResposta.setEmail(jsonObject.getString("email"));
            mensalistaResposta.setNome(jsonObject.getString("nome"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // chamar outras tesks para cadastro do mensalista completo
        CadastroTelefoneMensalista cadastroTelefoneMensalista = new CadastroTelefoneMensalista( telefone,  mensalistaResposta);
        cadastroTelefoneMensalista.execute();

        CadastroEnderecoMensalista cadastroEnderecoMensalista = new  CadastroEnderecoMensalista(endereco,  mensalistaResposta);
        cadastroEnderecoMensalista.execute();

        CadastroVeiculo cadastroVeiculo = new CadastroVeiculo( veiculo,  mensalistaResposta);
        cadastroVeiculo.execute();

    }
}
