package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Presenter.PresenterRegister;

import java.util.Locale;

public class Corresponsal_Register_Client extends AppCompatActivity implements InterfacesRegister.View {
    EditText txtNewNombre,txtNewdocumento,txtNewsaldo;
    ImageView imgArrowBack;
    Button btnRegistrar,btnCancelar;
    Usuario user;
    InterfacesRegister.Presenter presenter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_register_client);
        user = new Usuario();
        presenter = new PresenterRegister(this,Corresponsal_Register_Client.this);
        findElements();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNewNombre.getText().toString();
                String documento = txtNewdocumento.getText().toString();
                String saldo = txtNewsaldo.getText().toString();
                if (cadenaVacia(nombre) && cadenaVacia(documento) && cadenaVacia(saldo)){
                    int balance = Integer.parseInt(saldo);
                    dialogPersonal(nombre,documento,balance);
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void findElements() {
        imgArrowBack = findViewById(R.id.imgArrowBackCop);
        txtNewNombre = findViewById(R.id.txtNewClienteCop);
        txtNewdocumento = findViewById(R.id.txtNewCopCC);
        txtNewsaldo = findViewById(R.id.txtNewCopSaldo);
        btnRegistrar = findViewById(R.id.btnConfirmarNewCliente);
        btnCancelar = findViewById(R.id.btnCancelNewCliente);
    }

    public void dialogPersonal(String nombre, String documento, int balance){
        LayoutInflater inflater = getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(Corresponsal_Register_Client.this , R.style.MyDialogAnimation);
        builder.setCancelable(false);
        View view = inflater.inflate(R.layout.dialog_pin_pay_card,null);
        AlertDialog dialog = builder.setView(view).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog = builder.show();
        EditText txtpin;
        txtpin = view.findViewById(R.id.txtClientCardPin);
        Button btnAceptar,btnCancelar;
        btnAceptar = view.findViewById(R.id.btnAceptCardPin);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato = txtpin.getText().toString();

                if (cadenaVacia(dato)) {
                    int pin = Integer.parseInt(dato);
                    confirmPin(nombre,documento,balance,pin,Corresponsal_Register_Client.this);

                }else{
                    txtpin.setError("Campo obligatorio");
                }

            }
        });

    }

    public void confirmPin(String nombre, String documento, int balance,int newpin, Context contexto){
        LayoutInflater inflater = getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(Corresponsal_Register_Client.this , R.style.MyDialogAnimation);
        builder.setCancelable(false);
        View view = inflater.inflate(R.layout.dialog_pin_confirm_retiro,null);
        AlertDialog dialog = builder.setView(view).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog = builder.show();
        EditText txtConfirmpin;
        txtConfirmpin = view.findViewById(R.id.txtConfirmPinRet);
        Button btnAceptar,btnCancelar;
        btnAceptar = view.findViewById(R.id.btnAceptConfirmPin);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confirmPin = txtConfirmpin.getText().toString();
                if (cadenaVacia(confirmPin)){
                    int pin = Integer.parseInt(confirmPin);
                    if (pin != newpin){
                        txtConfirmpin.setError("El pin no coincide");
                        txtConfirmpin.setText("");
                    }else{
                        redireccion(nombre,documento,balance,pin);
                    }
                }else{
                    txtConfirmpin.setText("Campo obligatorio");
                }
            }
        });
        btnCancelar = view.findViewById(R.id.btnCancelConfirmPin);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private  boolean cadenaVacia(String cadena){
        return !cadena.equals("");
    }

    public void redireccion(String nombre, String documento, int balance,int pin){
        Intent intent = new Intent(Corresponsal_Register_Client.this,Corresponsal_confirm_New_Client.class);
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