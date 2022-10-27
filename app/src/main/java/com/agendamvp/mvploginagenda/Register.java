package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Register extends AppCompatActivity implements View.OnClickListener, InterfacesRegister.View {
    EditText txtnewNombre,txtNewdocumento,txtNewsaldo;
    TextView data;
    Button btnRegistrar,btnCancelar;
    InterfacesRegister.Presenter presenter;
    Usuario user;
    SharedPreferences sp;
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
                        dialogPersonal(R.layout.dialog_pin,nombre,documento,residue,Register.this);

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
                String mensaje = "Registro cancelado";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
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
        btnRegistrar = findViewById(R.id.btnConfirmarNewCliente);
        btnCancelar = findViewById(R.id.btnCancelClient);
    }



    private void limpiar(){
        txtnewNombre.setText("");
        txtNewdocumento.setText("");
        txtNewsaldo.setText("");
    }

    public void dialogPersonal(int layout, String nombre, String documento, int balance, Context contexto){
        sp = new SharedPreferences(this);
        sp.setCcUser(documento);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Register.this);
        View layoutview = getLayoutInflater().inflate(layout,null);
        dialogBuilder.setView(layoutview);
        AlertDialog alert = dialogBuilder.create();
        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText txtpin;
        txtpin = layoutview.findViewById(R.id.txtClientPin);
        Button btnAceptPin,btnCancelPin;
        btnAceptPin = layoutview.findViewById(R.id.btnAceptPin);
        btnCancelPin = layoutview.findViewById(R.id.btnCancelPin);
        btnAceptPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ntxtpin = txtpin.getText().toString();
                if (cadenaVacia(ntxtpin)){
                    int newpin = Integer.parseInt(ntxtpin);
                 confirmarpin(nombre,sp,documento,balance,newpin);
                }else {
                    Toast.makeText(contexto, "Campo obligatorio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancelPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Registro cancelado";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }

        });
    }

  public  void confirmarpin(String nombre,SharedPreferences sp, String documento, int balance,int newpin){
      AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Register.this);
      View layoutview = getLayoutInflater().inflate(R.layout.dialog_confirm_pin,null);
      dialogBuilder.setView(layoutview);
      AlertDialog alert = dialogBuilder.create();
      alert.show();
      alert.setCancelable(false);
      alert.setCanceledOnTouchOutside(false);
      alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
      alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

      EditText txtConfirmPin;
        txtConfirmPin = layoutview.findViewById(R.id.txtConfirmClientPin);
        Button btnAceptConfirmPin, btnCancelConfirm;

        btnAceptConfirmPin = layoutview.findViewById(R.id.btnConfirmAceptPin);
        btnAceptConfirmPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confirm_pin = txtConfirmPin.getText().toString();

                if (cadenaVacia(confirm_pin)){
                    int newconfirmpin = Integer.parseInt(confirm_pin);
                    if (newconfirmpin != newpin){
                        Toast.makeText(Register.this, "El pin no coincide", Toast.LENGTH_LONG).show();
                        txtConfirmPin.setText("");
                    }else{
                        boolean info = presenter.validar_existencia(sp);
                        if (info){
                            Toast.makeText(Register.this, "Ya existe un usuario Registrado con este número de documento", Toast.LENGTH_LONG).show();
                            alert.dismiss();
                        }else {
                            redirigir(nombre, documento, balance, newconfirmpin);
                        }
                    }
                }
            }
        });
        btnCancelConfirm = layoutview.findViewById(R.id.btnCancelConfirmPin);
        btnCancelConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alert.dismiss();
            }
        });
    }

    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Register.this);
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

     public  void redirigir(String nombre, String documento, int balance,int pin){
        Intent intent = new Intent(Register.this,PinRegisterClientAdmin.class);
        intent.putExtra("DATA_CLIENT_NAME",nombre);
        intent.putExtra("DATA_CLIENT_CC",documento);
        intent.putExtra("DATA_CLIENT_BALANCE",balance);
        intent.putExtra("DATA_CLIENT_PIN",pin);
        startActivity(intent);
    }
    private void salir(){
        Intent intent = new Intent(this,Admin_corresponsal.class);
        startActivity(intent);
    }
}