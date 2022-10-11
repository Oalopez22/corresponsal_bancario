package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfaceRetiroClientCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterRetiroClient;

public class Corresponsal_Retiro extends AppCompatActivity implements InterfaceRetiroClientCop.view {
    EditText txtCc,txtBalance;
    Button btnConfirm,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_retiro);
        findElements();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String cedula = txtCc.getText().toString();
            String monto = txtBalance.getText().toString();
            if (cedula.equals("") && monto.equals("")){
                Toast.makeText(Corresponsal_Retiro.this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
            }else{
                int valor = Integer.parseInt(monto);
                AlertDialog.Builder builderpin = new AlertDialog.Builder(Corresponsal_Retiro.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_pin_pay_card,null);
                builderpin.setView(view);
                AlertDialog dialog = builderpin.create();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                EditText txtCardPin;
                txtCardPin = view.findViewById(R.id.txtClientCardPin);

                Button btnAceptar;
                btnAceptar = view.findViewById(R.id.btnAceptCardPin);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*redireccion(cedula,valor,pin);*/
                    }
                });

            }
            }
        });
    }

    @Override
    public void findElements() {
        txtCc = findViewById(R.id.txtRetiroCc);
        txtBalance = findViewById(R.id.txtRetiroBalance);
        btnConfirm = findViewById(R.id.btnRetConfirm);
        btnCancel = findViewById(R.id.btnRetCancel);

        /*https://www.youtube.com/watch?v=tK7MC8KzkYA&ab_channel=T0pCode*/
    }


    public void  redireccion(String cc, int valor, String pin){
        Intent intent = new Intent(this,ConfirmRetiro.class);
        intent.putExtra("DATA_CLIENT_CC",cc);
        intent.putExtra("DATA_CLIENT_VALUE",valor);
        intent.putExtra("DATA_CLIENT_PIN",pin);
        startActivity(intent);
    }
}