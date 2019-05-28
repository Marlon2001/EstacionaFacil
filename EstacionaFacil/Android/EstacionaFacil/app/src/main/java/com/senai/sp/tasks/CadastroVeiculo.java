package com.senai.sp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.senai.sp.estacionafacil.MainActivity;
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

public class CadastroVeiculo extends AsyncTask {
    Veiculo veiculo;
    Mensalista mensalista;
    public CadastroVeiculo(Veiculo veiculo, Mensalista mensalista){
        this.veiculo = veiculo;
        this.mensalista = mensalista;
    }

    @Override
    protected Object doInBackground(Object[] objects) {



        JSONStringer jsMovimento = new JSONStringer();

        //modelo do json requisitado
//        {
//
//            "placa": "ASD-5854",
//            "modelo": "FordK",
//            "anoVeiculo": "2018",
//            "fabricante": {
//            "codFabricante": 1
//        },
//            "codMensalista": {
//            "codMensalista": 1
//        }
//        }

        try {
            jsMovimento.object();
                jsMovimento.key("placa").value(veiculo.getPlaca());
                jsMovimento.key("modelo").value(veiculo.getModelo());
                jsMovimento.key("anoVeiculo").value(veiculo.getAnoVeiculo());
                jsMovimento.key("codFabricante").object().key("codFabricante").value(veiculo.getCodFabricante()).endObject();
                jsMovimento.key("codMensalista").object().key("codMensalista").value(mensalista.getCodMensalista()).endObject();
            jsMovimento.endObject();

            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/veiculo");

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
