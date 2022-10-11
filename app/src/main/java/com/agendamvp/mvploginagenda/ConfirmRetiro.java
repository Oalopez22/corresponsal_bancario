package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Interfaces.InterfaceRetiroClientCop;

public class ConfirmRetiro extends AppCompatActivity implements InterfaceRetiroClientCop.view {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_retiro);
        Bundle datos = getIntent().getExtras();
        String cc = datos.getString("DATA_CLIENT_CC");
        int value = datos.getInt("DATA_CLIENT_VALUE");

    }

    @Override
    public void findElements() {

    }
}