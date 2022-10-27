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
import com.agendamvp.mvploginagenda.Interfaces.InterfacesBalanceClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterBalanceClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class Corresponsal_Client_Balance extends AppCompatActivity implements InterfacesBalanceClient.view {
    ImageView imgArrowBack;
    EditText txtCcCliente;
    Button btnConfirmBalance,btnCancelBalance;
    InterfacesBalanceClient.presenter presenter;
    Usuario user;
    Usuario consulta;
    Usuario corresponsal;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_client_balance);
        FindElements();
        user = new Usuario();
        consulta = new Usuario();
        corresponsal = new Usuario();
        sp = new SharedPreferences(this);

        presenter = new PresenterBalanceClient(this,Corresponsal_Client_Balance.this);

        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnConfirmBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CcCliente = txtCcCliente.getText().toString();
                sp.setCcUser(CcCliente);
                if (CcCliente.equals("")) {
                    txtCcCliente.setError("Campo obligatorio");
                }else{
                    mensaje();
                }

            }
        });

        btnCancelBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Consulta cancelada";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });
    }

    public void mensaje(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Corresponsal_Client_Balance.this);
        View layoutview = getLayoutInflater().inflate(R.layout.dialog,null);
        dialogBuilder.setView(layoutview);
        AlertDialog alert = dialogBuilder.create();
        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnSi,btnNo;
        btnSi = layoutview.findViewById(R.id.btnSi);
        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin();
            }
        });

        btnNo = layoutview.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
    }

    public void pin(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Corresponsal_Client_Balance.this);
        View layoutview = getLayoutInflater().inflate(R.layout.dialog_pin_pay_card,null);
        dialogBuilder.setView(layoutview);
        AlertDialog alert = dialogBuilder.create();
        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText txtRetPin;
        txtRetPin = layoutview.findViewById(R.id.txtClientCardPin);

        Button btnAceptar, btncancelar;
        btnAceptar = layoutview.findViewById(R.id.btnAceptCardPin);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datopin = txtRetPin.getText().toString();
                if (datopin.equals("")){

                }else{
                    int pin = Integer.parseInt(datopin);
                    user = presenter.datosDevueltos(sp);
                    confirmPin(pin,user);
                }

            }
        });

        btncancelar = layoutview.findViewById(R.id.btnCancelCardPin);
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = " Retiro cancelado";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });
    }

    public void confirmPin(int pinuser, Usuario user){
        AlertDialog.Builder builderpin = new AlertDialog.Builder(Corresponsal_Client_Balance.this);
        View layoutview = getLayoutInflater().inflate(R.layout.dialog_pin_confirm_retiro,null);
        builderpin.setView(layoutview);
        AlertDialog dialog = builderpin.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText txtConfirmPin;
        txtConfirmPin = layoutview.findViewById(R.id.txtConfirmPinRet);
        if (user==null){
            Toast.makeText(Corresponsal_Client_Balance.this, "El usuario no existe", Toast.LENGTH_LONG).show();
        }
            int pinDevuelto = user.getPin();
            int saldoCliente = user.getSaldo();

        Button btnAceptar,btnCancelar;
        btnAceptar = layoutview.findViewById(R.id.btnAceptConfirmPin);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confirmaPin = txtConfirmPin.getText().toString();
                if (!confirmaPin.equals("")){
                    int newpin = Integer.parseInt(confirmaPin);
                    if (newpin == pinuser && newpin == pinDevuelto){
                        corresponsal = presenter.datosCorresponsal(sp);
                        int copbalance = corresponsal.getCorresponsal_balance();
                        consulta.setSaldo(saldoCliente);
                        consulta.setCorresponsal_balance(copbalance);
                        boolean consultarSaldo = presenter.consultarsaldo(consulta);
                        if (consultarSaldo){
                            redireccion();
                        }else{
                            Toast.makeText(Corresponsal_Client_Balance.this, "No se pudo hacer la consulta", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(Corresponsal_Client_Balance.this, "El pin no coincide", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        btnCancelar = layoutview.findViewById(R.id.btnCancelConfirmPin);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Corresponsal_Client_Balance.this);
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
                inicio();
            }
        });
    }
    public void inicio(){
        Intent intent = new Intent(Corresponsal_Client_Balance.this, Corresponsal_Start.class);
        startActivity(intent);
    }
    public void redireccion(){
        Intent intent = new Intent(Corresponsal_Client_Balance.this,Corresponsal_Data_Client.class);
        startActivity(intent);
    }

    @Override
    public void FindElements() {
        imgArrowBack = findViewById(R.id.imgArrowbackDeposit);
        txtCcCliente = findViewById(R.id.txtccBalance);
        btnConfirmBalance = findViewById(R.id.btnConfirmBalance);
        btnCancelBalance = findViewById(R.id.btnCamcelBalance);
    }


}