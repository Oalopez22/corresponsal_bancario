package com.agendamvp.mvploginagenda.adaptadores;

import android.os.Build;
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

public class ListAdminClientsAdapter extends RecyclerView.Adapter<ListAdminClientsAdapter.DataViewHolder> {
    ArrayList<Usuario> ListAdminClients;
    ArrayList<Usuario> SearchClient;
    public ListAdminClientsAdapter(ArrayList<Usuario> listClients){
        this.ListAdminClients = listClients;
        SearchClient = new ArrayList<>();
        SearchClient.addAll(listClients);
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_clientes_admin,null,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
            holder.viewClientName.setText(ListAdminClients.get(position).getNombre());
            holder.viewClientCc.setText(ListAdminClients.get(position).getDocumento());
            holder.viewClientAcout.setText(ListAdminClients.get(position).getCard_number());
            holder.viewBalanceClient.setText(String.valueOf(ListAdminClients.get(position).getSaldo()));
    }
    public  void buscarcliente(final String cc){
        int longitud = cc.length();
        if (longitud ==0){
            ListAdminClients.clear();
            ListAdminClients.addAll(SearchClient);
        }else {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
                List<Usuario> coleccion = ListAdminClients.stream().filter(i -> i.getDocumento().toLowerCase().contains(cc.toLowerCase())).collect(Collectors.toList());
                ListAdminClients.clear();
                ListAdminClients.addAll(coleccion);
            }else {
                ListAdminClients.clear();
                for (Usuario us: ListAdminClients){
                    if (us.getDocumento().toLowerCase().contains(cc.toLowerCase())){
                        ListAdminClients.add(us);
                    }
                }
            }
     }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ListAdminClients.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView viewClientName,viewClientCc,viewBalanceClient,viewClientAcout;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            viewClientName = itemView.findViewById(R.id.txtListClientName);
            viewClientCc = itemView.findViewById(R.id.txtListCcClient);
            viewBalanceClient = itemView.findViewById(R.id.txtListBalanceClient);
            viewClientAcout = itemView.findViewById(R.id.txtListAcout);
        }
    }
}
