package com.senai.sp.tasks;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.senai.sp.estacionafacil.CadastroEndereco;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Cidade;
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

public class ConsultarCidades extends AsyncTask {

    private CadastroEndereco cadastroEndereco;
    private ArrayList<Cidade> listCidades;
    private int codEstado;

    public ConsultarCidades(CadastroEndereco cadastroEndereco, int codEstado) {
        this.cadastroEndereco = cadastroEndereco;
        this.codEstado = codEstado;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/cidades/"+codEstado);

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
            listCidades = new ArrayList<>();
            Cidade cidade;

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                cidade = new Cidade();
                cidade.setCodCidade(jsonObject.getInt("codCidade"));
                cidade.setNomeCidade(jsonObject.getString("nomeCidade"));
                listCidades.add(cidade);
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
        ArrayAdapter arrayAdapter = new ArrayAdapter(cadastroEndereco, android.R.layout.simple_list_item_1, listCidades);
        CadastroEndereco.spinnerCidades.setAdapter(arrayAdapter);
    }
}
