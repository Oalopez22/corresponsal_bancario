package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesBalanceClient;
import com.agendamvp.mvploginagenda.Model.ModelBalanceClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterBalanceClient implements InterfacesBalanceClient.presenter {
    InterfacesBalanceClient.view view;
    InterfacesBalanceClient.model model;

    public PresenterBalanceClient(InterfacesBalanceClient.view view, Context contexto){
        this.view = view;
        this.model = new ModelBalanceClient(this,contexto);
    }



    @Override
    public Usuario datosDevueltos(SharedPreferences sp) {
        Usuario data = null;
        data = this.model.datosDevueltos(sp);
        return data;
    }


}
