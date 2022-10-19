package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        if (user!=null){
            tvCopName.setText(user.getCorresponsal_name());
            tvCopBalance.setText(String.valueOf(user.getCorresponsal_balance()));
            tvCopNcuenta.setText(user.getCorreponsal_ncuenta());
        }

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
                                txtCcClientTransfer.setError("No se encontro el cliente");
                            }
                            if (datosClienteATransferir== null){
                                txtCcToTransfer.setError("No se encuentra un cliente registrado con ese numero de documento");
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
}