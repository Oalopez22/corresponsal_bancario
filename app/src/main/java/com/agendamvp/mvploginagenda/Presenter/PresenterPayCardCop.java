package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesPayCardCop;
import com.agendamvp.mvploginagenda.Model.ModelPayCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterPayCardCop implements InterfacesPayCardCop.presenter {

    InterfacesPayCardCop.view view;
    InterfacesPayCardCop.model model;

    public PresenterPayCardCop(InterfacesPayCardCop.view view, Context contexto){
        this.view = view;
        this.model = new ModelPayCop(this,contexto);
    }

    @Override
    public Usuario validar_datos_cliente(SharedPreferences sp) {
        Usuario data = null;
        data = this.model.validar_datos_cliente(sp);
        return data;
    }

    @Override
    public Usuario data(SharedPreferences sp) {
        Usuario user;
        user = this.model.data(sp);
        return user;
    }

    @Override
    public long Pago_tarjeta_cop(Usuario user) {
        long id = 0;
        id = this.model.Pago_tarjeta_cop(user);
        return id;
    }
}
