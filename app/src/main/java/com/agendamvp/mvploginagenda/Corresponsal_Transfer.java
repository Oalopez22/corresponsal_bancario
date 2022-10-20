package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        datosCliente = new Usuario();
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
                            if ( datosCliente== null){
                                txtCcClientTransfer.setError("No se encontro el cliente con ese numero de documento");
                            }
                            if (datosClienteATransferir== null){
                                txtCcToTransfer.setError("No se encuentra un cliente registrado con ese numero de documento");
                            }

                    int pincliente = datosCliente.getPin();


                                            /*  ALERTDIALOG PIN*/

                    AlertDialog.Builder builderpin = new AlertDialog.Builder(Corresponsal_Transfer.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_pin_pay_card,null);
                    builderpin.setView(view);
                    AlertDialog dialog = builderpin.create();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    EditText txtPinTransfer = view.findViewById(R.id.txtClientCardPin);

                    Button btnConfirmPin,btnCancelPinTransfer;
                    btnConfirmPin = view.findViewById(R.id.btnAceptCardPin);
                    btnConfirmPin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String pin = txtPinTransfer.getText().toString();
                            int pinConvertido = Integer.parseInt(pin);
                                /* ALERTDIALOG CONFIRMAR PIN */

                            AlertDialog.Builder builderpin = new AlertDialog.Builder(Corresponsal_Transfer.this);
                            LayoutInflater inflater = getLayoutInflater();
                            View view = inflater.inflate(R.layout.dialog_pin_confirm_retiro,null);
                            builderpin.setView(view);
                            AlertDialog dialog = builderpin.create();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();
                            EditText txtConfirmPin;
                            Button btnConfirmPin,btnCancelConfirmPin;
                            txtConfirmPin = view.findViewById(R.id.txtConfirmPinRet);

                            btnConfirmPin = view.findViewById(R.id.btnAceptConfirmPin);
                            btnConfirmPin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String confirmPin = txtConfirmPin.getText().toString();
                                    int convertConfirm = Integer.parseInt(confirmPin);
                                    if (convertConfirm == pinConvertido && convertConfirm == pincliente ){
                                        int saldoCliente = datosCliente.getSaldo();
                                        int saldoClienteTransferido = datosClienteATransferir.getSaldo_cliente_deposito();

                                        if (saldoCliente < transferValue ){
                                            Toast.makeText(Corresponsal_Transfer.this, "Saldo insuficiente", Toast.LENGTH_LONG).show();
                                        }else{
                                            user.setSaldo(saldoCliente);
                                            user.setSaldo_cliente_deposito(saldoClienteTransferido);
                                            user.setValor_pay_card_cop(transferValue);
                                            user.setCorresponsal_balance(copBalance);
                                            boolean exito = presenter.transferencia(user);
                                            if (exito){
                                                redireccion();
                                                Toast.makeText(Corresponsal_Transfer.this, "Transferencia Exitosa", Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(Corresponsal_Transfer.this, "No se pudo hacer la transferencia", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                    }else {
                                        Toast.makeText(Corresponsal_Transfer.this, "El pin no coincide", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                            btnCancelConfirmPin = view.findViewById(R.id.btnCancelConfirmPin);
                            btnCancelConfirmPin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }
                    });
                    btnCancelPinTransfer = view.findViewById(R.id.btnCancelCardPin);

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

            }
        });
    }
        private void redireccion(){
            Intent intent = new Intent(Corresponsal_Transfer.this,Corresponsal_Start.class);
            startActivity(intent);
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
}