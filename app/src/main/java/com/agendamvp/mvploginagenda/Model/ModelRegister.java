package com.agendamvp.mvploginagenda.Model;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Presenter.PresenterRegister;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelRegister implements InterfacesRegister.Model {
    InterfacesRegister.Presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelRegister(InterfacesRegister.Presenter presenter, Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }


    @Override
    public boolean validar_existencia(SharedPreferences sp) {
        boolean info = false;
        info = db.validarExistenciaCliente(sp);
        return info;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public long  Usuario(Usuario user) {
        long id=0;
        id = db.crearUsuario(user);
        return id;
    }
}
