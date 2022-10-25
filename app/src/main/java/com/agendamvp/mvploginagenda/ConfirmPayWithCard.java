package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.agendamvp.mvploginagenda.Interfaces.InterfacesPayCardCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterPayCardCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ConfirmPayWithCard extends AppCompatActivity implements InterfacesPayCardCop.view {

    TextView tvClientNAme, tvClientValue, tvClientCuotes, tvCardNumber, tvCardType;
    Button btnConfirmPay, btnCancelPay;
    DbLogin db;
    SharedPreferences sp;
    InterfacesPayCardCop.presenter presenter;
    Usuario user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pay_with_card);
        db = new DbLogin(ConfirmPayWithCard.this);
        sp = new SharedPreferences(ConfirmPayWithCard.this);
        user = new Usuario();
        findElements();
        presenter = new PresenterPayCardCop(this, ConfirmPayWithCard.this);
        Bundle datos = getIntent().getExtras();
        String card = datos.getString("DATA_CLIENT_CARD");
        String nombre = datos.getString("DATA_CLIENT_NAME");
        String corresponsal_email = datos.getString(("DATA_COP_EMAIL"));

        String cuotas = datos.getString("DATA_CLIENT_CUOTES");
        String balance_cliente = datos.getString("DATA_CLIENT_BALANCE");
        int valor_total = datos.getInt("DATA_CLIENT_TOTAL_BALANCE");


        String cvv = datos.getString("DATA_CLIENT_CVV");
        String year = datos.getString("DATA_CLIENT_YEAR");
        String month = datos.getString("DATA_CLIENT_MONTH");
        String cuotes = datos.getString("DATA_CLIENT_CUOTES");
        int cop = datos.getInt("DATA_COP_BALANCE");
/*        int copBalance = Integer.parseInt(valorCop);*/
        user = presenter.validar_datos_cliente(sp);

        String cvvCliente = user.getCvv_client_number_cop();

        String fecha_recibida = user.getFecha_expiracion_client_cop();
        String pinDevuelto = String.valueOf(user.getPin());
        int saldoCliente = user.getSaldo();
        tvClientNAme.setText(nombre);
        tvClientValue.setText(String.valueOf(valor_total));
        tvClientCuotes.setText("A # " + cuotas + " Cuotas");
        tvCardNumber.setText(card);
        char caracter = card.charAt(0);

        switch (caracter) {
            case '3':
                String american = "american express";
                tvCardType.setText(american.toUpperCase(Locale.ROOT));
                break;
            case '4':
                String visa = "VISA";
                tvCardType.setText(visa.toUpperCase(Locale.ROOT));
                break;
            case '5':
                String master = "MasterCard";
                tvCardType.setText(master.toUpperCase(Locale.ROOT));
                break;
            case '6':
                String unoion = "UnionPlay";
                tvCardType.setText(unoion.toUpperCase(Locale.ROOT));
                break;
            default:
                String sinIdentificar = "Tarjeta sin identificar";
                tvCardType.setText(sinIdentificar.toUpperCase(Locale.ROOT));
        }

        btnConfirmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha_ingresada = year + "-" + month;
                LocalDate fecha_actual = LocalDate.now();
                String fechaInicial = fecha_actual.toString();
                String datos_fecha1 = fechaInicial.substring(2, 4);
                String datos2_fecha1 = fechaInicial.substring(5, 7);
                String fechaIniciaCompleta = datos_fecha1+"-" + datos2_fecha1;


                AlertDialog.Builder builderpin = new AlertDialog.Builder(ConfirmPayWithCard.this);
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
                            /*                String card = datos.getString("DATA_CLIENT_CARD");*/

                        int saldoCliente = user.getSaldo();
                        String pinIngresado = txtCardPin.getText().toString();
                        if (!fecha_ingresada.equals(fecha_recibida)){
                            Toast.makeText(ConfirmPayWithCard.this, "Las fechas no coinciden", Toast.LENGTH_LONG).show();
                        }else{
                            if (!pinIngresado.equals(pinDevuelto)){
                                Toast.makeText(ConfirmPayWithCard.this, "El pin no coincide", Toast.LENGTH_LONG).show();
                            }else {
                                if (valor_total > saldoCliente){
                                    Toast.makeText(ConfirmPayWithCard.this, "Saldo insuficiente".toUpperCase(Locale.ROOT), Toast.LENGTH_SHORT).show();
                                }else{
                                    user.setCard_number(card);
                                    user.setSaldo(saldoCliente);
                                    user.setCorresponsal_email(corresponsal_email);
                                    user.setValor_pay_cuotes_cop(valor_total);
                                    user.setCorresponsal_balance(cop);
                                    long id = presenter.Pago_tarjeta_cop(user);
                                    if (id > 0) {
                                        String pago = "Pago realizado";
                                        alertPerzonalizado(R.layout.positive_dialog,pago);
                                    }else {
                                        Toast.makeText(ConfirmPayWithCard.this, "Error al realizar el pago", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }

                    }

                });

            }
        });
        btnCancelPay.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String cancel = "Pago cancelado";
                alertPerzonalizado(R.layout.negative_dialog,cancel);
            }
        });
    }

    @Override
    public void findElements() {
        tvClientNAme = findViewById(R.id.tvConfirmClientNAme);
        tvClientValue = findViewById(R.id.tvConfirmValue);
        tvClientCuotes = findViewById(R.id.tvConfirmNcuotes);
        tvCardNumber = findViewById(R.id.tvConfirmCardNumber);
        tvCardType = findViewById(R.id.tvConfirmCardType);
        btnConfirmPay = findViewById(R.id.btnConfirmPay);
        btnCancelPay = findViewById(R.id.btnCancelPay);

    }
    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ConfirmPayWithCard.this);
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
                redirigir();
            }
        });
    }
    public void redirigir(){
        Intent intent = new Intent(this, Corresponsal_Start.class);
        startActivity(intent);
    }

}