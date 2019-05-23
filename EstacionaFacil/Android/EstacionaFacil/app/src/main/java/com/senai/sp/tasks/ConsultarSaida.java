package com.senai.sp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.senai.sp.estacionafacil.ComprovanteActivity;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.estacionafacil.VisualizarSaida;
import com.senai.sp.model.Movimentacao;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConsultarSaida extends AsyncTask {

    private Context context;
    private Movimentacao movimentacao;

    public ConsultarSaida(Context context, Movimentacao movimentacao){
        this.context = context;
        this.movimentacao = movimentacao;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+MainActivity.ipServidor+":8080/movimentacao/orcamento/"+this.movimentacao.getId());

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conexao.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = "";
            String dados = "";

            if (linha != null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }

            JSONObject jsonObject = new JSONObject(dados);
            movimentacao = new Movimentacao();
            movimentacao.setId(jsonObject.getInt("codMovimentacao"));
            movimentacao.setPlaca(jsonObject.getString("placa"));
            movimentacao.setModelo(jsonObject.getString("modelo"));
            movimentacao.setDataEntrada(jsonObject.getString("dataEntrada"));
            movimentacao.setDataSaida(jsonObject.getString("dataSaida"));
            movimentacao.setValorPago(Double.parseDouble(jsonObject.getString("valorPago")));
            movimentacao.setTempoPermanencia(jsonObject.getString("tempoPermanencia"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        Intent visualizarSaida = new Intent(context, VisualizarSaida.class);
        visualizarSaida.putExtra("movimentacao", movimentacao);
        context.startActivity(visualizarSaida);
    }
}
