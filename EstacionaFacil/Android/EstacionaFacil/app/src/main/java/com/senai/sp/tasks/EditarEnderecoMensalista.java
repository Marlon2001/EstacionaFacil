package com.senai.sp.tasks;

import android.os.AsyncTask;

import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.model.Endereco;
import com.senai.sp.model.Mensalista;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class EditarEnderecoMensalista extends AsyncTask {

    private Endereco endereco;
    private Mensalista mensalista;

    public EditarEnderecoMensalista(Endereco endereco, Mensalista mensalista) {
        this.endereco = endereco;
        this.mensalista = mensalista;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsEndereco = new JSONStringer();

        /* Modelo do json
            {
                "codMensalista": {
                    "codMensalista": 1
                },
                "codEndereco": {
                    "codEndereco": 5,
                    "logradouro": "Rua eulália",
                    "numero": "38",
                    "bairro": "JD Julieta",
                    "codCidade": {
                        "codCidade": 5
                    }
                },
                "descricao": "Minha casa"
            }
        */

        try {
            jsEndereco.object();
                jsEndereco.key("codMensalista").object()
                    .key("codMensalista").value(mensalista.getCodMensalista())
                .endObject();
                jsEndereco.key("codEndereco").object()
                    .key("codEndereco").value(endereco.getCodEndereco())
                    .key("logradouro").value(endereco.getLogradouro())
                    .key("numero").value(endereco.getNumero())
                    .key("bairro").value(endereco.getLogradouro())
                    .key("codCidade").object()
                        .key("codCidade").value(endereco.getCidade())
                    .endObject()
                .endObject();
                jsEndereco.key("descricao").value(endereco.getDescricao());
            jsEndereco.endObject();

            URL url = new URL("http://"+MainActivity.ipServidor+":8080/enderecoMensalista/");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsEndereco);

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
        return null;
    }
}
