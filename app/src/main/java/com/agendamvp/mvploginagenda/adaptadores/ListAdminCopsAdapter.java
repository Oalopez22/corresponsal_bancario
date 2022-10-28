package com.agendamvp.mvploginagenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agendamvp.mvploginagenda.DataAdminCop;
import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.R;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

import java.util.ArrayList;

public class ListAdminCopsAdapter extends RecyclerView.Adapter<ListAdminCopsAdapter.DataViewHolder> {
    ArrayList<Usuario> listadoCops;
    SharedPreferences sp;

    public ListAdminCopsAdapter(ArrayList<Usuario> listaCops){
        this.listadoCops = listaCops;
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cops_admin,null,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
            holder.viewCopsname.setText(listadoCops.get(position).getCorresponsal_name());
            holder.viewCopsNit.setText(listadoCops.get(position).getCorresponsal_nit());
            holder.viewCopsStatus.setText(String.valueOf(listadoCops.get(position).getCorresponsal_status()));
            holder.viewCopsBalance.setText(String.valueOf(listadoCops.get(position).getCorresponsal_balance()));
            int estado = listadoCops.get(position).getCorresponsal_status();
            String valor = String.valueOf(estado);
            char dato = valor.charAt(0);
            switch (dato){
                case '0':
                    valor = "Habilitado";
                break;
                case '1':
                    valor = "Deshabilitado";
                    break;
                default:
                    valor = "";
            }
                    holder.viewCopsStatus.setText(valor);
                    holder.viewCopsNumber.setText(String.valueOf(listadoCops.get(position).getCorreponsal_ncuenta()));

    }

    @Override
    public int getItemCount() {
        return listadoCops.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView viewCopsname,viewCopsNumber,viewCopsNit,viewCopsStatus,viewCopsBalance;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            viewCopsname = itemView.findViewById(R.id.txtCopsName);
            viewCopsNumber = itemView.findViewById(R.id.txtCopsNumber);
            viewCopsNit = itemView.findViewById(R.id.txtCopsNit);
            viewCopsStatus = itemView.findViewById(R.id.txtCopsStatus);
            viewCopsBalance = itemView.findViewById(R.id.txtLisBalanceCop);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    sp = new SharedPreferences(context);
                    Intent intent = new Intent(context, DataAdminCop.class);
                    intent.putExtra("ID",listadoCops.get(getAdapterPosition()).getCorresponsal_nit());
                    context.startActivity(intent);
                }
            });
        }
    }
}
