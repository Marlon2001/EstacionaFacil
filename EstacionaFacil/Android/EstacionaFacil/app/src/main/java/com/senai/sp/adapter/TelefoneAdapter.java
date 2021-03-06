package com.senai.sp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.senai.sp.estacionafacil.R;
import com.senai.sp.estacionafacil.TelefoneMensalistaActivity;
import com.senai.sp.model.Telefone;
import com.senai.sp.tasks.CarregarTelefoneMensalista;
import com.senai.sp.tasks.ExcluirTelefone;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TelefoneAdapter extends BaseAdapter {

    private TelefoneMensalistaActivity telefoneMensalistaActivity;
    private List<Telefone> listTelefones;
    private int codMensalista;

    public TelefoneAdapter(TelefoneMensalistaActivity telefoneMensalistaActivity, List<Telefone> listTelefones, int codMensalista) {
        this.telefoneMensalistaActivity = telefoneMensalistaActivity;
        this.listTelefones = listTelefones;
        this.codMensalista = codMensalista;
    }

    @Override
    public int getCount() {
        return listTelefones.size();
    }

    @Override
    public Object getItem(int position) {
        return listTelefones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listTelefones.get(position).getCodTelefone();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(telefoneMensalistaActivity);
        View view = inflater.inflate(R.layout.lista_telefone, null);

        final Telefone telefone = listTelefones.get(position);

        TextView txtTipoTelefone = view.findViewById(R.id.txt_tipo_telefone);
        TextView txtTelefone = view.findViewById(R.id.txt_telefone);
        Button btnDeletar = view.findViewById(R.id.btn_deletar_telefone);

        txtTipoTelefone.setText(telefone.getTipoTelefone());
        txtTelefone.setText(telefone.getTelefone());

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alBuilder = new AlertDialog.Builder(telefoneMensalistaActivity);
                alBuilder.setTitle("Excluir telefone.");
                alBuilder.setMessage(Html.fromHtml("Tem certeza que deseja excluir o telefone <b>"
                        + telefone.getTelefone() + "</b>?"));
                alBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExcluirTelefone excluirTelefone = new ExcluirTelefone(telefone.getCodTelefone());

                        try {
                            excluirTelefone.execute().get();

                            CarregarTelefoneMensalista carregarTelefoneMensalista = new CarregarTelefoneMensalista(telefoneMensalistaActivity, codMensalista);
                            carregarTelefoneMensalista.execute();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alBuilder.setNegativeButton("Não", null);
                alBuilder.setIcon(R.drawable.ic_alert);
                alBuilder.show();
            }
        });
        return view;
    }
}
