package com.senai.sp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Mensalista;
import com.senai.sp.model.Telefone;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CadastroTelefoneMensalista extends AsyncTask {

    Telefone telefone;
    Mensalista mensalista;

    public CadastroTelefoneMensalista(Telefone telefone, Mensalista mensalista){
        this.telefone = telefone;
        this.mensalista = mensalista;

    }


    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsMovimento = new JSONStringer();

            //modelo do json requisitado
//        {
//            "codMensalista": {
//            "codMensalista": 1
//        },
//            "codTelefone": {
//            "telefone": "77 77777-7777"
//        },
//            "tipoTelefone": "Fixo"
//        }

        try {
            jsMovimento.object();
            jsMovimento.key("codMensalista").object().key("codMensalista").value(mensalista.getCodMensalista()).endObject();
            jsMovimento.key("codTelefone").object().key("telefone").value(telefone.getTelefone()).endObject();
            jsMovimento.key("tipoTelefone").value(telefone.getTipoTelefone());
            jsMovimento.endObject();

            URL url = new URL("http://"+ MainActivity.ipServidor+":8080/telefoneMensalista");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsMovimento);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("33333333333333333", "chegou 2!!!!!!!!!!!!!");
        return null;

    }




}
