package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfaceRetiroClientCop;
import com.agendamvp.mvploginagenda.Model.ModelRetiroClientCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterRetiroClient implements InterfaceRetiroClientCop.presenter {
    InterfaceRetiroClientCop.view view;
    InterfaceRetiroClientCop.model model;
    public PresenterRetiroClient(InterfaceRetiroClientCop.view view, Context contexto){
        this.view = view;
        this.model = new ModelRetiroClientCop(this,contexto);
    }
    @Override
    public boolean retiro_cliente( Usuario user) {
        boolean id;
        id = this.model.retiro_cliente(user);
        return id;
    }

    @Override
    public Usuario datos_cliente(SharedPreferences sp) {
        Usuario data = null;
        data = this.model.datos_cliente(sp);
        return data;
    }
}
