package com.agendamvp.mvploginagenda.Model;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesNewClientCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Presenter.PresenterNewClientCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelNewClientCop implements InterfacesNewClientCop.Model {
    InterfacesNewClientCop.Presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelNewClientCop(InterfacesNewClientCop.Presenter presenter, Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public long registrar_Usuario(Usuario user) {
        long id=0;
        id = db.crearClienteCop(user);
        return id;
    }

    @Override
    public Usuario data(SharedPreferences sp) {
        Usuario user;
        user = db.infoCop(sp);
        return user;
    }
}
