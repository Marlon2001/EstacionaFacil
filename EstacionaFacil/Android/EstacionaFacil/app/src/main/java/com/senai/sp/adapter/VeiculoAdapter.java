package com.senai.sp.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.senai.sp.estacionafacil.CadastroNovoVeiculo;
import com.senai.sp.estacionafacil.R;
import com.senai.sp.estacionafacil.VeiculoMensalistaActivity;
import com.senai.sp.model.Veiculo;
import com.senai.sp.tasks.CarregarVeiculoMensalista;
import com.senai.sp.tasks.ExcluirVeiculo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class VeiculoAdapter extends BaseAdapter {

    private VeiculoMensalistaActivity veiculoMensalistaActivity;
    private List<Veiculo> listVeiculos;
    private int codMensalista;

    public VeiculoAdapter(VeiculoMensalistaActivity veiculoMensalistaActivity, List<Veiculo> listVeiculos, int codMensalista) {
        this.veiculoMensalistaActivity = veiculoMensalistaActivity;
        this.listVeiculos = listVeiculos;
        this.codMensalista = codMensalista;
    }

    @Override
    public int getCount() {
        return listVeiculos.size();
    }

    @Override
    public Object getItem(int position) {
        return listVeiculos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listVeiculos.get(position).getCodVeiculo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(veiculoMensalistaActivity);
        View view = inflater.inflate(R.layout.lista_veiculos, null);

        final Veiculo veiculo = listVeiculos.get(position);

        TextView txtPlaca = view.findViewById(R.id.txt_placa_veiculo3);
        TextView txtModelo = view.findViewById(R.id.txt_modelo_veiculo3);
        Button btnDeletar = view.findViewById(R.id.btn_deletar_veiculo);
        LinearLayout linearLayoutEditar = view.findViewById(R.id.layout_veiculo_editar);

        txtPlaca.setText(veiculo.getPlaca());
        txtModelo.setText(veiculo.getModelo());

        linearLayoutEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCadastroVeiculo = new Intent(veiculoMensalistaActivity, CadastroNovoVeiculo.class);

                abrirCadastroVeiculo.putExtra("veiculo", veiculo);
                abrirCadastroVeiculo.putExtra("codVeiculo", veiculo.getCodVeiculo());

                abrirCadastroVeiculo.putExtra("codMensalista", codMensalista);

                veiculoMensalistaActivity.startActivity(abrirCadastroVeiculo);
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final AlertDialog.Builder alBuilder = new AlertDialog.Builder(veiculoMensalistaActivity);
            alBuilder.setTitle("Excluir veiculo.");
            alBuilder.setMessage(Html.fromHtml("Tem certeza que deseja excluir o veiculo <b>"
                    + veiculo.getPlaca() + "</b>?"));
            alBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                ExcluirVeiculo excluirVeiculo = new ExcluirVeiculo(veiculo.getCodVeiculo());

                try {
                    excluirVeiculo.execute().get();

                    CarregarVeiculoMensalista carregarVeiculoMensalista = new CarregarVeiculoMensalista(veiculoMensalistaActivity, codMensalista);
                    carregarVeiculoMensalista.execute();
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
