package com.senai.sp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.senai.sp.estacionafacil.R;
import com.senai.sp.estacionafacil.TelefoneMensalistaActivity;
import com.senai.sp.estacionafacil.VeiculoMensalistaActivity;
import com.senai.sp.estacionafacil.VisualizarMensalistasActivity;
import com.senai.sp.model.Mensalista;

import java.util.List;

public class MensalistaAdapter extends BaseAdapter {

    private VisualizarMensalistasActivity visualizarMensalistasActivity;
    private List<Mensalista> listMensalistas;

    public MensalistaAdapter(VisualizarMensalistasActivity visualizarMensalistasActivity, List<Mensalista> listMensalistas) {
        this.visualizarMensalistasActivity = visualizarMensalistasActivity;
        this.listMensalistas = listMensalistas;
    }

    @Override
    public int getCount() {
        return listMensalistas.size();
    }

    @Override
    public Object getItem(int position) {
        return listMensalistas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listMensalistas.get(position).getCodMensalista();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(visualizarMensalistasActivity);
        View view = layoutInflater.inflate(R.layout.lista_mensalistas, null);

        final Mensalista mensalista = listMensalistas.get(position);

        TextView txtNome = view.findViewById(R.id.txt_nome_mensalista);
        TextView txtCpf = view.findViewById(R.id.txt_cpf_mensalista);
        TextView txtEmail = view.findViewById(R.id.txt_email_mensalista);

        Button btnVeiculos = view.findViewById(R.id.btn_veiculos_mensalista);
        Button btnTelefone = view.findViewById(R.id.btn_telefone_mensalista);
        Button btnEndereco = view.findViewById(R.id.btn_endereco_mensalista);

        txtNome.setText(mensalista.getNome());
        txtCpf.setText(mensalista.getCpf());
        txtEmail.setText(mensalista.getEmail());

        btnVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirVeiculosMensalista = new Intent(visualizarMensalistasActivity, VeiculoMensalistaActivity.class);
                abrirVeiculosMensalista.putExtra("codMensalista", mensalista.getCodMensalista());

                visualizarMensalistasActivity.startActivity(abrirVeiculosMensalista);
            }
        });

        btnTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent abrirTelefonesMensalista = new Intent(visualizarMensalistasActivity, TelefoneMensalistaActivity.class);
            abrirTelefonesMensalista.putExtra("codMensalista", mensalista.getCodMensalista());

            visualizarMensalistasActivity.startActivity(abrirTelefonesMensalista);
            }
        });

        btnEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
