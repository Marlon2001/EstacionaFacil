package com.senai.sp.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Mensalista;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CadastroMensalista extends AsyncTask {

    private Mensalista mensalista;
    private String resposta;

    public  CadastroMensalista(Context context, Mensalista mensalista){
        this.mensalista = mensalista;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsMensalista = new JSONStringer();

        try {
            jsMensalista.object();
            jsMensalista.key("nome").value(mensalista.getNome());
            jsMensalista.key("email").value(mensalista.getEmail());
            jsMensalista.key("cpf").value(mensalista.getCpf());
            jsMensalista.endObject();

            URL url = new URL("http://"+MainActivity.ipServidor+":8080/mensalista");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsMensalista);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            resposta = scanner.nextLine();

            JSONObject jsonObject = null;

            jsonObject = new JSONObject(resposta);
            mensalista.setCodMensalista(jsonObject.getInt("codMensalista"));
            mensalista.setCpf(jsonObject.getString("cpf"));
            mensalista.setEmail(jsonObject.getString("email"));
            mensalista.setNome(jsonObject.getString("nome"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensalista;
    }
}
