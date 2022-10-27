package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Model.ModelRegister;
import com.agendamvp.mvploginagenda.Register;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterRegister implements InterfacesRegister.Presenter {
    InterfacesRegister.View view;
    InterfacesRegister.Model model;
    public PresenterRegister(InterfacesRegister.View view, Context contexto) {
        this.view = view;
        this.model = new ModelRegister(this,contexto);
    }


    @Override
    public boolean validar_existencia(SharedPreferences sp) {
        boolean info = false;
        info = this.model.validar_existencia(sp);
        return info;
    }

    @Override
    public long registrar_Usuario(Usuario user) {
        long id = 0;
           id = this.model.Usuario(user);
            return id;
    }


}
