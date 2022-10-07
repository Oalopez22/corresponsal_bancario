package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesMainActivity;
import com.agendamvp.mvploginagenda.Presenter.PresenterMainActivity;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbHelper;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, InterfacesMainActivity.View {
    EditText txtLoginEmail,txtLoginPassword;
    Button btnLogin;
    DbLogin  dblogin;
    DbHelper dbHelper;
    Usuario user;
    SharedPreferences sp;
    InterfacesMainActivity.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(MainActivity.this);
        dblogin = new DbLogin(MainActivity.this);
        this.findElement();
        user = new Usuario();
        sp = new SharedPreferences(MainActivity.this);
        presenter = new PresenterMainActivity(this,MainActivity.this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtLoginEmail.getText().toString();
                String password = txtLoginPassword.getText().toString();
                ingresoAdmin(email,password);
                if (!email.equals("") && !password.equals("")){
                    user.setCorresponsal_email(email);
                    user.setCorresponsal_password(password);
                    sp.setEmailCop(email);
                    boolean id = presenter.Ingresar(user);
                    if (id){
                        ingreso();

                    }

                }else{
                    txtLoginEmail.setError("Campo Email obligatorio");
                    txtLoginPassword.setError("Campo Contraseña obligatorio");
                }
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

    private void ingresoAdmin(String email, String password){
        email = txtLoginEmail.getText().toString();
       password = txtLoginPassword.getText().toString();
        if (email.equals( "admin@wposs.com") & password.equals("Admin123*")){
            Intent intent = new Intent(this,Admin_corresponsal.class);
            startActivity(intent);
        }
    }
    private void  ingreso(){
        Intent intent = new Intent(this,Corresponsal_Start.class);
        startActivity(intent);
    }
}