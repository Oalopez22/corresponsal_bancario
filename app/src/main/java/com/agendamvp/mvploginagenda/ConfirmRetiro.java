package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfaceRetiroClientCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterRetiroClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class ConfirmRetiro extends AppCompatActivity implements InterfaceRetiroClientCop.view {
    TextView txtClientName,txtNcuenta,txtbalance;
    Button btnConfirmRet,btnCancelRet;
    InterfaceRetiroClientCop.presenter presenter;
    SharedPreferences sp;

    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_retiro);
        user = new Usuario();
        sp = new SharedPreferences(ConfirmRetiro.this);

        presenter = new PresenterRetiroClient(this,ConfirmRetiro.this);
        findElements();
        user = presenter.datos_cliente(sp);
        int balanceCliente = user.getSaldo();

    if (user != null){
        String nombre = user.getNombre();
        String nCuenta = user.getCard_number();
        txtClientName.setText("Estimado :"+ nombre);
        txtNcuenta.setText(nCuenta);
    }
        Bundle datos = getIntent().getExtras();
        String cc = datos.getString("DATA_CLIENT_CC");
        int value = datos.getInt("DATA_CLIENT_VALUE");
        int corresponsalBalance = datos.getInt("DATA_COP_BALANCE");
        txtbalance.setText(String.valueOf(value));
            btnConfirmRet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.setSaldo(balanceCliente);
                    user.setValor_pay_cuotes_cop(value);
                    user.setCorresponsal_balance(corresponsalBalance);
                    boolean pago = presenter.retiro_cliente(user);
                    if (pago){
                        String mensaje = "Retiro exitoso";
                        alertPerzonalizado(R.layout.positive_dialog,mensaje);
                    }else {
                        Toast.makeText(ConfirmRetiro.this, "Error al hacer el retiro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnCancelRet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mensaje = "Retiro cancelado";
                    alertPerzonalizado(R.layout.negative_dialog,mensaje);
                }
            });

    }

    @Override
    public void findElements() {
        txtClientName = findViewById(R.id.txtConfirmRetName);
        txtNcuenta = findViewById(R.id.txtConfirmNcuentaClient);
        txtbalance = findViewById(R.id.txtRetBalance);
        btnConfirmRet = findViewById(R.id.btnConfirmRetClient);
        btnCancelRet = findViewById(R.id.btnCancelRetClient);
    }

    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ConfirmRetiro.this);
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
                redireccion();
            }
        });
    }
    public void  redireccion(){
        Intent intent = new Intent( this,Corresponsal_Start.class);
        startActivity(intent);
    }

}