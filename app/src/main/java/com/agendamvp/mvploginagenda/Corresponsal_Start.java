package com.agendamvp.mvploginagenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesCopStart;
import com.agendamvp.mvploginagenda.Presenter.PresenterCopStart;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class Corresponsal_Start extends AppCompatActivity  implements InterfacesCopStart.view {
    TextView txtCopName,txtCopBalance,txtCopAcount;
    CardView cvPayCard,cvRet,cvDepo,cvTransfer,cvHistory,cvClientBalance;
    DbLogin db;
    Usuario user;
   SharedPreferences sp;
   ImageView imgMenuCop;
    InterfacesCopStart.presenter presenter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corresponsal_start);
        findElements();
        db = new DbLogin(Corresponsal_Start.this);
        sp = new SharedPreferences(Corresponsal_Start.this);
        user = new Usuario();
        presenter = new PresenterCopStart(this,Corresponsal_Start.this);

        user = presenter.data(sp);

        if (presenter != null){
            txtCopName.setText(user.getCorresponsal_name());
            txtCopBalance.setText(String.valueOf(user.getCorresponsal_balance()));
            txtCopAcount.setText(user.getCorreponsal_ncuenta());
        }

        imgMenuCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuCorresponsal();
            }
        });
        cvPayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagotarjeta();
            }
        });

        cvRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retiros();
            }
        });
        cvDepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depositos();
            }
        });
        cvTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferencias();
            }
        });
        cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historal();
            }
        });
        cvClientBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saldo();
            }
        });

    }

    @Override
    public void findElements() {
        cvPayCard = findViewById(R.id.cvPayCard);
        cvRet = findViewById(R.id.cvRetiro);
        cvDepo = findViewById(R.id.cvDeposit);
        cvTransfer = findViewById(R.id.cvTransfer);
        cvHistory = findViewById(R.id.cvHistory);
        cvClientBalance = findViewById(R.id.cvClienBalance);
        txtCopName = findViewById(R.id.txtCopName);
        txtCopBalance = findViewById(R.id.txtCopBalance);
        txtCopAcount = findViewById(R.id.txtCopAcount);
        imgMenuCop = findViewById(R.id.imgMenuCop);
    }
    public void menuCorresponsal(){
        PopupMenu pm = new PopupMenu(this,imgMenuCop);
        pm.getMenuInflater()
                .inflate(R.menu.menucorresponsal, pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.menuUpdateDataCop:
                        Toast.makeText(Corresponsal_Start.this, "Boton actualizar presionado", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.menuCreateClientCop:
                        crear_cliente();
                        return true;
                    case R.id.menuExitCop:
                        finish();
                        return true;
                }
                return false;
            }
        });
        pm.show();
    }


    public void pagotarjeta(){
        Intent intent = new Intent(Corresponsal_Start.this,PayWithCard.class);
        startActivity(intent);
    }
    public void retiros(){
        Intent intent = new Intent(Corresponsal_Start.this, Corresponsal_Retiro.class);
        startActivity(intent);
    }
    public void depositos(){
        Intent intent = new Intent(Corresponsal_Start.this,Corresponsal_deposit.class);
        startActivity(intent);
    }
    public void transferencias(){
        Intent intent = new Intent(Corresponsal_Start.this, Corresponsal_Transfer.class);
        startActivity(intent);
    }
    public void historal(){
        Intent intent = new Intent(Corresponsal_Start.this, Corresponsal_History.class);
        startActivity(intent);
    }
    public void saldo(){
        Intent intent = new Intent(Corresponsal_Start.this, Corresponsal_Client_Balance.class);
        startActivity(intent);
    }
    public void crear_cliente(){
        Intent intent = new Intent(Corresponsal_Start.this,Corresponsal_Register_Client.class);
        startActivity(intent);
    }


}