package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDataCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterDataCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterSeachCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class DataAdminCop extends AppCompatActivity implements InterfacesSeachCop.view {

    Usuario user;
    TextView viewDataNameCop,viewDataNitCop,viewDataSaldoCop,viewDataEmailCop;
    SharedPreferences sp;
    InterfacesSeachCop.presenter presenter ;
    Button btnHabilitarCop,btnDeshabilitarCop;
    ImageView imgarrowback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_admin_cop);
        findElements();
        user = new Usuario();
        sp = new SharedPreferences(DataAdminCop.this);
        presenter = new PresenterSeachCop(this,DataAdminCop.this);

        user = presenter.info(sp);
        String nombre = user.getCorresponsal_name();

        imgarrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnHabilitarCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnDeshabilitarCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public void findElements() {
        viewDataNameCop = findViewById(R.id.txtDataCopName);
        viewDataNitCop = findViewById(R.id.txtDataCopNit);
        viewDataSaldoCop = findViewById(R.id.txtDataSaldoCop);
        viewDataEmailCop = findViewById(R.id.txtDataEmailCop);
        imgarrowback = findViewById(R.id.imgArrowBackAdmin);
        btnHabilitarCop = findViewById(R.id.btndataHabilitar);
        btnDeshabilitarCop = findViewById(R.id.btndataDesabilitar);
    }
    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DataAdminCop.this);
        View layoutview = getLayoutInflater().inflate(layout,null);
        Button btnExit = layoutview.findViewById(R.id.btnDialog);
        TextView txtmensaje = layoutview.findViewById(R.id.txtmensaje);
        txtmensaje.setText(mensaje.toUpperCase());
        dialogBuilder.setView(layoutview);
        AlertDialog alert = dialogBuilder.create();
        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redireccion();
            }
        });
    }


    private  void redireccion(){
        Intent intent = new Intent(DataAdminCop.this,Admin_corresponsal.class);
        startActivity(intent);
    }
}