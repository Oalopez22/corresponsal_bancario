package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegisterCorresponsal;
import com.agendamvp.mvploginagenda.Model.ModelRegisterCorresponsal;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterRegisterCorreponsal implements InterfacesRegisterCorresponsal.presenter {
    InterfacesRegisterCorresponsal.view view;
    InterfacesRegisterCorresponsal.model model;
    public PresenterRegisterCorreponsal(InterfacesRegisterCorresponsal.view view, Context contexto){
        this.view = view;
        this.model = new ModelRegisterCorresponsal(this,contexto);
    }

    @Override
    public boolean validar_existencia(SharedPreferences sp) {
        boolean info = false;
        info = this.model.validar_existencia(sp);
        return info;
    }

    @Override
    public long registrar_corresponsal(Usuario user) {
       long id = 0;
       id = this.model.registrar_corresponsal(user);
        return id;
    }
}
