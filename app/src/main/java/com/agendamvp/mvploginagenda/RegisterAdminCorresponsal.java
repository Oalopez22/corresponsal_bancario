package com.agendamvp.mvploginagenda;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;

import android.text.Layout;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegisterCorresponsal;
import com.agendamvp.mvploginagenda.Presenter.PresenterRegisterCorreponsal;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

import java.util.regex.Pattern;

public class RegisterAdminCorresponsal extends AppCompatActivity implements InterfacesRegisterCorresponsal.view {
    EditText txtNewCopName,txtNewCopNit,txtNewCopEmail,txtNewCopPass;;
    Button btnConfirmNewCop,btnCancel;
    ImageView imgArrowback;
    InterfacesRegisterCorresponsal.presenter presenter;
    Usuario user;
    SharedPreferences sp;

    private  static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin_corresponsal);
        presenter = new PresenterRegisterCorreponsal(this , RegisterAdminCorresponsal.this);
        user = new Usuario();
        sp = new SharedPreferences(this);
        findelements();
        btnConfirmNewCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNewCopName.getText().toString();
                String nit = txtNewCopNit.getText().toString();
                String password = txtNewCopPass.getText().toString();
                String email = txtNewCopEmail.getText().toString();
                if ( cadenaVacia(nombre) && cadenaVacia(nit) && validarEmail(email) && validarPassword(password)){
                    sp.setNitCop(nit);
                    user.setCorresponsal_name(nombre);
                    user.setCorresponsal_nit(nit);
                    user.setCorresponsal_email(email);
                    user.setCorresponsal_password(password);

                    boolean info = presenter.validar_existencia(sp);
                    if (info){
                        Toast.makeText(RegisterAdminCorresponsal.this, "Ya existe un corresponsal registrado con este nit ", Toast.LENGTH_SHORT).show();
                    }else{
                        long id = presenter.registrar_corresponsal(user);
                        if (id > 0 ){
                            String mensaje = "Corresponsal registrado";
                            alertPerzonalizado(R.layout.positive_dialog,mensaje);
                        }else {
                            Toast.makeText(RegisterAdminCorresponsal.this, "Error al registrar el corresponsal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    txtNewCopName.setError("Campo obligatorio");
                    txtNewCopNit.setError("Campo obligatorio");
                    txtNewCopEmail.setError("Campo obligatorio");
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Registro cancelado";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });

        imgArrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private  boolean cadenaVacia(String cadena){
        return !cadena.equals("");
    }

    @Override
    public void findelements() {
        imgArrowback = findViewById(R.id.imgArrowBackAdmin);
        txtNewCopName = findViewById(R.id.txtNewCop);
        txtNewCopNit = findViewById(R.id.txtnewnitcop);
        txtNewCopEmail = findViewById(R.id.txtNewEmailCop);
        txtNewCopPass = findViewById(R.id.txtNewPassCop);
        btnConfirmNewCop = findViewById(R.id.btnConfirmarNewCop);
        btnCancel = findViewById(R.id.btnCancelCop);
    }

    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(RegisterAdminCorresponsal.this);
        View layoutview = getLayoutInflater().inflate(layout,null);
        Button btnExit = layoutview.findViewById(R.id.btnDialog);
        TextView txtmensaje = layoutview.findViewById(R.id.txtmensaje);
        txtmensaje.setText(mensaje.toUpperCase());
        dialogBuilder.setView(layoutview);
        AlertDialog alert = dialogBuilder.create();
        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });
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
    private boolean validarPassword(String Pass){
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
        return false;
    }
    private  void salir(){
        Intent intent = new Intent(this,Admin_corresponsal.class);
        startActivity(intent);
    }
}