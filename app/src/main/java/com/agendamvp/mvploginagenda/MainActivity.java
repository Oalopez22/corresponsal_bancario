package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agendamvp.mvploginagenda.Interfaces.InterfacesMainActivity;
import com.agendamvp.mvploginagenda.Presenter.PresenterMainActivity;
import com.agendamvp.mvploginagenda.db.DbHelper;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, InterfacesMainActivity.View {
    EditText txtLoginEmail,txtLoginPassword;
    Button btnLogin;
    DbLogin  dblogin;
    DbHelper dbHelper;
    InterfacesMainActivity.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(MainActivity.this);
        dblogin = new DbLogin(MainActivity.this);
        this.findElement();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresoAdmin();
            }
        });
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void findElement() {
        txtLoginEmail = findViewById(R.id.txtLoginEmail);
        txtLoginPassword = findViewById(R.id.txtLoginPassword);
        btnLogin = findViewById(R.id.btnLoginIngresar);
    }

    private void ingresoAdmin(){
        String correoAdmin = txtLoginEmail.getText().toString();
        String passwordAdmin = txtLoginPassword.getText().toString();
        if (correoAdmin.equals( "admin@wposs.com") & passwordAdmin.equals("Admin123*")){
            Intent intent = new Intent(this,Admin_corresponsal.class);
            startActivity(intent);
        }
    }
}