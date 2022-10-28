package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSearchClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterSeachClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class DataAdminClient extends AppCompatActivity implements InterfacesSearchClient.view {
    DbLogin db;
    SharedPreferences sp;
    TextView txtViewDataName,txtCardNumber,txtCardbalance;
    InterfacesSearchClient.presenter presenter;
/*    TextView data;*/
    ImageView imgCard,imglogo, imgArrowBack;
    Usuario user;
    Button btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_admin_client);
        db = new DbLogin(DataAdminClient.this);
        user = new Usuario();
        sp = new SharedPreferences(DataAdminClient.this);
        presenter = new PresenterSeachClient(this,DataAdminClient.this);
        findElements();
        imgCard.setImageResource(R.drawable.tarjeta);
        /*data = findViewById(R.id.txtCardBalance);*/

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicio();
            }
        });

        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        user = presenter.mostrardatos(sp);
    if (user != null){
        String nombre = user.getNombre();
        int saldo = user.getSaldo();
        String Ncard = user.getCard_number();
        txtViewDataName.setText(nombre);
        txtCardbalance.setText(String.valueOf(saldo));
        txtCardNumber.setText(Ncard);

        char caracter = Ncard.charAt(0);
        switch (caracter) {
            case '3':
                imglogo.setImageResource(R.drawable.american_express);
                break;
            case '4':
                imglogo.setImageResource(R.drawable.visa);
                break;
            case '5':
                imglogo.setImageResource(R.drawable.master_card);
                break;
            case '6':
                imglogo.setImageResource(R.drawable.unionpay_logo_svg);
                break;
            default:
                imglogo.setImageResource(R.drawable.american_express);
        }
    }else{
        inicio();
        Toast.makeText(this, "No se encontraron datos del cliente", Toast.LENGTH_LONG).show();
    }



    }
    @Override
    public void findElements() {
        txtViewDataName = findViewById(R.id.txtCardName);
        txtCardbalance = findViewById(R.id.txtCardBalance);
        txtCardNumber = findViewById(R.id.txtCardNumber);
        imgCard = findViewById(R.id.imgCard);
        imglogo = findViewById(R.id.imglogoCard);
        btnAceptar = findViewById(R.id.btnAceptClient);
        imgArrowBack = findViewById(R.id.imgArrowBackAdmin);
    }
    private  void inicio (){
        Intent intent = new Intent(this,Admin_corresponsal.class);
        startActivity(intent);
    }


}