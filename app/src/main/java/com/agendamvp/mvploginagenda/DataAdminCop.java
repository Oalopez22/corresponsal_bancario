package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDataCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.adaptadores.DataAdminCopAdapter;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.util.ArrayList;

public class DataAdminCop extends AppCompatActivity implements InterfacesDataCop.view {
    DbLogin db;
    Usuario user;
    TextView viewDataNameCop,viewDataNitCop,viewDataSaldoCop,viewDataEmailCop;
    SharedPreferences sp;
    DataAdminCopAdapter adapter;
    ImageView imgarrowback;
    RecyclerView RviewDataCop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_admin_cop);
        findElements();
        db = new DbLogin(DataAdminCop.this);
        user = new Usuario();
        sp = new SharedPreferences(DataAdminCop.this);
        RviewDataCop.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataAdminCopAdapter(db.mostrarDataCop(sp));
        RviewDataCop.setAdapter(adapter);
        imgarrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void findElements() {
        RviewDataCop = findViewById(R.id.RviewDataCop);
        viewDataNameCop = findViewById(R.id.txtDataCopName);
        viewDataNitCop = findViewById(R.id.txtDataCopNit);
        viewDataSaldoCop = findViewById(R.id.txtDataSaldoCop);
        viewDataEmailCop = findViewById(R.id.txtDataEmailCop);
        imgarrowback = findViewById(R.id.imgArrowBackAdmin);
    }
}