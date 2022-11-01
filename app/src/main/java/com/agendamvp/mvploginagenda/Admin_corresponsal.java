package com.agendamvp.mvploginagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesCopStart;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegisterCorresponsal;
import com.agendamvp.mvploginagenda.Presenter.PresenterCopStart;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class Admin_corresponsal extends AppCompatActivity implements InterfacesCopStart.view {
    ImageView imgMenuAdmin;
    TextView tvname;
    CardView cvNewCliente, cvCorresponsal,cvSearchCliente,cvSearcCorresponsal,cvListCliente,cvListCorresponsal;
    InterfacesCopStart.presenter presenter;
    SharedPreferences sp;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_corresponsal);
        findElements();
        presenter = new PresenterCopStart(this,Admin_corresponsal.this);
        user = new Usuario();
        sp = new SharedPreferences(this);
        user = presenter.data(sp);
        tvname.setText(user.getCorresponsal_name());
        imgMenuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMenu();
            }
        });

        cvNewCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vista_nuevo_cliente();
            }
        });
        cvCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vista_nuevoCorresponsal();
            }
        });

        cvSearchCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar_cliente();
            }
        });
        cvSearcCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar_corresponsal();
            }
        });
        cvListCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listado_clientes();
            }
        });
        cvListCorresponsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listado_corresponsales();
            }
        });
    }

    private  void vista_nuevo_cliente(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    private void vista_nuevoCorresponsal(){
        Intent intent = new Intent(this, RegisterAdminCorresponsal.class);
        startActivity(intent);
    }
    private  void buscar_corresponsal(){
        Intent intent = new Intent(this, SearchAdminCorresponsal.class);
        startActivity(intent);
    }
    private  void buscar_cliente(){
        Intent intent = new Intent(this, SearchClientAdmin.class);
        startActivity(intent);
    }
    private void listado_clientes(){
        Intent intent = new Intent(this,List_Admin_Clientes.class);
        startActivity(intent);
    }
    private void listado_corresponsales(){
        Intent intent = new Intent(this,List_admin_Cop.class);
        startActivity(intent);
    }
    @Override
    public void findElements() {
        tvname = findViewById(R.id.txtNombre);
        cvNewCliente = findViewById(R.id.cvNewCliente);
        cvCorresponsal = findViewById(R.id.cvNewCoresponsal);
        cvSearchCliente = findViewById(R.id.cvSearchCliente);
        cvSearcCorresponsal = findViewById(R.id.cvSearchCorresponsal);
        cvListCliente = findViewById(R.id.cvListlientes);
        cvListCorresponsal = findViewById(R.id.cvListCorresponsal);
        imgMenuAdmin = findViewById(R.id.imgMenuAdmin);
    }
    public void mostrarMenu(){
        PopupMenu pm = new PopupMenu(this,imgMenuAdmin);
        pm.getMenuInflater()
                .inflate(R.menu.menuadmincop , pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.MenuUpdateCop:
                        Toast.makeText(Admin_corresponsal.this, "Boton Corresponsal Presionado", Toast.LENGTH_SHORT).show();
                        return true;
                    case  R.id.MenuUpdateClient:
                        Toast.makeText(Admin_corresponsal.this, "Boton Cliente Presionado", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.MenuCloseSession:
                        inicio();
                        return true;
                }
                return false;
            }
        });
        pm.show();
    }
    public  void inicio(){
        Intent intent = new Intent(Admin_corresponsal.this,MainActivity.class);
        startActivity(intent);
    }


}