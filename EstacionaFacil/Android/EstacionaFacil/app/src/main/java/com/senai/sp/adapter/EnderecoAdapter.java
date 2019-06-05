package com.senai.sp.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.senai.sp.estacionafacil.EnderecosMensalistaActivity;
import com.senai.sp.estacionafacil.R;
import com.senai.sp.model.Endereco;
import com.senai.sp.tasks.CarregarEnderecoMensalista;
import com.senai.sp.tasks.CarregarTelefoneMensalista;
import com.senai.sp.tasks.ExcluirEndereco;
import com.senai.sp.tasks.ExcluirTelefone;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EnderecoAdapter extends BaseAdapter {

    private EnderecosMensalistaActivity enderecosMensalistaActivity;
    private List<Endereco> listEndereco;
    private int codMensalista;

    public EnderecoAdapter(EnderecosMensalistaActivity enderecosMensalistaActivity, List<Endereco> listEndereco, int codMensalista) {
        this.enderecosMensalistaActivity = enderecosMensalistaActivity;
        this.listEndereco = listEndereco;
        this.codMensalista = codMensalista;
    }

    @Override
    public int getCount() {
        return listEndereco.size();
    }

    @Override
    public Object getItem(int position) {
        return listEndereco.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listEndereco.get(position).getCodEndereco();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(enderecosMensalistaActivity);
        View view = inflater.inflate(R.layout.lista_enderecos, null);

        final Endereco endereco = listEndereco.get(position);

        String strEndereco = endereco.getLogradouro()
                + " - "
                + endereco.getBairro()
                + ", "
                + endereco.getNumero();

        Button btnDeletar = view.findViewById(R.id.btn_deletar_endereco);
        TextView txtEndereco = view.findViewById(R.id.txt_endereco);
        LinearLayout linearLayoutEditar = view.findViewById(R.id.layout_endereco_editar);

        txtEndereco.setText(strEndereco);

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alBuilder = new AlertDialog.Builder(enderecosMensalistaActivity);
                alBuilder.setTitle("Excluir endereço.");
                alBuilder.setMessage(Html.fromHtml("Tem certeza que deseja excluir o endereço <b>"
                        + endereco.getLogradouro() + "</b>?"));
                alBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExcluirEndereco excluirEndereco = new ExcluirEndereco(endereco.getCodEndereco());

                        try {
                            excluirEndereco.execute().get();


                            CarregarEnderecoMensalista carregarEnderecoMensalista = new CarregarEnderecoMensalista(enderecosMensalistaActivity, codMensalista);
                            carregarEnderecoMensalista.execute();
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
        linearLayoutEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
