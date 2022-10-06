package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Corresponsal_Start extends AppCompatActivity {
    CardView cvPayCard,cvRet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_start);
        cvPayCard = findViewById(R.id.cvPayCard);
        cvPayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagotarjeta();
            }
        });
        cvRet = findViewById(R.id.cvRetiro);
        cvRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retiros();
            }
        });
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