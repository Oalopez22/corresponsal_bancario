package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesUpdateDataCop;
import com.agendamvp.mvploginagenda.Model.ModelUpdateDataCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterUpdateDataCop implements InterfacesUpdateDataCop.presenter {
    InterfacesUpdateDataCop.view view;
    InterfacesUpdateDataCop.model model;

    public PresenterUpdateDataCop(InterfacesUpdateDataCop.view view , Context contexto){
        this.view = view;
        this.model = new ModelUpdateDataCop(this,contexto);
    }
    @Override
    public Usuario datos_corresponsal(SharedPreferences sp) {
        Usuario data = null;
        data = this.model.datos_corresponsal(sp);
        return data;
    }

    @Override
    public boolean actualizar_password(Usuario user) {
        boolean update;
        update = this.model.actualizar_password(user);
        return update;
    }


}
