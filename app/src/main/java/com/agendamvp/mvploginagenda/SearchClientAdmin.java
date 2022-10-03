package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSearchClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterSeachClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class SearchClientAdmin extends AppCompatActivity implements InterfacesSearchClient.view {
    EditText searchClient;
    Button btnConfirmSearchClient;
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
                    }
                }
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
    }
    private void vistaDatosCliente(){
        Intent intent = new Intent(this,DataAdminClient.class);
        startActivity(intent);
    }
}