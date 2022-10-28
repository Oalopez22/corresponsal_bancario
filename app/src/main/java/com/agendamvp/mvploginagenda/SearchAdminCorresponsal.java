package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterSeachCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class SearchAdminCorresponsal extends AppCompatActivity  implements InterfacesSeachCop.view {
    EditText SeachCop;
    Button btnConfirmSeachrCop,btnCancelSearchCop;
    ImageView imgArrowback;
    InterfacesSeachCop.presenter presenter;
    SharedPreferences sp;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_admin_corresponsal);
        sp = new SharedPreferences(SearchAdminCorresponsal.this);
        presenter = new PresenterSeachCop(this, SearchAdminCorresponsal.this);
        user = new Usuario();
        findElements();
        btnConfirmSeachrCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nit = SeachCop.getText().toString();
                if (cadenaVacia(nit)){
                    user.setCorresponsal_nit(nit);
                    sp.setNitCopAdmin(nit);
                    boolean id = presenter.buscarcop(sp);
                    if (id){
                        vistaDatosCorresponsal();
                    }else{
                        Toast.makeText(SearchAdminCorresponsal.this, "Error al buscar el corresponsal", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnCancelSearchCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buildercancel = new AlertDialog.Builder(SearchAdminCorresponsal.this);

                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_cancel_client,null);
                buildercancel.setView(view);
                AlertDialog dialog = buildercancel.create();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                Button btnExit;
                btnExit = view.findViewById(R.id.btnSalir);
                btnExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        salir();
                    }
                });
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
        SeachCop = findViewById(R.id.txtSearchNitCop);
        btnConfirmSeachrCop = findViewById(R.id.btnsearchcop);
        imgArrowback = findViewById(R.id.imgArrowBackAdmin);
        btnCancelSearchCop = findViewById(R.id.btnCancelSeachCop);
    }
    private void vistaDatosCorresponsal(){
        Intent intent = new Intent(this, DataAdminCop.class);
        startActivity(intent);
    }
    private void salir(){
        Intent intent = new Intent(this,Admin_corresponsal.class);
        startActivity(intent);
    }
}