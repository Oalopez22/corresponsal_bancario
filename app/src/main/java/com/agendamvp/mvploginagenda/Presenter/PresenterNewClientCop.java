package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesNewClientCop;
import com.agendamvp.mvploginagenda.Model.ModelNewClientCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterNewClientCop implements InterfacesNewClientCop.Presenter {
    InterfacesNewClientCop.View view;
    InterfacesNewClientCop.Model model;
    public PresenterNewClientCop(InterfacesNewClientCop.View view, Context contexto){
        this.view = view;
        this.model = new ModelNewClientCop(this,contexto);
    }
    @Override
    public long registrar_Usuario(Usuario user) {
        long id= 0;
        id = this.model.registrar_Usuario(user);
        return id;
    }

    @Override
    public Usuario data(SharedPreferences sp) {
        Usuario user;
        user = this.model.data(sp);
        return user;
    }
}
