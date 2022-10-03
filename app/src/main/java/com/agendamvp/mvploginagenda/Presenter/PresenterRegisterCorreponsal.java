package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegisterCorresponsal;
import com.agendamvp.mvploginagenda.Model.ModelRegisterCorresponsal;

public class PresenterRegisterCorreponsal implements InterfacesRegisterCorresponsal.presenter {
    InterfacesRegisterCorresponsal.view view;
    InterfacesRegisterCorresponsal.model model;
    public PresenterRegisterCorreponsal(InterfacesRegisterCorresponsal.view view, Context contexto){
        this.view = view;
        this.model = new ModelRegisterCorresponsal(this,contexto);
    }
    @Override
    public long registrar_corresponsal(Usuario user) {
       long id = 0;
       id = this.model.registrar_corresponsal(user);
        return id;
    }
}
