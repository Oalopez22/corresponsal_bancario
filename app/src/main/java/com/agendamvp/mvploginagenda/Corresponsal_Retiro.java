package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfaceRetiroClientCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesCopStart;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesPayCardCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterCopStart;
import com.agendamvp.mvploginagenda.Presenter.PresenterRetiroClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class Corresponsal_Retiro extends AppCompatActivity implements InterfaceRetiroClientCop.view {
    EditText txtCc,txtBalance;
    TextView txtCopName,txtCopBalance,txtCopNcuenta;
    ImageView imgArrowback;
    Button btnConfirm,btnCancel;
    SharedPreferences sp;
    InterfaceRetiroClientCop.presenter presenter;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_retiro);
        sp = new SharedPreferences(Corresponsal_Retiro.this);
        findElements();
        presenter = new PresenterRetiroClient(this,Corresponsal_Retiro.this);
        user = presenter.data_cop(sp);
        if (presenter != null){
            txtCopName.setText(user.getCorresponsal_name());
            txtCopBalance.setText(String.valueOf(user.getCorresponsal_balance()));
            txtCopNcuenta.setText(String.valueOf(user.getCorreponsal_ncuenta()));
        }
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String cedula = txtCc.getText().toString();
            String monto = txtBalance.getText().toString();
            if (cedula.equals("") && monto.equals("")){
                Toast.makeText(Corresponsal_Retiro.this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
            }else{

                int valor = Integer.parseInt(monto);
                AlertDialog.Builder builderpin = new AlertDialog.Builder(Corresponsal_Retiro.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_pin_pay_card,null);
                builderpin.setView(view);
                AlertDialog dialog = builderpin.create();
                dialog.setCancelable(false);

                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                EditText txtRetPin;
                txtRetPin = view.findViewById(R.id.txtClientCardPin);

                Button btnAceptar, btncancelar;
                btnAceptar = view.findViewById(R.id.btnAceptCardPin);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String datopin = txtRetPin.getText().toString();

                        int pin = Integer.parseInt(datopin);
                       AlertDialog.Builder builderpin = new AlertDialog.Builder(Corresponsal_Retiro.this);
                        LayoutInflater inflater = getLayoutInflater();
                        View view = inflater.inflate(R.layout.dialog_pin_confirm_retiro,null);
                        builderpin.setView(view);
                        AlertDialog dialog = builderpin.create();
                        dialog.setCancelable(false);

                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        EditText txtConfirmPin;
                        txtConfirmPin = view.findViewById(R.id.txtConfirmPinRet);

                        Button btnAceptar;
                        btnAceptar = view.findViewById(R.id.btnAceptConfirmPin);
                        btnAceptar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String ConfirmPins = txtConfirmPin.getText().toString();
                                    int ConfirmPin = Integer.parseInt(ConfirmPins);
                                if (ConfirmPin != pin ){
                                    Toast.makeText(Corresponsal_Retiro.this, "El pin coincide, intente nuevamente", Toast.LENGTH_LONG).show();
                                }else{
                                        redireccion(cedula,valor,ConfirmPin);
                                }

                            }
                        });
                    }
                });

                btncancelar = view.findViewById(R.id.btnCancelCardPin);
                btncancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mensaje = " Retiro cancelado";
                        alertPerzonalizado(R.layout.negative_dialog,mensaje);
                    }
                });

            }
            }
        });

        imgArrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Deposito cancelado";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });
    }

    @Override
    public void findElements() {
        txtCc = findViewById(R.id.txtRetiroCc);
        txtBalance = findViewById(R.id.txtRetiroBalance);
        txtCopName = findViewById(R.id.txtCopName);
        txtCopBalance = findViewById(R.id.txtCopBalance);
        txtCopNcuenta = findViewById(R.id.txtCopAcount);
        imgArrowback = findViewById(R.id.imgArrowbackRet);
        btnConfirm = findViewById(R.id.btnRetConfirm);
        btnCancel = findViewById(R.id.btnRetCancel);

        /*https://www.youtube.com/watch?v=tK7MC8KzkYA&ab_channel=T0pCode*/
    }


    public void  redireccion(String cc, int valor, int pin){
        sp.setCcUser(cc);

        Intent intent = new Intent(this,ConfirmRetiro.class);
        int balanceCop = user.getCorresponsal_balance();
        intent.putExtra("DATA_CLIENT_CC",cc);
        intent.putExtra("DATA_CLIENT_VALUE",valor);
        intent.putExtra("DATA_CLIENT_PIN",pin);
        intent.putExtra("DATA_COP_BALANCE",balanceCop);
        startActivity(intent);
    }
    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Corresponsal_Retiro.this);
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
                redirigir();
            }
        });
    }
    public void redirigir(){
        Intent intent = new Intent(Corresponsal_Retiro.this, Corresponsal_Start.class);
        startActivity(intent);
    }

}