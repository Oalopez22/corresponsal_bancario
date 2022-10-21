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
                if (!CcCliente.equals("")){
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Corresponsal_Client_Balance.this,R.style.MyDialogAnimation);
                    AlertDialog dialog = builder.setView(view).create();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog = builder.show();
                    Button btnSi,btnNo;
                    btnSi = view.findViewById(R.id.btnSi);
                    AlertDialog finalDialog = dialog;
                    btnSi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            user = presenter.datosDevueltos(sp);
                            int pinDevuelto = user.getPin();
                            int saldoCliente = user.getSaldo();
                            if (user==null){

                                Toast.makeText(Corresponsal_Client_Balance.this, "El usuario no existe", Toast.LENGTH_LONG).show();
                            }else {
                                finalDialog.dismiss();
                                LayoutInflater inflater = getLayoutInflater();
                                View view = inflater.inflate(R.layout.dialog_pin_pay_card,null);
                                AlertDialog.Builder builder = new AlertDialog.Builder(Corresponsal_Client_Balance.this,R.style.MyDialogAnimation);
                                AlertDialog dialog1 = builder.setView(view).create();
                                dialog1.setCancelable(false);
                                dialog1.setCanceledOnTouchOutside(false);
                                dialog1 = builder.show();
                                EditText txtPin = view.findViewById(R.id.txtClientCardPin);
                                Button btnPin,btnCancelPin;
                                btnPin = view.findViewById(R.id.btnAceptCardPin);
                                btnPin.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String pin = txtPin.getText().toString();
                                        if (!pin.equals("")){
                                            int pinuser = Integer.parseInt(pin);
                                            LayoutInflater inflater = getLayoutInflater();
                                            View view = inflater.inflate(R.layout.dialog_pin_confirm_retiro,null);
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Corresponsal_Client_Balance.this,R.style.MyDialogAnimation);
                                            AlertDialog dialog = builder.setView(view).create();
                                            dialog.setCancelable(false);
                                            dialog.setCanceledOnTouchOutside(false);
                                            dialog = builder.show();
                                            EditText confirmpin = view.findViewById(R.id.txtConfirmPinRet);
                                            Button btnconfirmPin, btnCancelPin;
                                            btnconfirmPin = view.findViewById(R.id.btnAceptConfirmPin);
                                            btnconfirmPin.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String datoConfirm = confirmpin.getText().toString();
                                                    if (!datoConfirm.equals("")){
                                                        int newpin = Integer.parseInt(datoConfirm);
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

                                            btnCancelPin = view.findViewById(R.id.btnCancelConfirmPin);
                                            btnCancelPin.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                }
                                            });

                                        }else{
                                            txtPin.setError("Campo obligatorio");
                                        }
                                    }
                                });
                                btnCancelPin = view.findViewById(R.id.btnCancelCardPin);
                                btnCancelPin.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                            }


                        }
                    });
                    btnNo = view.findViewById(R.id.btnNo);
                    btnNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }else {
                    txtCcCliente.setError("Campo obligatorio");
                }


            }
        });

        btnCancelBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        btnCancelBalance = findViewById(R.id.btnCancelBalance);
    }


}