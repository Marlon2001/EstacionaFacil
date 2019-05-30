package com.senai.sp.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.senai.sp.adapter.MensalistaAdapter;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.estacionafacil.VisualizarMensalistasActivity;
import com.senai.sp.model.Mensalista;
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

public class CarregarListaMensalistas extends AsyncTask {

    private VisualizarMensalistasActivity visualizarMensalistasActivity;
    private List<Mensalista> listMensalistas;
    private ProgressDialog progressDialog;

    public CarregarListaMensalistas(VisualizarMensalistasActivity visualizarMensalistasActivity) {
        this.visualizarMensalistasActivity = visualizarMensalistasActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(visualizarMensalistasActivity);
        progressDialog.setTitle("Carregando mensalistas...");
        progressDialog.setMessage("Aguarde uns instantes.");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/mensalista");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conexao.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = "";
            String dados = "";

            while (linha != null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }

            JSONArray jsonArray = new JSONArray(dados);
            listMensalistas = new ArrayList<>();
            Mensalista mensalista;

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jo = (JSONObject) jsonArray.get(i);
                mensalista = new Mensalista();
                mensalista.setCodMensalista(jo.getInt("codMensalista"));
                mensalista.setNome(jo.getString("nome"));
                mensalista.setCpf(jo.getString("cpf"));
                mensalista.setEmail(jo.getString("email"));
                listMensalistas.add(mensalista);
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

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        MensalistaAdapter mensalistaAdapter = new MensalistaAdapter(visualizarMensalistasActivity, listMensalistas);
        VisualizarMensalistasActivity.listMensalistas.setAdapter(mensalistaAdapter);
        progressDialog.dismiss();
    }
}
