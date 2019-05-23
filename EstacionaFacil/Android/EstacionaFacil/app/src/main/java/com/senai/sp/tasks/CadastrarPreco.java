package com.senai.sp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.senai.sp.adapter.MovimentacaoAdapter;
import com.senai.sp.estacionafacil.CadastrarPrecoActivity;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Preco;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CadastrarPreco extends AsyncTask {

    private CadastrarPrecoActivity cadastrarPrecoActivity;
    private Preco preco;
    private ProgressDialog progressDialog;

    public CadastrarPreco(CadastrarPrecoActivity cadastrarPrecoActivity, Preco preco){
        this.cadastrarPrecoActivity = cadastrarPrecoActivity;
        this.preco = preco;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(cadastrarPrecoActivity);
        progressDialog.setTitle("Cadastrando Novo Preco.");
        progressDialog.setMessage("Cadastrando...");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsPreco = new JSONStringer();

        try {
            jsPreco.object();
            jsPreco.key("valorHora1").value(preco.getValorHora1());
            jsPreco.key("valorDemaisHoras").value(preco.getValorDemaisHoras());
            jsPreco.endObject();

            URL url = new URL("http://"+MainActivity.ipServidor+":8080/precos/cadastroPreco");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsPreco);

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

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
        cadastrarPrecoActivity.finish();
    }
}
