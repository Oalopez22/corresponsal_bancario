package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.agendamvp.mvploginagenda.Interfaces.InterfacesDepoClient;

public class Corresponsal_deposit extends AppCompatActivity implements InterfacesDepoClient.view {
    TextView tvCopName,tvCopBalance,tvCopNAcout;
    ImageView imgArrowBack;
    EditText txtCcClient,txtCcTarjet,txtValueTarjet;
    Button btnConfirmDepo,btnCancelDepo;
    InterfacesDepoClient.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_deposit);
        findElements();

        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnConfirmDepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancelDepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void findElements() {
        tvCopName = findViewById(R.id.txtCopName);
        tvCopBalance = findViewById(R.id.txtCopBalance);
        tvCopNAcout = findViewById(R.id.txtCopAcount);
        imgArrowBack = findViewById(R.id.imgArrowbackDeposit);
        txtCcClient = findViewById(R.id.txtCcClient);
        txtCcTarjet = findViewById(R.id.txtCcTarjet);
        txtValueTarjet = findViewById(R.id.txtValueTarjet);
        btnConfirmDepo = findViewById(R.id.btnDepositConfirm);
        btnCancelDepo = findViewById(R.id.btnDepositCancel);
    }
}