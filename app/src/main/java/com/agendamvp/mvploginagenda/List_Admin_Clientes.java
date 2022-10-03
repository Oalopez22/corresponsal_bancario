package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.adaptadores.ListAdminClientsAdapter;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.util.ArrayList;

public class List_Admin_Clientes extends AppCompatActivity  implements  SearchView.OnQueryTextListener{
    DbLogin db;
    Usuario user;
    ImageView imgArrowback;
    ListAdminClientsAdapter adapter;
    TextView viewClientName,viewClientCc,viewClientBalance;
    SearchView SvClient;
    RecyclerView RviewClients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_admin_clientes);
        db = new DbLogin(List_Admin_Clientes.this);
        user = new Usuario();
        viewClientName = findViewById(R.id.txtListClientName);
        viewClientCc = findViewById(R.id.txtListCcClient);
        viewClientBalance = findViewById(R.id.txtListBalanceClient);
        SvClient = findViewById(R.id.svClientCC);

        imgArrowback = findViewById(R.id.imgArrowBackAdmin);
        imgArrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RviewClients = findViewById(R.id.RviewListClients);
        RviewClients.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdminClientsAdapter(db.listadoClientes());
        RviewClients.setAdapter(adapter);
        SvClient.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.buscarcliente(newText);
        return false;
    }
}