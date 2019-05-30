package com.senai.sp.tasks;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.senai.sp.estacionafacil.CadastroEnderecoMensalistaActivity;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Estado;

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

public class ConsultarEstados extends AsyncTask {

    private ArrayList<Estado> listEstados;
    private CadastroEnderecoMensalistaActivity cadastroEndereco;

    public ConsultarEstados(CadastroEnderecoMensalistaActivity cadastroEndereco) {
        this.cadastroEndereco = cadastroEndereco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/estado");

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
            listEstados = new ArrayList<>();
            Estado estado;

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                estado = new Estado();
                estado.setCodEstado(jsonObject.getInt("codEstado"));
                estado.setNomeEstado(jsonObject.getString("nomeEstado"));
                estado.setSigla(jsonObject.getString("sigla"));
                listEstados.add(estado);
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
        ArrayAdapter arrayAdapter = new ArrayAdapter(cadastroEndereco, android.R.layout.simple_list_item_1, listEstados);
        CadastroEnderecoMensalistaActivity.spinnerEstado.setAdapter(arrayAdapter);
        super.onPostExecute(o);
    }
}
