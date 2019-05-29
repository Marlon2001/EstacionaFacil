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

public class CadastroVeiculoMensalista extends AsyncTask {

    private Veiculo veiculo;
    private Mensalista mensalista;

    public CadastroVeiculoMensalista(Veiculo veiculo, Mensalista mensalista){
        this.veiculo = veiculo;
        this.mensalista = mensalista;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsVeiculo = new JSONStringer();

        /* Modelo do json
            {
                "placa": "ASD-5854",
                "modelo": "FordK",
                "anoVeiculo": "2018",
                "fabricante": {
                    "codFabricante": 1
                },
                "codMensalista": {
                    "codMensalista": 1
                }
            }
        */

        try {
            jsVeiculo.object();
                jsVeiculo.key("placa").value(veiculo.getPlaca());
                jsVeiculo.key("modelo").value(veiculo.getModelo());
                jsVeiculo.key("anoVeiculo").value(veiculo.getAnoVeiculo());
                jsVeiculo.key("fabricante").object()
                        .key("codFabricante").value(veiculo.getCodFabricante())
                .endObject();
                jsVeiculo.key("codMensalista").object()
                        .key("codMensalista").value(mensalista.getCodMensalista())
                .endObject();
            jsVeiculo.endObject();

            Log.d("VEICULO --- ", jsVeiculo.toString());

            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/veiculo");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsVeiculo);

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
        return null;
    }
}
