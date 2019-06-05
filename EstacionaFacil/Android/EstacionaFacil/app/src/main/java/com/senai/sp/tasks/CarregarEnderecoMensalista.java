package com.senai.sp.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.senai.sp.adapter.EnderecoAdapter;
import com.senai.sp.adapter.TelefoneAdapter;
import com.senai.sp.estacionafacil.EnderecosMensalistaActivity;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.estacionafacil.TelefoneMensalistaActivity;
import com.senai.sp.model.Endereco;
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

public class CarregarEnderecoMensalista extends AsyncTask {

    private EnderecosMensalistaActivity enderecosMensalistaActivity;
    private ProgressDialog progressDialog;
    private List<Endereco> listEnderecos;
    private int codMensalista;

    public CarregarEnderecoMensalista(EnderecosMensalistaActivity enderecosMensalistaActivity, int codMensalista) {
        this.enderecosMensalistaActivity = enderecosMensalistaActivity;
        this.codMensalista = codMensalista;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(enderecosMensalistaActivity);
        progressDialog.setTitle("Carregando lista de enderecos...");
        progressDialog.setMessage("Aguarde alguns instantes.");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://"+MainActivity.ipServidor+":8080/enderecoMensalista/"+codMensalista);

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
            listEnderecos = new ArrayList<>();
            Endereco endereco;

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jo = (JSONObject) jsonArray.get(i);
                endereco = new Endereco();
                endereco.setCodEndereco(jo.getJSONObject("codEndereco").getInt("codEndereco"));
                endereco.setLogradouro(jo.getJSONObject("codEndereco").getString("logradouro"));
                endereco.setNumero(jo.getJSONObject("codEndereco").getString("numero"));
                endereco.setBairro(jo.getJSONObject("codEndereco").getString("bairro"));
                endereco.setCidade(jo.getJSONObject("codEndereco").getJSONObject("codCidade").getInt("codCidade"));
                listEnderecos.add(endereco);
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
        EnderecoAdapter enderecoAdapter = new EnderecoAdapter(enderecosMensalistaActivity, listEnderecos, codMensalista);
        EnderecosMensalistaActivity.listEndereco.setAdapter(enderecoAdapter);
    }
}
