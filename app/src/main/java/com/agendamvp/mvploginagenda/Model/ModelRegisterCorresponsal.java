package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegisterCorresponsal;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelRegisterCorresponsal implements InterfacesRegisterCorresponsal.model {
    InterfacesRegisterCorresponsal.presenter presenter;
    Context contexto;
    DbLogin db;

    public ModelRegisterCorresponsal(InterfacesRegisterCorresponsal.presenter presenter, Context contexto){
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }

    @Override
    public boolean validar_existencia(SharedPreferences sp) {
        boolean info = false;
        info = db.validarExistenciaCorresponsal(sp);
        return info;
    }

    @Override
    public long registrar_corresponsal(Usuario user) {
        long id = 0;
        id = db.crearCorresponsal(user);
        return id;
    }
}
