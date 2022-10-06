package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDataCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterDataCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterRegister;
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
    InterfacesDataCop.presenter presenter;
    Button btnHabilitarCop,btnDeshabilitarCop;
    ImageView imgarrowback;
    RecyclerView RviewDataCop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_admin_cop);
        findElements();
        db = new DbLogin(DataAdminCop.this);
        user = new Usuario();
        /*presenter = new PresenterRegister(this,Register.this);*/
        presenter = new PresenterDataCop(this,DataAdminCop.this);
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
        Toast.makeText(this, "Preference : " + sp.getNitCop(), Toast.LENGTH_SHORT).show();
        if (user != null){
            int status= user.getCorresponsal_status();
            if (status == 0){
                btnDeshabilitarCop.setVisibility(View.INVISIBLE);
            }
            if (status == 1){
                btnHabilitarCop.setVisibility(View.VISIBLE);
            }
        }

        btnHabilitarCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int estado = 1;
                boolean a= presenter.update_status(sp,estado);
                Toast.makeText(DataAdminCop.this, "Corresponsal habilitado", Toast.LENGTH_LONG).show();
            }
        });

        btnDeshabilitarCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int estado = 0;
                boolean a= presenter.update_status(sp,estado);
                redireccion();
                Toast.makeText(DataAdminCop.this, "Corresponsal inhabilitado", Toast.LENGTH_LONG).show();
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
        btnHabilitarCop = findViewById(R.id.btndataHabilitar);
        btnDeshabilitarCop = findViewById(R.id.btndataDesabilitar);
    }
    private  void redireccion(){
        Intent intent = new Intent(DataAdminCop.this,Admin_corresponsal.class);
        startActivity(intent);
    }
}