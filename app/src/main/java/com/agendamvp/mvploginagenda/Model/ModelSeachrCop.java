package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelSeachrCop implements InterfacesSeachCop.model {
    InterfacesSeachCop.presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelSeachrCop(InterfacesSeachCop.presenter presenter , Context contexto){
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }
    @Override
    public boolean buscarCorresponsal(SharedPreferences sp) {
        boolean id;
        id = db.buscarCorresponsal(sp);
        return id;
    }

    @Override
    public Usuario info(SharedPreferences sp) {
        Usuario user;
        user = db.infoCopAdmin(sp);
        return user;
    }

    @Override
    public boolean actualizar_estado(Usuario user) {
        boolean id;
        id = db.actualizarEstado(user);
        return id;
    }
}
