package com.senai.sp.tasks;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Movimentacao;
import com.senai.sp.model.Preco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConsultarPreco extends AsyncTask{

    private Preco preco;
    private TextView txtPrimeiraHora;
    private TextView txtDemaisHoras;
    private TextView txtVaga;
    private TextView txtDiaria;

    public ConsultarPreco(TextView txtPrimeiraHora, TextView txtDemaisHoras, TextView txtDiaria, TextView txtVaga){
        this.txtPrimeiraHora = txtPrimeiraHora;
        this.txtDemaisHoras = txtDemaisHoras;
        this.txtDiaria = txtDiaria;
        this.txtVaga = txtVaga;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/precos/precoVigente");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            InputStream inputStream = conexao.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String dados = "";
            String linha = "";

            if (linha != null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }

            JSONObject jsonObject = new JSONObject(dados);
            preco = new Preco();
            preco.setId(jsonObject.getLong("codPreco"));
            preco.setValorHora1(jsonObject.getDouble("valorHora1"));
            preco.setValorDemaisHoras(jsonObject.getDouble("valorDemaisHoras"));
            preco.setDataFim(jsonObject.getString("dataSaida"));
            preco.setValorVaga(jsonObject.getDouble("valorVaga"));
            preco.setValorDiaria(jsonObject.getDouble("valorDiaria"));

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
        txtPrimeiraHora.setText( preco.getValorHora1().toString());
        txtDemaisHoras.setText( preco.getValorDemaisHoras().toString());
        txtVaga.setText(preco.getValorVaga().toString());
        txtDiaria.setText(preco.getValorDiaria().toString());
    }
}
