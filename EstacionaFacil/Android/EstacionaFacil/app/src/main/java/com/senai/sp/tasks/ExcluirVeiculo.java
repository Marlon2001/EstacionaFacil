package com.senai.sp.tasks;

import android.os.AsyncTask;

import com.senai.sp.estacionafacil.MainActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExcluirVeiculo extends AsyncTask {

    private int codVeiculo;

    public ExcluirVeiculo(int codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+MainActivity.ipServidor+":8080/veiculo/"+codVeiculo);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("DELETE");
            conexao.connect();
            conexao.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
