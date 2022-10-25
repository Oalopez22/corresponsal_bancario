package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesUpdateDataCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterUpdateDataCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class Corresponsal_Update_Password extends AppCompatActivity implements InterfacesUpdateDataCop.view {

    EditText txtOldPassword,txtNewPassword,txtConfirmNewPassword;
    Button btnUpdatePass,btnCancelUpdate;
    ImageView imgArrowBack;
    SharedPreferences sp;
    Usuario datosCop;
    Usuario passwordCop;
    InterfacesUpdateDataCop.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_update_password);
        sp = new SharedPreferences(this);
        datosCop = new Usuario();
        passwordCop = new Usuario();
        presenter = new PresenterUpdateDataCop(this, Corresponsal_Update_Password.this);
        datosCop = presenter.datos_corresponsal(sp);
        String copPassword = datosCop.getCorresponsal_password();
        findElements();
        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnUpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = txtOldPassword.getText().toString();
                String newPass = txtNewPassword.getText().toString();
                String confirmPass = txtConfirmNewPassword.getText().toString();




                if (oldPass.equals("") && newPass.equals("") && confirmPass.equals("")){
                    txtOldPassword.setError("Campo obligatorio");
                    txtNewPassword.setError("Campo obligatorio");
                    txtConfirmNewPassword.setError("Campo obligatorio");
                }else{
                    if (!confirmPass.equals(newPass)){
                        Toast.makeText(Corresponsal_Update_Password.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    }else{
                        if (!oldPass.equals(copPassword)){
                            txtConfirmNewPassword.setError("La contraseña no coincide con la registrada");
                        }else{
                            passwordCop.setCorresponsal_password(confirmPass);
                            boolean confirmacion = presenter.actualizar_password(passwordCop);
                            if (confirmacion){
                                String update = "Contraseña actualizada!";
                                AlertPerzonalizado(R.layout.positive_dialog,update);
                            }else {
                                Toast.makeText(Corresponsal_Update_Password.this, "Error al actualizar la contraseña", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

            }
        });
        btnCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cancel = "Actualización cancelada!";
                AlertPerzonalizado(R.layout.negative_dialog,cancel);
            }
        });
    }

    @Override
    public void findElements() {
        imgArrowBack = findViewById(R.id.imgArrowback);
        txtOldPassword = findViewById(R.id.txtOldPass);
        txtNewPassword = findViewById(R.id.txtNewPass);
        txtConfirmNewPassword = findViewById(R.id.txtConfirmNewPass);
        btnUpdatePass = findViewById(R.id.btnConfirmUpdate);
        btnCancelUpdate = findViewById(R.id.btnCancelUpdate);
    }
    public void AlertPerzonalizado(int layout, String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Corresponsal_Update_Password.this);
        View layoutview = getLayoutInflater().inflate(layout,null);
        TextView txtmensaje = layoutview.findViewById(R.id.txtmensaje);
        txtmensaje.setText(mensaje.toUpperCase());
        Button btnExit = layoutview.findViewById(R.id.btnDialog);
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
                redireccion();
            }
        });
    }
    public void redireccion(){
        Intent intent = new Intent(Corresponsal_Update_Password.this, Corresponsal_Start.class);
        startActivity(intent);
    }
}