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

public class DataAdminCopAdapter extends RecyclerView.Adapter<DataAdminCopAdapter.DataViewHolder> {
    ArrayList<Usuario> DataAdminCop;

    public DataAdminCopAdapter(ArrayList<Usuario> datacop){
        this.DataAdminCop = datacop;
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listadatacopadmin,null,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
            holder.viewNameCop.setText(DataAdminCop.get(position).getCorresponsal_name());
            holder.viewNitCop.setText(DataAdminCop.get(position).getCorresponsal_nit());
/*            holder.viewSaldoCop.setText(DataAdminCop.get(position).getSaldo());*/
            holder.viewEmailCop.setText(DataAdminCop.get(position).getCorresponsal_email());
    }

    @Override
    public int getItemCount() {
        return DataAdminCop.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView viewNameCop,viewNitCop,viewSaldoCop,viewEmailCop;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNameCop = itemView.findViewById(R.id.txtDataCopName);
            viewNitCop = itemView.findViewById(R.id.txtDataCopNit);
            viewSaldoCop = itemView.findViewById(R.id.txtDataSaldoCop);
            viewEmailCop = itemView.findViewById(R.id.txtDataEmailCop);
        }
    }
}
