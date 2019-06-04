package com.senai.sp.tasks;

import android.os.AsyncTask;

import com.senai.sp.estacionafacil.MainActivity;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExcluirTelefone extends AsyncTask {

    private int codTelefone;

    public ExcluirTelefone(int codTelefone) {
        this.codTelefone = codTelefone;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+MainActivity.ipServidor+":8080/telefone/"+codTelefone);

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
