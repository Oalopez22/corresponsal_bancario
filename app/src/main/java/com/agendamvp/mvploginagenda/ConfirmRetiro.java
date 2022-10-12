package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

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

    if (user != null){
        String nombre = user.getNombre();
        String nCuenta = user.getCard_number();
        int balance = user.getSaldo();
        txtClientName.setText("Estimado :"+ nombre);
        txtNcuenta.setText(nCuenta);
    }
        Bundle datos = getIntent().getExtras();
        String cc = datos.getString("DATA_CLIENT_CC");
        int value = datos.getInt("DATA_CLIENT_VALUE");
        txtbalance.setText(String.valueOf(value));
            btnConfirmRet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean pago = presenter.retiro_cliente(user);
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
}