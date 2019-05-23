package com.senai.sp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.senai.sp.estacionafacil.MainActivity;
import com.senai.sp.estacionafacil.MovimentacaoActivity;
import com.senai.sp.estacionafacil.R;
import com.senai.sp.model.Movimentacao;
import com.senai.sp.tasks.ConsultarSaida;

import java.util.List;

public class MovimentacaoAdapter extends BaseAdapter {

    private MovimentacaoActivity movimentacaoActivity;
    private List<Movimentacao> movimentacoes;

    public MovimentacaoAdapter(MovimentacaoActivity movimentacaoActivity, List<Movimentacao> movimentacoes){
        this.movimentacaoActivity = movimentacaoActivity;
        this.movimentacoes = movimentacoes;
    }

    @Override
    public int getCount() {
        return movimentacoes.size();
    }

    @Override
    public Object getItem(int position) {
        return  movimentacoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  movimentacoes.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(movimentacaoActivity);
        View view = inflater.inflate(R.layout.lista_carros, null);

        final Movimentacao movimentacao = new Movimentacao();
        movimentacao.setId(movimentacoes.get(position).getId());
        movimentacao.setPlaca(movimentacoes.get(position).getPlaca());
        movimentacao.setDataEntrada(movimentacoes.get(position).getDataEntrada());
        movimentacao.setModelo(movimentacoes.get(position).getModelo());

        Button btnSaida = view.findViewById(R.id.bt_saida);
        TextView txtPlaca = view.findViewById(R.id.txt_placa);
        TextView txtDataEntrada = view.findViewById(R.id.txt_data_entrada);
        TextView txtModelo =  view.findViewById(R.id.txt_modelo);

        txtPlaca.setText(movimentacao.getPlaca());
        txtDataEntrada.setText(movimentacao.getDataEntrada());
        txtModelo.setText(movimentacao.getModelo());

        btnSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ConsultarSaida consultarSaida = new ConsultarSaida(movimentacaoActivity, movimentacao);
            consultarSaida.execute();
            }
        });
        return view;
    }
}
