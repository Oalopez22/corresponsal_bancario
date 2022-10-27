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
import java.util.List;
import java.util.stream.Collectors;

public class HistoryCopTransactionsAdapter extends RecyclerView.Adapter<HistoryCopTransactionsAdapter.DataViewHolder> {
    ArrayList<Usuario> datos;
    ArrayList<Usuario> busqueda;
    public HistoryCopTransactionsAdapter(ArrayList<Usuario> datos){
        this.datos = datos;
        this.busqueda = new ArrayList<>();
        busqueda.addAll(datos);
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_cop,null, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.tvfecha.setText(datos.get(position).getCorresponsal_transaccion_fecha());
        holder.tvtipotransaccion.setText(datos.get(position).getCorresponsa_transaccion_type());
        holder.tvmonto.setText(datos.get(position).getCorresponsal_transaccion_value());
        holder.tvCuentaRef.setText(datos.get(position).getCorresponsal_transaccion_ref());
        holder.tvid.setText(datos.get(position).getCorresponsal_transaccion_id());
    }

    public void filtrado(String texto){
        int longitud = texto.length();
        if (longitud == 0){
            datos.clear();
            datos.addAll(busqueda);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Usuario> coleccion = datos.stream()
                        .filter(i ->i.getCorresponsal_transaccion_ref().toLowerCase().contains(texto.toLowerCase()))
                        .collect(Collectors.toList());
                datos.clear();
                datos.addAll(coleccion);
            }else {
                for (Usuario us: busqueda){
                    if (us.getCorresponsa_transaccion_type().toLowerCase().contains(texto.toLowerCase())){
                        datos.add(us);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvfecha,tvtipotransaccion,tvmonto,tvCuentaRef,tvid;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvfecha = itemView.findViewById(R.id.txtHistoryDate);
            tvtipotransaccion = itemView.findViewById(R.id.txtHistoryTransferType);
            tvmonto = itemView.findViewById(R.id.txtHistoryBalance);
            tvCuentaRef = itemView.findViewById(R.id.txtHistoryRef);
            tvid = itemView.findViewById(R.id.txtHistoryId);
        }
    }
}
