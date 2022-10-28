package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Model.ModelSeachrCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterSeachCop implements InterfacesSeachCop.presenter {
    InterfacesSeachCop.view view;
    InterfacesSeachCop.model model;
    public PresenterSeachCop(InterfacesSeachCop.view view, Context contexto){
        this.view = view;
        this.model = new ModelSeachrCop(this,contexto);
    }


    @Override
    public boolean buscarcop(SharedPreferences sp) {
        boolean id ;
        id = this.model.buscarCorresponsal(sp);
        return id;
    }

    @Override
    public Usuario info(SharedPreferences sp) {
        Usuario user;
        user = this.model.info(sp);
        return user;
    }

    @Override
    public boolean actualizar_estado(Usuario user) {
        boolean id ;
        id = this.model.actualizar_estado(user);
        return id;
    }
}
