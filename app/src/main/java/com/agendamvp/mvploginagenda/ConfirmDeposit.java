package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDepoClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterDepoClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class ConfirmDeposit extends AppCompatActivity implements InterfacesDepoClient.view {
    TextView tvclientDepositData,tvclientToDepositData,tvValueDeposit;
    Button btnConfirmDepo,btnCancelDepo;
    InterfacesDepoClient.presenter presenter;
    SharedPreferences sp;
    Usuario user;

    String vacio ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_deposit);
        findElements();
        user = new Usuario();
        sp = new SharedPreferences(this);
        presenter = new PresenterDepoClient(this,ConfirmDeposit.this);
        Bundle datos = getIntent().getExtras();
        String ccClient = datos.getString("DATA_CLIENT_CC");
        String ccClientTarjet = datos.getString("DATA_CLIENT_TARGET");
        int balance = datos.getInt("DATA_CLIENT_VALUE");
        int balancecop = datos.getInt("DATA_COP_VALUE");
        user = presenter.datosCliente(sp);
        if (user!=null){
            String nombre = user.getNombre();
            tvclientDepositData.setText(nombre + " - " + ccClient);

        }else {
            Toast.makeText(this, "No se encontraron datos del cliente", Toast.LENGTH_LONG).show();
        }

        user = presenter.datosClienteADespositar(sp);

        if (user== null){
            Toast.makeText(this, "No se encontraron datos del cliente a depositar", Toast.LENGTH_LONG).show();
        }
            String nombreClienteAdepositar = user.getNombre_cliente_deposito();
            int saldo = user.getSaldo_cliente_deposito();
            tvclientToDepositData.setText(nombreClienteAdepositar + " - " + ccClientTarjet);
        tvValueDeposit.setText(String.valueOf(balance));
        btnConfirmDepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setSaldo(saldo);
                user.setSaldo_cliente_deposito(balance);
                user.setCorresponsal_balance(balancecop);
                boolean depositar = presenter.deposito(user);
                if (depositar){
                    redirigir();
                    Toast.makeText(ConfirmDeposit.this, "Deposito exitoso", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ConfirmDeposit.this, "Error al hacer el deposito", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void findElements() {
        tvclientDepositData = findViewById(R.id.tvClientData);
        tvclientToDepositData = findViewById(R.id.tvClientDataTarjet);
        tvValueDeposit = findViewById(R.id.tvDepoValue);
        btnConfirmDepo = findViewById(R.id.btnConfirmDepoClient);
    }
    public void redirigir(){
        Intent intent = new Intent(this,Corresponsal_Start.class);
        startActivity(intent);
    }
}