package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesPayCardCop;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.util.ArrayList;

public class PayWithCard extends AppCompatActivity implements InterfacesPayCardCop.view {
    EditText txtNtarjeta,txtCvv,txtMes,txtDia,txtNombreCliente,txtValorPagado;
    Button btnConfirmPayCard,btnCancelPayCard;
    Spinner spinner;
    Usuario user;
    DbLogin db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_with_card);
        findElements();
        user = new Usuario();
        db = new DbLogin(PayWithCard.this);
        String [] datos = {"Cuotas","1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,datos);
        spinner.setAdapter(adapter);


        btnConfirmPayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroCard = txtNtarjeta.getText().toString();
                String cvv = txtCvv.getText().toString();
                String mes = txtMes.getText().toString();
                String dia = txtDia.getText().toString();
                String nombreCliente = txtNombreCliente.getText().toString();
                String cuota = spinner.getSelectedItem().toString();
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
        btnConfirmPayCard = findViewById(R.id.btnPayCardConfirm);
        btnCancelPayCard = findViewById(R.id.btnPayCardCancel);
    }
}