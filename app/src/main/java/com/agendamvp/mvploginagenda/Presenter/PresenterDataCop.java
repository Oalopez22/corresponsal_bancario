package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDataCop;
import com.agendamvp.mvploginagenda.Model.ModelDataCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterDataCop implements InterfacesDataCop.presenter {
    InterfacesDataCop.view view;
    InterfacesDataCop.model model;
    public PresenterDataCop(InterfacesDataCop.view view, Context contexto){
        this.view = view;
        this.model = new ModelDataCop(this,contexto);
    }

    @Override
    public boolean update_status(Usuario user) {
        boolean id = false;
        id = this.model.actualizar_estado(user);
        return id;
    }

    @Override
    public Usuario info(SharedPreferences sp) {
        Usuario user;
        user = this.model.info(sp);
        return user;
    }


}
