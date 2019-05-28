package com.senai.sp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Endereco;
import com.senai.sp.model.Mensalista;
import com.senai.sp.model.Veiculo;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CadastroEnderecoMensalista extends AsyncTask {


    Endereco endereco;
    Mensalista mensalista;
    public CadastroEnderecoMensalista(Endereco endereco, Mensalista mensalista){
        this.endereco = endereco;
        this.mensalista = mensalista;
    }

    @Override
    protected Object doInBackground(Object[] objects) {



        JSONStringer jsMovimento = new JSONStringer();

        //modelo do json requisitado
//        {
//            "codMensalista": {
//            "codMensalista": 1
//        },
//            "codEndereco": {
//            "logradouro": "Rua eulália",
//                    "numero": "38",
//                    "bairro": "JD Julieta",
//                    "codCidade": {
//                "codCidade": 5
//            }
//        },
//            "descricao": "Minha casa"
//        }
        try {
            jsMovimento.object();
                jsMovimento.key("codMensalista").object().key("codMensalista").value(mensalista.getCodMensalista()).endObject();
                jsMovimento.key("codEndereco").object().key("logradouro").value(endereco.getLogradouro());
                jsMovimento.key("codEndereco").object().key("logradouro").value(endereco.getLogradouro())
                    .key("logradouro").value(endereco.getLogradouro())
                    .key("bairro").value(endereco.getLogradouro())
                    .key("codCidade").object()
                        .key("codCidade").value(endereco.getCidade()).endObject()
                    .endObject();
            jsMovimento.key("descricao").value(endereco.getDescricao());
            jsMovimento.endObject();

            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/enderecoMensalista");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsMovimento);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("33333333333333333", "chegou 3!!!!!!!!!!!!!");
        return null;

    }
}
