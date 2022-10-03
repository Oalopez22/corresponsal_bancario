package com.agendamvp.mvploginagenda.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.R;

import java.util.ArrayList;

public class ListAdminCopsAdapter extends RecyclerView.Adapter<ListAdminCopsAdapter.DataViewHolder> {
    ArrayList<Usuario> listadoCops;

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
/*            holder.viewCopsStatus.setText(listadoCops.get(position).getCorresponsal_status());*/
    }

    @Override
    public int getItemCount() {
        return listadoCops.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView viewCopsname,viewCopsNumber,viewCopsNit,viewCopsStatus;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            viewCopsname = itemView.findViewById(R.id.txtCopsName);
            viewCopsNumber = itemView.findViewById(R.id.txtCopsNumber);
            viewCopsNit = itemView.findViewById(R.id.txtCopsNit);
            viewCopsStatus = itemView.findViewById(R.id.txtCopsStatus);
        }
    }
}
