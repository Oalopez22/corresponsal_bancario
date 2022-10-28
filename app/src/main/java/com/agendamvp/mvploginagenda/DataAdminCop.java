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
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDataCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterDataCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterSeachCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class DataAdminCop extends AppCompatActivity implements InterfacesSeachCop.view {

    Usuario user;
    Usuario actualizar;
    TextView viewDataNameCop,viewDataNitCop,viewDataSaldoCop,viewDataEmailCop;
    SharedPreferences sp;
    InterfacesSeachCop.presenter presenter ;
    Button btnHabilitarCop,btnDeshabilitarCop;
    ImageView imgarrowback;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_admin_cop);
        findElements();
        user = new Usuario();
        actualizar = new Usuario();
        sp = new SharedPreferences(DataAdminCop.this);
        presenter = new PresenterSeachCop(this,DataAdminCop.this);



        user = presenter.info(sp);
        if (presenter!= null){
            String nombre = user.getCorresponsal_name();
            String email = user.getCorresponsal_email();
            String nit = user.getCorresponsal_nit();
            int saldo = user.getCorresponsal_balance();
            int estado = user.getCorresponsal_status();
            String validar = String.valueOf(estado);
            char numero = validar.charAt(0);
            viewDataEmailCop.setText(email);
            viewDataNitCop.setText(nit);
            viewDataNameCop.setText(nombre);
            viewDataSaldoCop.setText(String.valueOf(saldo));
            switch (numero){
                case '0':
                    int deshabilitar = 0;
                    actualizar.setCorresponsal_status(deshabilitar);
                    btnDeshabilitarCop.setVisibility(View.VISIBLE);
                    btnDeshabilitarCop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean exito = presenter.actualizar_estado(actualizar);
                            if (exito){
                                String mensaje = "Corresponsal inhabilitado";
                                alertPerzonalizado(R.layout.negative_dialog,mensaje);
                            }else {
                                Toast.makeText(DataAdminCop.this, "Error al cambiar estado", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    break;
                case '1':
                    int habilitar = 0;
                    actualizar.setCorresponsal_status(habilitar);
                    btnHabilitarCop.setVisibility(View.VISIBLE);
                    btnHabilitarCop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean exito = presenter.actualizar_estado(actualizar);
                            if (exito){
                                String mensaje = "Corresponsal habilitado";
                                alertPerzonalizado(R.layout.positive_dialog,mensaje);
                            }else {
                                Toast.makeText(DataAdminCop.this, "Error al cambiar estado", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    break;
                default:
                    Toast.makeText(this, "Error al obtener informacion", Toast.LENGTH_SHORT).show();
            }
        }

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        imgarrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Consulta cancelada";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });
        btnHabilitarCop.setVisibility(View.INVISIBLE);
        btnDeshabilitarCop.setVisibility(View.INVISIBLE);



    }

    @Override
    public void findElements() {
        viewDataNameCop = findViewById(R.id.txtDataCopName2);
        viewDataNitCop = findViewById(R.id.txtDataCopNit2);
        viewDataSaldoCop = findViewById(R.id.txtDataSaldoCop2);
        viewDataEmailCop = findViewById(R.id.txtDataEmailCop2);
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