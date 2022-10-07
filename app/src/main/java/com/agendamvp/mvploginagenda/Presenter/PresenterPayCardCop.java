package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesPayCardCop;
import com.agendamvp.mvploginagenda.Model.ModelPayCop;

public class PresenterPayCardCop implements InterfacesPayCardCop.presenter {

    InterfacesPayCardCop.view view;
    InterfacesPayCardCop.model model;

    public PresenterPayCardCop(InterfacesPayCardCop.view view, Context contexto){
        this.view = view;
        this.model = new ModelPayCop(this,contexto);
    }

    @Override
    public boolean validar_datos_cliente(Usuario user) {
        boolean id = false;
        id = this.model.validar_datos_cliente(user);
        return id;
    }

    @Override
    public long Pago_tarjeta_cop(Usuario user) {

        long id = 0;
        id = this.model.Pago_tarjeta_cop(user);
        return id;
    }
}
