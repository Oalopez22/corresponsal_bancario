package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesMainActivity;
import com.agendamvp.mvploginagenda.Presenter.PresenterMainActivity;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbHelper;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, InterfacesMainActivity.View {
    EditText txtLoginEmail,txtLoginPassword;
    Button btnLogin;
    DbLogin  dblogin;
    DbHelper dbHelper;
    Usuario user;
    Usuario login;
    SharedPreferences sp;
    InterfacesMainActivity.Presenter presenter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(MainActivity.this);
        dblogin = new DbLogin(MainActivity.this);
        this.findElement();
        user = new Usuario();
        login = new Usuario();

        sp = new SharedPreferences(MainActivity.this);
        presenter = new PresenterMainActivity(this,MainActivity.this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtLoginEmail.getText().toString();
                String password = txtLoginPassword.getText().toString();

                    if (!email.equals("") && !password.equals("")){
                        user.setCorresponsal_email(email);
                        user.setCorresponsal_password(password);
                        sp.setEmailCop(email);
                        login  = presenter.Ingresar(user);
                        if (login != null){
                            int rol = login.getCorresponsal_rol();

                            int estado = login.getCorresponsal_status();
                            String nombre = login.getCorresponsal_name();
                            if (estado == 0){
                                switch (rol){
                                    case 0:
                                        ingresoAdmin();
                                    break;
                                    case 1:
                                        ingresoCop();
                                        break;
                                    default:
                                        Toast.makeText(MainActivity.this, "Error al ingresar", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "Su usuario aun no se encuentra habilitado", Toast.LENGTH_LONG).show();
                            }

                        }else {
                            Toast.makeText(MainActivity.this, "No se encontro ningun usuario", Toast.LENGTH_SHORT).show();
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

    private void ingresoAdmin(){
            Intent intent = new Intent(this,Admin_corresponsal.class);
            startActivity(intent);
    }
    private void  ingresoCop(){
        Intent intent = new Intent(this,Corresponsal_Start.class);
        startActivity(intent);
    }
}