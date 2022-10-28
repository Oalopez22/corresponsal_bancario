package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDataCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterDataCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.util.ArrayList;

public class ModelDataCop implements InterfacesDataCop.model {
    InterfacesDataCop.presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelDataCop(InterfacesDataCop.presenter presenter, Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }


    @Override
    public boolean actualizar_estado(Usuario user) {
        boolean id;
        id = db.actualizarEstado(user);
        return id;
    }

    @Override
    public Usuario info(SharedPreferences sp) {
        Usuario user = null;
        return user;
    }

}
