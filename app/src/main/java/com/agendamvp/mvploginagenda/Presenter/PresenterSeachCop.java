package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Model.ModelSeachrCop;

public class PresenterSeachCop implements InterfacesSeachCop.presenter {
    InterfacesSeachCop.view view;
    InterfacesSeachCop.model model;
    public PresenterSeachCop(InterfacesSeachCop.view view, Context contexto){
        this.view = view;
        this.model = new ModelSeachrCop(this,contexto);
    }


    @Override
    public boolean buscarCorresponsal(Usuario user) {
        boolean id;
        id = this.model.buscarCorresponsal(user);
        return id;
    }
}
