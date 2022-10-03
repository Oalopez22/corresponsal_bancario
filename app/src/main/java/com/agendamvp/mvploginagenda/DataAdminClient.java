package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;
import com.google.android.material.internal.ForegroundLinearLayout;

import java.security.SecureRandom;
import java.util.Random;

public class DataAdminClient extends AppCompatActivity {
    DbLogin db;
    SharedPreferences sp;
    TextView txtViewDataName,txtCardNumber,txtCardbalance;
/*    TextView data;*/
    ImageView imgCard,imglogo, imgArrowBack;
    Usuario user;
    Button btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_admin_client);
        db = new DbLogin(DataAdminClient.this);
        user = new Usuario();
        sp = new SharedPreferences(DataAdminClient.this);

        user = db.mostrarDataClient(sp);
        txtViewDataName = findViewById(R.id.txtCardName);
        txtCardbalance = findViewById(R.id.txtCardBalance);
        txtCardNumber = findViewById(R.id.txtCardNumber);
        imgCard = findViewById(R.id.imgCard);
        imgCard.setImageResource(R.drawable.bkmkpbpdpcetnbw);
        imglogo = findViewById(R.id.imglogoCard);
        imglogo.setImageResource(R.drawable.pngwing_com__1_);
        /*data = findViewById(R.id.txtCardBalance);*/
        btnAceptar = findViewById(R.id.btnAceptClient);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicio();
            }
        });
        imgArrowBack = findViewById(R.id.imgArrowBackAdmin);
        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (user != null){
            txtViewDataName.setText(user.getNombre());
            txtCardbalance.setText(String.valueOf(user.getSaldo()));
            txtCardNumber.setText(user.getCard_number());
        }
    }
    private  void inicio (){
        Intent intent = new Intent(this,Admin_corresponsal.class);
        startActivity(intent);
    }
}