package com.senai.sp.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.senai.sp.adapter.TelefoneAdapter;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.estacionafacil.TelefoneMensalistaActivity;
import com.senai.sp.model.Mensalista;
import com.senai.sp.model.Telefone;

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

public class CarregarTelefoneMensalista extends AsyncTask {

    private TelefoneMensalistaActivity telefoneMensalistaActivity;
    private ProgressDialog progressDialog;
    private List<Telefone> listTelefones;
    private int codMensalista;

    public CarregarTelefoneMensalista(TelefoneMensalistaActivity telefoneMensalistaActivity, int codMensalista) {
        this.telefoneMensalistaActivity = telefoneMensalistaActivity;
        this.codMensalista = codMensalista;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(telefoneMensalistaActivity);
        progressDialog.setTitle("Carregando lista de telefones...");
        progressDialog.setMessage("Aguarde alguns instantes.");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/telefoneMensalista/"+codMensalista);

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
            listTelefones = new ArrayList<>();
            Telefone telefone;

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jo = (JSONObject) jsonArray.get(i);
                telefone = new Telefone();
                telefone.setCodTelefone(jo.getJSONObject("codTelefone").getInt("codTelefone"));
                telefone.setTelefone(jo.getJSONObject("codTelefone").getString("telefone"));
                telefone.setTipoTelefone(jo.getString("tipoTelefone"));
                listTelefones.add(telefone);
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
        progressDialog.dismiss();
        TelefoneAdapter telefoneAdapter = new TelefoneAdapter(telefoneMensalistaActivity, listTelefones);
        TelefoneMensalistaActivity.listTelefone.setAdapter(telefoneAdapter);
    }
}