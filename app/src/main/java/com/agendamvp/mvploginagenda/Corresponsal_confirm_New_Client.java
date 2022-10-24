package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesNewClientCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Presenter.PresenterNewClientCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterRegister;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class Corresponsal_confirm_New_Client extends AppCompatActivity implements InterfacesNewClientCop.View {
    TextView txtviewConfirmClientName,txtviewConfirmClientCc,txtviewConfirmClientSaldo;
    Button btnConfirmDataClient,btnCancelDataClient;
    InterfacesNewClientCop.Presenter presenter;
    Usuario user;
    Usuario datosCop;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_confirm_new_client);
        findElements();
        user = new Usuario();
        datosCop = new Usuario();
        sp = new SharedPreferences(this);

        presenter = new PresenterNewClientCop(this,Corresponsal_confirm_New_Client.this);
        Bundle datos = getIntent().getExtras();

        datosCop = presenter.data(sp);
        int saldoCop = datosCop.getCorresponsal_balance();
        String emailCop = datosCop.getCorresponsal_email();
        String nombre = datos.getString("DATA_CLIENT_NAME");
        String documento = datos.getString("DATA_CLIENT_CC");
        int balance = datos.getInt("DATA_CLIENT_BALANCE");
        int pin = datos.getInt("DATA_CLIENT_PIN");
        txtviewConfirmClientName.setText(nombre);
        txtviewConfirmClientCc.setText(documento);
        txtviewConfirmClientSaldo.setText(String.valueOf(balance));
        btnConfirmDataClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setNombre(nombre);
                user.setDocumento(documento);
                user.setSaldo(balance);
                user.setPin(pin);
                user.setCorresponsal_balance(saldoCop);
               long id = presenter.registrar_Usuario(user);
                if (id > 0){
                    Toast.makeText(Corresponsal_confirm_New_Client.this, "Cliente creado", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Corresponsal_confirm_New_Client.this, "Error al crear el cliente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void findElements() {
        txtviewConfirmClientName = findViewById(R.id.txtDataClientName);
        txtviewConfirmClientCc = findViewById(R.id.txtDataClientCc);
        txtviewConfirmClientSaldo = findViewById(R.id.txtDataBalanceClient);
        btnConfirmDataClient = findViewById(R.id.btnConfirmDataClient);
        btnCancelDataClient = findViewById(R.id.btnCancelDataClient);
    }
}