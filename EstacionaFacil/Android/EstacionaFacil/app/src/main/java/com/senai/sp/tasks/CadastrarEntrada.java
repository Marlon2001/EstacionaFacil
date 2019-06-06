package com.senai.sp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.senai.sp.estacionafacil.MainActivity;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CadastrarEntrada extends AsyncTask {

    private String txtPlaca;
    private String txtModelo;
    private String tipo;
    public CadastrarEntrada(String txtPlaca, String txtModelo, String tipo) {
        this.txtPlaca = txtPlaca;
        this.txtModelo = txtModelo;
        this.tipo = tipo;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsMovimento = new JSONStringer();

        try {
            jsMovimento.object();
            jsMovimento.key("placa").value(txtPlaca);
            jsMovimento.key("modelo").value(txtModelo);
            jsMovimento.key("tempo_permanencia").value(null);
            jsMovimento.key("valor_pago").value(null);
            jsMovimento.key("data_saida").value(null);
            jsMovimento.key("tipo").value(tipo);
            jsMovimento.endObject();

            URL url = new URL("http://"+MainActivity.ipServidor+":8080/movimentacao");

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
        return null;
    }
}
