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
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDepoClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterDepoClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class Corresponsal_deposit extends AppCompatActivity implements InterfacesDepoClient.view {
    TextView tvCopName,tvCopBalance,tvCopNAcout;
    ImageView imgArrowBack;
    EditText txtCcClient,txtCcTarjet,txtValueTarjet;
    Button btnConfirmDepo,btnCancelDepo;
    SharedPreferences sp;
    Usuario user;
    InterfacesDepoClient.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_deposit);
        findElements();
        sp = new SharedPreferences(this);
        user = new Usuario();
        presenter = new PresenterDepoClient(this,Corresponsal_deposit.this);
        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        user = presenter.data_cop(sp);

        if (user != null){
            tvCopName.setText(user.getCorresponsal_name());
            tvCopBalance.setText(String.valueOf(user.getCorresponsal_balance()));
            tvCopNAcout.setText(user.getCorreponsal_ncuenta());
        }
        btnConfirmDepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ccCliente = txtCcClient.getText().toString();
                String ccClienteToDepo = txtCcTarjet.getText().toString();
                String monto = txtValueTarjet.getText().toString();
                int saldo = user.getCorresponsal_balance();
                if (!ccCliente.equals("") && !ccClienteToDepo.equals("") && !monto.equals("")){
                    int balance = Integer.parseInt(monto);

                    redireccion(ccCliente,ccClienteToDepo,balance,saldo);
                }else {
                    txtCcClient.setError("Campo obligatorio");
                    txtCcTarjet.setError("Campo obligatorio");
                    txtValueTarjet.setError("Campo obligatorio");
                }
            }
        });

        btnCancelDepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Deposito cancelado";
            AlertPerzonalizado(R.layout.negative_dialog,mensaje);
            }
        });

    }

    @Override
    public void findElements() {
        tvCopName = findViewById(R.id.txtCopName);
        tvCopBalance = findViewById(R.id.txtCopBalance);
        tvCopNAcout = findViewById(R.id.txtCopAcount);
        imgArrowBack = findViewById(R.id.imgArrowbackDeposit);
        txtCcClient = findViewById(R.id.txtCcClient);
        txtCcTarjet = findViewById(R.id.txtCcTarjet);
        txtValueTarjet = findViewById(R.id.txtValueTarjet);
        btnConfirmDepo = findViewById(R.id.btnDepositConfirm);
        btnCancelDepo = findViewById(R.id.btnDepositCancel);
    }

    public void AlertPerzonalizado(int layout, String mensaje){
       AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Corresponsal_deposit.this);
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
                redireccion();
            }
        });
    }
    private void redireccion(String cc, String ccTarjet,int value,int saldoCop){
        sp.setCcUser(cc);
        sp.setCcDeposit(ccTarjet);
        Intent intent = new Intent(Corresponsal_deposit.this,ConfirmDeposit.class);
        intent.putExtra("DATA_CLIENT_CC",cc);
        intent.putExtra("DATA_CLIENT_TARGET",ccTarjet);
        intent.putExtra("DATA_CLIENT_VALUE",value);
        intent.putExtra("DATA_COP_VALUE",saldoCop);
        startActivity(intent);
    }

    public void redireccion(){
        Intent intent = new Intent(Corresponsal_deposit.this, Corresponsal_Start.class);
        startActivity(intent);
    }
}