package com.senai.sp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.senai.sp.adapter.MovimentacaoAdapter;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.estacionafacil.MovimentacaoActivity;
import com.senai.sp.model.Movimentacao;
import com.senai.sp.utils.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CarregarListaMovimentacao extends AsyncTask {

    private MovimentacaoActivity movimentacaoActivity;
    private List<Movimentacao> listMovimentacao;
    private ProgressDialog progressDialog;
    private String dados = "";

    public CarregarListaMovimentacao (MovimentacaoActivity movimentacaoActivity){
        this.movimentacaoActivity = movimentacaoActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(movimentacaoActivity);
        progressDialog.setTitle("Carregando dados.");
        progressDialog.setMessage("Carregando... Aguarde.");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("http://"+MainActivity.ipServidor+":8080/movimentacao/estacionados");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conexao.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = "";

            while (linha != null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }

            JSONArray jsonArray = new JSONArray(dados);
            listMovimentacao = new ArrayList<>();
            Movimentacao movimentacao;

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jo = (JSONObject) jsonArray.get(i);
                movimentacao = new Movimentacao();
                movimentacao.setId(jo.getInt("codMovimentacao"));
                movimentacao.setPlaca(jo.getString("placa"));
                movimentacao.setModelo(jo.getString("modelo"));
                movimentacao.setDataEntrada(Data.converterParaPortugues(jo.getString("dataEntrada")));
                listMovimentacao.add(movimentacao);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        MovimentacaoAdapter adapterMovimentacao = new MovimentacaoAdapter(movimentacaoActivity, listMovimentacao);
        MovimentacaoActivity.listaMovimentacao.setAdapter(adapterMovimentacao);
        progressDialog.dismiss();
    }
}
