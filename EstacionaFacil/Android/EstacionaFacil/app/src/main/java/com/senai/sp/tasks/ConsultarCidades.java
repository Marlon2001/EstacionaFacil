package com.senai.sp.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.senai.sp.estacionafacil.CadastroEnderecoMensalistaActivity;
import com.senai.sp.estacionafacil.CadastroNovoEndereco;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Cidade;

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

    private Context context;
    private ArrayList<Cidade> listCidades;
    private int codEstado;
    private String activityCalling;

    public ConsultarCidades(Context context, int codEstado, String activityCalling) {
        this.context = context;
        this.codEstado = codEstado;
        this.activityCalling = activityCalling;
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
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, listCidades);

        // Condição para saber qual activity irá receber o spinner de estados
        if(activityCalling == "1001"){
            CadastroEnderecoMensalistaActivity.spinnerCidades.setAdapter(arrayAdapter);
        }else if(activityCalling == "1002"){
            CadastroNovoEndereco.spinnerCidades.setAdapter(arrayAdapter);
        }
    }
}
