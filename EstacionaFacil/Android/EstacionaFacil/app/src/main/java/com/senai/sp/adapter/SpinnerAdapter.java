package com.senai.sp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.senai.sp.estacionafacil.R;
import com.senai.sp.model.Estado;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Estado> listEstados;

    public SpinnerAdapter(Context context, ArrayList<Estado> listEstados) {
        this.context = context;
        this.listEstados = listEstados;
    }

    @Override
    public int getCount() {
        return listEstados.size();
    }

    @Override
    public Object getItem(int position) {
        return listEstados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listEstados.get(position).getCodEstado();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_estados, null);

        Estado estado = listEstados.get(position);

        TextView txtEstado = view.findViewById(R.id.txt_estado);
        txtEstado.setText(estado.getNomeEstado());

        return view;
    }
}
