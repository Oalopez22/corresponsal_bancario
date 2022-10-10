package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesCopStart;
import com.agendamvp.mvploginagenda.Presenter.PresenterCopStart;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Corresponsal_Start extends AppCompatActivity  implements InterfacesCopStart.view {
    TextView txtCopName,txtCopBalance,txtCopAcount;
    CardView cvPayCard,cvRet;
    DbLogin db;
    Usuario user;
   SharedPreferences sp;
    InterfacesCopStart.presenter presenter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_start);
        findElements();
        db = new DbLogin(Corresponsal_Start.this);
        sp = new SharedPreferences(Corresponsal_Start.this);
        user = new Usuario();
        presenter = new PresenterCopStart(this,Corresponsal_Start.this);

        user = presenter.data(sp);

        if (presenter != null){
            txtCopName.setText(user.getCorresponsal_name());
            txtCopBalance.setText(String.valueOf(user.getCorresponsal_balance()));
            txtCopAcount.setText(user.getCorreponsal_ncuenta());
        }

        cvPayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagotarjeta();
            }
        });

        cvRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retiros();
            }
        });


    }

    @Override
    public void findElements() {
        cvPayCard = findViewById(R.id.cvPayCard);
        cvRet = findViewById(R.id.cvRetiro);
        txtCopName = findViewById(R.id.txtCopName);
        txtCopBalance = findViewById(R.id.txtCopBalance);
        txtCopAcount = findViewById(R.id.txtCopAcount);
    }

    public void pagotarjeta(){
        Intent intent = new Intent(Corresponsal_Start.this,PayWithCard.class);
        startActivity(intent);
    }
    public void retiros(){
        Intent intent = new Intent(Corresponsal_Start.this, Corresponsal_Retiro.class);
        startActivity(intent);
    }


}