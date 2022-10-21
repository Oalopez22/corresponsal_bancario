package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesBalanceClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterBalanceClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class Corresponsal_Data_Client extends AppCompatActivity implements InterfacesBalanceClient.view {
    TextView tvCopNAme,tvCopBalance,tvCopAcount,tvClienName,tvClientCc,tvClientBalance;
    ImageView imgArrowback;
    Button btnAcept;
    Usuario corresponsal;
    Usuario cliente;
    SharedPreferences sp;
    InterfacesBalanceClient.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_data_client);
        FindElements();
        corresponsal = new Usuario();
        cliente = new Usuario();
        sp = new SharedPreferences(this);
        presenter = new PresenterBalanceClient(this,Corresponsal_Data_Client.this);
        corresponsal = presenter.datosCorresponsal(sp);
        if (corresponsal!=null){
            tvCopNAme.setText(corresponsal.getCorresponsal_name());
            tvCopBalance.setText(String.valueOf(corresponsal.getCorresponsal_balance()));
            tvCopAcount.setText(corresponsal.getCorreponsal_ncuenta());
        }

        cliente = presenter.datosDevueltos(sp);
        if (cliente!= null){
            tvClienName.setText(cliente.getNombre());
            tvClientCc.setText(cliente.getDocumento());
            tvClientBalance.setText(String.valueOf(cliente.getSaldo()));
        }
        imgArrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redireccion();
            }
        });
        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redireccion();
            }
        });

    }

    @Override
    public void FindElements() {
        tvCopNAme = findViewById(R.id.txtCopName);
        tvCopBalance = findViewById(R.id.txtCopBalance);
        tvCopAcount = findViewById(R.id.txtCopAcount);
        tvClienName = findViewById(R.id.tvClientName);
        tvClientCc = findViewById(R.id.tvClientCc);
        tvClientBalance = findViewById(R.id.tvClientBalance);
        imgArrowback = findViewById(R.id.imgArrowbackDeposit);
        btnAcept = findViewById(R.id.btnAceptBalance);
    }

    public void redireccion(){
        Intent intent = new Intent(Corresponsal_Data_Client.this,Corresponsal_Start.class);
        startActivity(intent);
    }

}