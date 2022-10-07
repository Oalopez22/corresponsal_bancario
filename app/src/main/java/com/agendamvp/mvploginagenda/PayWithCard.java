package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
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
import com.agendamvp.mvploginagenda.Presenter.PresenterPayCardCop;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PayWithCard extends AppCompatActivity implements InterfacesPayCardCop.view {
    EditText txtNtarjeta,txtCvv,txtMes,txtDia,txtNombreCliente,txtValorPagado;
    Button btnConfirmPayCard,btnCancelPayCard;
    Spinner spinner;
    InterfacesPayCardCop.presenter presenter;
    Usuario user;
    DbLogin db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_with_card);
        findElements();
        user = new Usuario();
        db = new DbLogin(PayWithCard.this);
        presenter = new PresenterPayCardCop(this,PayWithCard.this);
        String [] datos = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,datos);
        spinner.setAdapter(adapter);

        btnConfirmPayCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnPayCardConfirm:
                        String numeroCard = txtNtarjeta.getText().toString();
                        String cvv = txtCvv.getText().toString();
                        String mes = txtMes.getText().toString();
                        String dia = txtDia.getText().toString();
                        String nombreCliente = txtNombreCliente.getText().toString();
                        String cuota = spinner.getSelectedItem().toString();
                        int valor = Integer.parseInt(txtValorPagado.getText().toString());
                        if (cadena_vacia(numeroCard) && cadena_vacia(cvv) && /*cadena_vacia(mes) && cadena_vacia(dia) && */cadena_vacia(nombreCliente) && cuota.isEmpty() && valor ==0){
                            int cuotas = Integer.parseInt(cuota);
                            int valor_total = valor * cuotas;
                            user.setCard_number(numeroCard);
                            user.setCvv_cliente(cvv);
                            user.setNombre(nombreCliente);
                            user.setValor_pay_card_cop(valor);
                            user.setValor_pay_cuotes_cop(valor_total);
                            Toast.makeText(PayWithCard.this, "cuotas " + valor_total, Toast.LENGTH_SHORT).show();
/*                    long id = presenter.Pago_tarjeta_cop(user);
                    if (id>0){
                        Toast.makeText(PayWithCard.this, "Registrado", Toast.LENGTH_SHORT).show();
                    }*/
                        }                        break;
                }
/*                LocalDate Month = LocalDate.now();
                String year = String.valueOf(Month.getYear());

                String fechaPago = year + "-" + mes + "-" + dia;
                System.out.println(fechaPago);*/

            }
        });
    }
    public boolean cadena_vacia(String cadena){
        return cadena.equals("");
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