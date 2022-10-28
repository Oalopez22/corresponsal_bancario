package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSearchClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterSeachClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class SearchClientAdmin extends AppCompatActivity implements InterfacesSearchClient.view {
    EditText searchClient;
    Button btnConfirmSearchClient,btnCancelSearchClient;
    ImageView imgArrowback;
    InterfacesSearchClient.presenter presenter;
    SharedPreferences sp;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_client_admin);
        presenter = new PresenterSeachClient(this,SearchClientAdmin.this);
        sp = new SharedPreferences(SearchClientAdmin.this);
        user = new Usuario();
        findElements();
        btnConfirmSearchClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cc = searchClient.getText().toString();
                if (cadenaVacia(cc)){
                    sp.setCcUser(cc);
                    boolean id = presenter.buscar_cliente(sp);
                    if (id){
                        vistaDatosCliente();
                    }else {
                        Toast.makeText(SearchClientAdmin.this, "No se encontron un cliente con este numero de documento", Toast.LENGTH_LONG).show();
                        searchClient.setText("");
                    }
                }
            }
        });
        btnCancelSearchClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String mensaje = "Busqueda cancelada";
            alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });

        imgArrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private  boolean cadenaVacia(String cadena){
        return !cadena.equals("");
    }
    @Override
    public void findElements() {
        searchClient = findViewById(R.id.txtSearchCcUser);
        btnConfirmSearchClient = findViewById(R.id.btnsearchUser);
        imgArrowback = findViewById(R.id.imgArrowBackAdmin);
        btnCancelSearchClient = findViewById(R.id.btnCancelSearchUser);
    }

    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SearchClientAdmin.this);
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
                salir();
            }
        });
    }

    private void vistaDatosCliente(){
        Intent intent = new Intent(this,DataAdminClient.class);
        startActivity(intent);
    }
    private void salir(){
        Intent intent = new Intent(this,Admin_corresponsal.class);
        startActivity(intent);
    }
}