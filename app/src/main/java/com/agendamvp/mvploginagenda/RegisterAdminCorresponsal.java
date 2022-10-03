package com.agendamvp.mvploginagenda;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegisterCorresponsal;
import com.agendamvp.mvploginagenda.Presenter.PresenterRegisterCorreponsal;

import java.util.regex.Pattern;

public class RegisterAdminCorresponsal extends AppCompatActivity implements InterfacesRegisterCorresponsal.view {
    EditText txtNewCopName,txtNewCopNit,txtNewCopEmail,txtNewCopPass;;
    Button btnConfirmNewCop;
    InterfacesRegisterCorresponsal.presenter presenter;
    Usuario user;

    private  static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin_corresponsal);
        presenter = new PresenterRegisterCorreponsal(this , RegisterAdminCorresponsal.this);
        user = new Usuario();
        findelements();


        btnConfirmNewCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = txtNewCopName.getText().toString();
                String nit = txtNewCopNit.getText().toString();
                String password = txtNewCopPass.getText().toString();
                String email = txtNewCopEmail.getText().toString();
                if ( cadenaVacia(nombre) && cadenaVacia(nit) && validarEmail(email) && cadenaVacia(password)){
                    user.setCorresponsal_name(nombre);
                    user.setCorresponsal_nit(nit);
                    user.setCorresponsal_email(email);
                    user.setCorresponsal_password(password);
                    long id = presenter.registrar_corresponsal(user);
                        if (id > 0 ){
                            Toast.makeText(RegisterAdminCorresponsal.this, " Corresponsal creado", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterAdminCorresponsal.this, "Error al registrar el corresponsal", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
    }
    private  boolean cadenaVacia(String cadena){
        return !cadena.equals("");
    }

    @Override
    public void findelements() {
        txtNewCopName = findViewById(R.id.txtNewCop);
        txtNewCopNit = findViewById(R.id.txtnewnitcop);
        txtNewCopEmail = findViewById(R.id.txtNewEmailCop);
        txtNewCopPass = findViewById(R.id.txtNewPassCop);
        btnConfirmNewCop = findViewById(R.id.btnConfirmarNewCop);
    }


    private  boolean validarEmail(String email){
         email = txtNewCopEmail.getText().toString();
        if (email.equals("")){
            txtNewCopEmail.setError("Campo obligatorio");
            return false;
            }else {
            return true;
        }
    }
/*    private boolean validarPassword(String Pass){
         Pass = txtNewCopPass.getText().toString();
        if (Pass.equals("")){
            txtNewCopPass.setError("Campo obligatorio");
                    }else if(!PASSWORD_PATTERN.matcher(Pass).matches()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterAdminCorresponsal.this);
                    builder.setMessage("La contraseña debe incluir al menos un número, una letra en mayúscula, una  en minúscula y no debe incluir espacios")
                            .setTitle("Error")
                            .setCancelable(false)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                    return false;
                }
        else {
            return true;
        }
    }*/
}