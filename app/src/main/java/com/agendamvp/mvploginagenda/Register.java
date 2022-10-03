package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Register extends AppCompatActivity implements View.OnClickListener, InterfacesRegister.View {
    EditText txtnewNombre,txtNewdocumento,txtNewsaldo;
    TextView data;
    Button btnRegistrar,btnCancelar;
    InterfacesRegister.Presenter presenter;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new PresenterRegister(this,Register.this);
        user = new Usuario();
        this.findElements();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtnewNombre.getText().toString();
                String documento = txtNewdocumento.getText().toString();
                String balance = txtNewsaldo.getText().toString();

                if ( cadenaVacia(nombre) && cadenaVacia(documento) && cadenaVacia(balance)){
                    int residue = Integer.parseInt(balance);
                    dialogPersonal(nombre,documento,residue,Register.this);
                }else {
                    txtnewNombre.setError("Campo obigatorio");
                    txtNewdocumento.setError("Campo obligatorio");
                    txtNewsaldo.setError("Campo obligatorio");
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "12328904";
                Random cardRandom = new Random();
                int numeroCard = cardRandom.nextInt(6 - 3 + 1 )+3;
                String CardNumber = "";
                if (data.length() <= 8){
                    int aleatorio = 0;
                    aleatorio = (int) (Math.random()*1000);

                    CardNumber = numeroCard + data + String.valueOf(aleatorio);
                }
                if (data.length() <= 10){
                    int aleatorio = 0;
                    aleatorio = (int) (Math.random()*100);

                    CardNumber = numeroCard + data + String.valueOf(aleatorio);
                }
                String numeros  =  CardNumber + String.valueOf(1010);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private  boolean cadenaVacia(String cadena){
        return !cadena.equals("");
    }
    @Override
    public void findElements() {
        txtnewNombre = findViewById(R.id.txtNewClienteAdmin);
        txtNewdocumento = findViewById(R.id.txtNewAdminCC);
        txtNewsaldo = findViewById(R.id.txtNewAdminSaldo);
        data = findViewById(R.id.data);
        btnRegistrar = findViewById(R.id.btnConfirmarNewCliente);
        btnCancelar = findViewById(R.id.btnCancelClient);
    }



    private void limpiar(){
        txtnewNombre.setText("");
        txtNewdocumento.setText("");
        txtNewsaldo.setText("");
    }

    public void dialogPersonal(String nombre, String documento, int balance, Context contexto){
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_pin,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        EditText txtpin;
        txtpin = view.findViewById(R.id.txtClientPin);


        Button btnAceptPin;
        btnAceptPin = view.findViewById(R.id.btnAceptPin);
        btnAceptPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ntxtpin = txtpin.getText().toString();
                if (cadenaVacia(ntxtpin)){
                    int newpin = Integer.parseInt(ntxtpin);
                 confirmarpin(nombre,documento,balance,newpin, contexto);
                }
            }
        });
    }

  public  void confirmarpin(String nombre, String documento, int balance,int newpin, Context contexto){
        AlertDialog.Builder builderpin = new AlertDialog.Builder(contexto);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_confirm_pin,null);
        builderpin.setView(view);
        AlertDialog dialog = builderpin.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        EditText txtConfirmPin;
        txtConfirmPin = view.findViewById(R.id.txtConfirmClientPin);
        Button btnAceptConfirmPin;

        btnAceptConfirmPin = view.findViewById(R.id.btnConfirmAceptPin);
        btnAceptConfirmPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confirm_pin = txtConfirmPin.getText().toString();

                if (cadenaVacia(confirm_pin)){
                    int newconfirmpin = Integer.parseInt(confirm_pin);
                    if (newconfirmpin != newpin){
                        Toast.makeText(contexto, "El pin no coincide", Toast.LENGTH_LONG).show();
                        txtConfirmPin.setText("");
                    }else{
                        redirigir(nombre,documento,balance,newconfirmpin,contexto);
                    }
                }
            }
        });

    }
    public  void cancelar(){
        Intent intent = new Intent(this,Admin_corresponsal.class);
        startActivity(intent);
    }
    private  String calculo_cc(String cc){
            String cedula = new String();
            try {
                if (cc.length() == 8){
                    int aleatorio = 0;
                    aleatorio = (int) (Math.random()*100);
                    cedula = cc + aleatorio;
                    return  cedula;
                }
            }catch (Exception ex){
                ex.toString();
            }
            return cedula;
    }
     public  void redirigir(String nombre, String documento, int balance,int pin,Context context){
        Intent intent = new Intent(context,PinRegisterClientAdmin.class);
        intent.putExtra("DATA_CLIENT_NAME",nombre);
        intent.putExtra("DATA_CLIENT_CC",documento);
        intent.putExtra("DATA_CLIENT_BALANCE",balance);
        intent.putExtra("DATA_CLIENT_PIN",pin);
        startActivity(intent);
    }
}