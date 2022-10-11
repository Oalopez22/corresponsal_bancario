package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesPayCardCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterPayCardCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PayWithCard extends AppCompatActivity implements InterfacesPayCardCop.view {
    EditText txtNtarjeta,txtCvv,txtMes,txtDia,txtNombreCliente,txtValorPagado;
    TextView txtCopName,txtCopBalance,txtCopNcuenta;
    ImageView imgArrowBack;
    Button btnConfirmPayCard,btnCancelPayCard;
    Spinner spinner;
    InterfacesPayCardCop.presenter presenter;
    SharedPreferences sp;
    Usuario user;
    DbLogin db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_with_card);
        findElements();
        user = new Usuario();
        db = new DbLogin(PayWithCard.this);
        sp = new SharedPreferences(PayWithCard.this);
        presenter = new PresenterPayCardCop(this,PayWithCard.this);
        String [] datos = {"0","1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,datos);
        spinner.setAdapter(adapter);
        user = presenter.data(sp);

        if (presenter != null){
            txtCopName.setText(user.getCorresponsal_name());
            txtCopBalance.setText(String.valueOf(user.getCorresponsal_balance()));
            txtCopNcuenta.setText(String.valueOf(user.getCorreponsal_ncuenta()));
        }
       btnConfirmPayCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String numeroCard = txtNtarjeta.getText().toString();
               String cvv = txtCvv.getText().toString();
               String year = txtMes.getText().toString();
               String month = txtDia.getText().toString();
               String nombreCliente = txtNombreCliente.getText().toString();
               String cuota = spinner.getSelectedItem().toString();
               int copBalance = user.getCorresponsal_balance();
               String valor_pagado = txtValorPagado.getText().toString();
               if (cuota.equals("0")) {
                   Toast.makeText(PayWithCard.this, "El valor de la cuota no debe ser de 0", Toast.LENGTH_SHORT).show();
               } else {
                   if (validarCard() && validarCvv() && validarMes() && validarDia() && validarNombre() && validarvalor()) {
                       int data = Integer.parseInt(valor_pagado);
                       if (data < 10000) {
                           txtValorPagado.setError("El valor pagado debe ser mayor de 10000");
                       } else if (data > 1000000) {
                           txtValorPagado.setError("El valor pagado debe ser menor de 1000000");
                       } else {
                           int spinner = Integer.parseInt(cuota);
                           int valor = (int) data * spinner;
                           sp.setCardClient(numeroCard);
                           confirmacion(numeroCard, cvv, year, month, nombreCliente, spinner, data, valor, copBalance);
                       }

                   }
               }
           }
       });
        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void findElements() {
        txtNtarjeta = findViewById(R.id.txtNcard);
        txtCvv = findViewById(R.id.txtCvvPayCard);
        txtMes = findViewById(R.id.txtMesPayCard);
        txtDia = findViewById(R.id.txtDiaPayCard);
        txtNombreCliente = findViewById(R.id.txtNameClientPayCard);
        spinner = (Spinner)findViewById(R.id.Spinner);
        txtValorPagado = findViewById(R.id.txtValuePay);
        txtCopName = findViewById(R.id.txtCopName);
        txtCopBalance = findViewById(R.id.txtCopBalance);
        txtCopNcuenta = findViewById(R.id.txtCopAcount);
        imgArrowBack = findViewById(R.id.imgArrowBack);
        btnConfirmPayCard = findViewById(R.id.btnPayCardConfirm);
        btnCancelPayCard = findViewById(R.id.btnPayCardCancel);
    }

    public boolean validarCard(){
        String card = txtNtarjeta.getText().toString();
        if (card.equals("")){
            txtNtarjeta.setError("Campo obligatorio");
            return false;
        }else{
            return true;
        }
    }
    public boolean validarCvv(){
        String cvv = txtCvv.getText().toString();
        if (cvv.equals("")){
            txtCvv.setError("Campo obligatorio");
            return false;
        }else{
            return true;
        }
    }
    public boolean validarMes(){
        String mes = txtMes.getText().toString();
        if (mes.equals("")){
            txtMes.setError("Campo obligatorio");
            return false;
        }else{
            return true;
        }
    }
    public boolean validarDia(){
        String dia = txtDia.getText().toString();
        if (dia.equals("")){
            txtDia.setError("Campo obligatorio");
            return false;
        }else{
            return true;
        }
    }
    public boolean validarNombre(){
        String nombre = txtNombreCliente.getText().toString();
        if (nombre.equals("")){
            txtNombreCliente.setError("Campo obligatorio");
            return false;
        }else{
            return true;
        }
    }
    public boolean validarvalor(){
        String valor = txtValorPagado.getText().toString();
        if (valor.equals("")){
            txtValorPagado.setError("Campo obligatorio");
            return false;
        }else{
            return true;
        }
    }
    public void confirmacion(String tarjeta ,String cvv,String year,String month,String nombre, int cuotas,int valor_pagado, int valor_total, int copBalance ){
        Intent intent = new Intent(PayWithCard.this, ConfirmPayWithCard.class);
        intent.putExtra("DATA_CLIENT_CARD",tarjeta);
        intent.putExtra("DATA_CLIENT_CVV",cvv);
        intent.putExtra("DATA_CLIENT_YEAR",year);
        intent.putExtra("DATA_CLIENT_MONTH",month);
        intent.putExtra("DATA_CLIENT_NAME",nombre);
        intent.putExtra("DATA_CLIENT_CUOTES",String.valueOf(cuotas));
        intent.putExtra("DATA_CLIENT_BALANCE",String.valueOf(valor_pagado));
        intent.putExtra("DATA_CLIENT_TOTAL_BALANCE",valor_total);
        intent.putExtra("DATA_COP_BALANCE",copBalance);
        startActivity(intent);
    }
}