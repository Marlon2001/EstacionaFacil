package com.senai.sp.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.senai.sp.adapter.VeiculoAdapter;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.estacionafacil.VeiculoMensalistaActivity;
import com.senai.sp.model.Veiculo;

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

public class CarregarVeiculoMensalista extends AsyncTask {

    private VeiculoMensalistaActivity veiculoMensalistaActivity;
    private ProgressDialog progressDialog;
    private List<Veiculo> listVeiculo;
    private int codMensalista;

    public CarregarVeiculoMensalista(VeiculoMensalistaActivity veiculoMensalistaActivity, int codMensalista) {
        this.veiculoMensalistaActivity = veiculoMensalistaActivity;
        this.codMensalista = codMensalista;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(veiculoMensalistaActivity);
        progressDialog.setTitle("Carregando lista de veiculos...");
        progressDialog.setMessage("Aguarde alguns instantes.");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/veiculo/"+codMensalista);

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
            listVeiculo = new ArrayList<>();
            Veiculo veiculo;

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jo = (JSONObject) jsonArray.get(i);
                veiculo = new Veiculo();
                veiculo.setCodVeiculo(jo.getInt("codVeiculo"));
                veiculo.setPlaca(jo.getString("placa"));
                veiculo.setModelo(jo.getString("modelo"));
                veiculo.setAnoVeiculo(jo.getString("anoVeiculo"));
                veiculo.setCodFabricante(jo.getJSONObject("fabricante").getInt("codFabricante"));
                listVeiculo.add(veiculo);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listVeiculo;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
        VeiculoAdapter veiculoAdapter = new VeiculoAdapter(veiculoMensalistaActivity, listVeiculo, codMensalista);
        VeiculoMensalistaActivity.listVeiculos.setAdapter(veiculoAdapter);
    }
}
