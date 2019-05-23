package com.senai.sp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.senai.sp.estacionafacil.ComprovanteActivity;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Movimentacao;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CadastrarSaida extends AsyncTask{

    private Context context;
    private Movimentacao movimentacao;
    private ProgressDialog progressDialog;
    private String resposta;

    public CadastrarSaida(Context context, Movimentacao movimentacao) {this.context = context; this.movimentacao = movimentacao;}

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Carregando...");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+MainActivity.ipServidor+":8080/movimentacao/saida/"+movimentacao.getId());

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("PUT");
            conexao.setDoInput(true);
            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            resposta = scanner.nextLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        Movimentacao movimentacao = new Movimentacao();

        try {
            JSONObject json = new JSONObject(resposta);
            movimentacao.setModelo(json.getString("modelo"));
            movimentacao.setPlaca(json.getString("placa"));
            movimentacao.setDataEntrada(json.getString("dataEntrada"));
            movimentacao.setDataSaida(json.getString("dataSaida"));
            movimentacao.setValorPago(Double.parseDouble(json.getString("valorPago")));
            movimentacao.setTempoPermanencia(json.getString("tempoPermanencia"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent abrirComprovante = new Intent(context, ComprovanteActivity.class);
        abrirComprovante.putExtra("movimentacao", movimentacao);
        context.startActivity(abrirComprovante);
        progressDialog.dismiss();
    }
}
