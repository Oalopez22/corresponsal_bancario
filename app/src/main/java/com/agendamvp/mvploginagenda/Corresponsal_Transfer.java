package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesTransferClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterTransferClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class Corresponsal_Transfer extends AppCompatActivity implements InterfacesTransferClient.view {
    TextView tvCopName,tvCopBalance,tvCopNcuenta;
    ImageView imgArrowBack;
    EditText txtCcToTransfer,txtCcClientTransfer,txtTransferValue;
    Button btnConfirmTransfer,btnCancelTransfer;
    SharedPreferences sp;
    InterfacesTransferClient.presenter presenter;
    Usuario user;
    Usuario datosCliente;
    Usuario datosClienteATransferir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_transfer);
        findElements();

        user = new Usuario();

        datosClienteATransferir = new Usuario();

        sp = new SharedPreferences(this);
        presenter = new PresenterTransferClient(this,Corresponsal_Transfer.this);
        user = presenter.data_cop(sp);


            tvCopName.setText(user.getCorresponsal_name());
            tvCopBalance.setText(String.valueOf(user.getCorresponsal_balance()));
            tvCopNcuenta.setText(user.getCorreponsal_ncuenta());

        int copBalance = user.getCorresponsal_balance();
        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnConfirmTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ccATransferir = txtCcToTransfer.getText().toString();
                String ccTransfiere = txtCcClientTransfer.getText().toString();
                String value = txtTransferValue.getText().toString();
                if (!ccATransferir.equals("") && !ccTransfiere.equals("") && !value.equals("")){
                    int transferValue = Integer.parseInt(value);
                    sp.setCcUser(ccTransfiere);
                    sp.setCcDeposit(ccATransferir);
                    datosCliente = presenter.datosCliente(sp);
                    datosClienteATransferir = presenter.datosClienteATransferir(sp);
                            if (datosCliente != null && datosClienteATransferir != null){
                                pin(transferValue,datosCliente,datosClienteATransferir,copBalance);
                            }else{
                                Toast.makeText(Corresponsal_Transfer.this, "No se encontraron datos de los clientes", Toast.LENGTH_LONG).show();
                            }
                }else {
                    txtCcToTransfer.setError("Campo obligatorio");
                    txtCcClientTransfer.setError("Campo obligatorio");
                    txtTransferValue.setError("Campo obligatorio");
                }
            }
        });
        btnCancelTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Transferencia cancelada";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });
    }

    @Override
    public void findElements() {
        tvCopName = findViewById(R.id.txtCopName);
        tvCopBalance = findViewById(R.id.txtCopBalance);
        tvCopNcuenta = findViewById(R.id.txtCopAcount);
        imgArrowBack = findViewById(R.id.imgArrowbackDeposit);
        txtCcToTransfer = findViewById(R.id.txtCctoClientTransfer);
        txtCcClientTransfer = findViewById(R.id.txtCcClient);
        txtTransferValue = findViewById(R.id.txtTransferValue);
        btnConfirmTransfer = findViewById(R.id.btnConfirmTransfer);
        btnCancelTransfer = findViewById(R.id.btnCancelTransfer);

    }


    public void pin(int balance, Usuario datosCliente, Usuario datosClienteAtransferir,int copBalance){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Corresponsal_Transfer.this);
        View layoutview = getLayoutInflater().inflate(R.layout.dialog_pin_pay_card,null);
        dialogBuilder.setView(layoutview);
        AlertDialog alert = dialogBuilder.create();
        alert.show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText txtRetPin;
        txtRetPin = layoutview.findViewById(R.id.txtClientCardPin);
        int pincliente = datosCliente.getPin();
        Button btnAceptar, btncancelar;
        btnAceptar = layoutview.findViewById(R.id.btnAceptCardPin);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datopin = txtRetPin.getText().toString();
                int pin = Integer.parseInt(datopin);
                confirmPin(pin,pincliente,balance,datosClienteAtransferir,copBalance);
            }
        });
        btncancelar = layoutview.findViewById(R.id.btnCancelCardPin);
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = " Retiro cancelado";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });
    }



    public void confirmPin (int pindigitado,int pincliente, int balance,Usuario datosClienteAtransferir, int copBalance){
        AlertDialog.Builder builderpin = new AlertDialog.Builder(Corresponsal_Transfer.this);
        View layoutview = getLayoutInflater().inflate(R.layout.dialog_pin_confirm_retiro,null);
        builderpin.setView(layoutview);
        AlertDialog dialog = builderpin.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText txtConfirmPin;
        txtConfirmPin = layoutview.findViewById(R.id.txtConfirmPinRet);
        Button btnAceptar,btnCancelar;
        btnAceptar = layoutview.findViewById(R.id.btnAceptConfirmPin);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new Usuario();
                //String ConfirmPins = txtConfirmPin.getText().toString();
                String confirmPin = txtConfirmPin.getText().toString();
                int convertConfirm = Integer.parseInt(confirmPin);
                if (convertConfirm == pindigitado && convertConfirm == pincliente ){
                    int saldoCliente = datosCliente.getSaldo();
                    int saldoClienteTransferido = datosClienteATransferir.getSaldo_cliente_deposito();

                    if (saldoCliente < balance ){
                        Toast.makeText(Corresponsal_Transfer.this, "Saldo insuficiente", Toast.LENGTH_LONG).show();
                    }else{
                        user.setSaldo(saldoCliente);
                        user.setSaldo_cliente_deposito(saldoClienteTransferido);
                        user.setValor_pay_card_cop(balance);
                        user.setCorresponsal_balance(copBalance);
                        boolean exito = presenter.transferencia(user);
                        if (exito){
                            String mensaje = "Transferencia exitosa";
                            alertPerzonalizado(R.layout.positive_dialog,mensaje);
                        }else{
                            Toast.makeText(Corresponsal_Transfer.this, "No se pudo hacer la transferencia", Toast.LENGTH_LONG).show();
                        }
                    }

                }else {
                    Toast.makeText(Corresponsal_Transfer.this, "El pin no coincide", Toast.LENGTH_LONG).show();
                }


            }
        });
        btnCancelar = layoutview.findViewById(R.id.btnCancelConfirmPin);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String mensaje = "Transferencia cancelada";
                alertPerzonalizado(R.layout.negative_dialog,mensaje);*/
                dialog.dismiss();
            }
        });
    }

    public void alertPerzonalizado(int layout,String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Corresponsal_Transfer.this);
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
        Intent intent = new Intent(Corresponsal_Transfer.this, Corresponsal_Start.class);
        startActivity(intent);
    }
}