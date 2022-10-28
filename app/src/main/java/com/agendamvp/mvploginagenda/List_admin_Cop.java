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
import com.agendamvp.mvploginagenda.adaptadores.ListAdminCopsAdapter;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class List_admin_Cop extends AppCompatActivity implements SearchView.OnQueryTextListener {
    DbLogin db;
    Usuario user;
    ImageView imgArrowBack;
    ListAdminCopsAdapter adapter;
    TextView viewCopName,viewCopNit,viewCopStatus,viewCopBalance,viewCopNacount;
    SearchView svCop;
    RecyclerView RviewCops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_admin_cop);
        db = new DbLogin(List_admin_Cop.this);
        user = new Usuario();
        viewCopName = findViewById(R.id.txtCopsName);
        viewCopNit = findViewById(R.id.txtCopsNit);
        viewCopStatus = findViewById(R.id.txtCopsStatus);
        viewCopBalance = findViewById(R.id.txtLisBalanceCop);
        viewCopNacount = findViewById(R.id.txtCopsNumber);
        svCop = findViewById(R.id.svCops);
        imgArrowBack = findViewById(R.id.imgArrowBackAdmin);
        RviewCops = findViewById(R.id.RviewCops);
        RviewCops.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdminCopsAdapter(db.listadoCorresponsales());
        RviewCops.setAdapter(adapter);
        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}