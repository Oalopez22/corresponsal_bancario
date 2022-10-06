package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Presenter.PresenterRegister;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

import java.time.LocalDate;
import java.util.Random;

public class PinRegisterClientAdmin extends AppCompatActivity implements  InterfacesRegister.View{
    InterfacesRegister.Presenter presenter;
    TextView txtviewConfirmClientName,txtviewConfirmClientCc,txtviewConfirmClientSaldo;

    Button btnConfirmDataClient,btnCancelDataClient;
    Usuario user;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_register_client_admin);
        findElements();
        Bundle datosRecibidos = getIntent().getExtras();
        sp = new SharedPreferences(PinRegisterClientAdmin.this);
        txtviewConfirmClientName.setText(datosRecibidos.getString("DATA_CLIENT_NAME"));
        txtviewConfirmClientCc.setText(datosRecibidos.getString("DATA_CLIENT_CC"));
        int data = datosRecibidos.getInt("DATA_CLIENT_BALANCE");
        txtviewConfirmClientSaldo.setText(String.valueOf(data));

        presenter = new PresenterRegister(this,PinRegisterClientAdmin.this);
        user = new Usuario();
       btnConfirmDataClient.setOnClickListener(new View.OnClickListener() {
                String nombreCliente= txtviewConfirmClientName.getText().toString();
               String ccCliente= txtviewConfirmClientCc.getText().toString();
                int data2 = datosRecibidos.getInt("DATA_CLIENT_BALANCE");
                int pin2 = datosRecibidos.getInt("DATA_CLIENT_PIN");
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Random cardRandom = new Random();
                int numeroCard = cardRandom.nextInt(6 - 3 + 1 )+3;
                String CardNumber = "";
                if (ccCliente.length() <= 8){
                    int aleatorio = 0;
                    aleatorio = (int) (Math.random()*1000);

                    CardNumber = numeroCard + ccCliente + String.valueOf(aleatorio);
                }
                if (ccCliente.length() <= 10){
                    int aleatorio = 0;
                    aleatorio = (int) (Math.random()*100);

                    CardNumber = numeroCard + ccCliente + String.valueOf(aleatorio);
                }
                String numeros  =  CardNumber + pin2;

                user.setNombre(nombreCliente);
                user.setDocumento(ccCliente);
                user.setSaldo(data2);
                user.setCard_number(numeros);
                user.setPin(pin2);
                sp.setCcUser(ccCliente);
                long id = presenter.registrar_Usuario(user);
                if (id > 0){
                    Toast.makeText(PinRegisterClientAdmin.this, "Cliente creado", Toast.LENGTH_LONG).show();
                    redireccion();

                }else{
                    Toast.makeText(PinRegisterClientAdmin.this, "Error al crear el Cliente", Toast.LENGTH_LONG).show();
                }
            }
        });
       btnCancelDataClient.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder buildercancel = new AlertDialog.Builder(PinRegisterClientAdmin.this);

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
    }

    @Override
    public void findElements() {
        txtviewConfirmClientName = findViewById(R.id.txtDataClientName);
        txtviewConfirmClientCc = findViewById(R.id.txtDataClientCc);
        txtviewConfirmClientSaldo = findViewById(R.id.txtDataBalanceClient);
        btnConfirmDataClient = findViewById(R.id.btnConfirmDataClient);
        btnCancelDataClient = findViewById(R.id.btnCancelDataClient);
    }


    private void redireccion(){
        Intent intent = new Intent(this,DataAdminClient.class);
        startActivity(intent);
    }
    private void salir(){
        Intent intent = new Intent(this,Admin_corresponsal.class);
        startActivity(intent);
    }
}