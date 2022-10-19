package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDepoClient;
import com.agendamvp.mvploginagenda.Model.ModelDepoClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterDepoClient implements InterfacesDepoClient.presenter {
    InterfacesDepoClient.view view;
    InterfacesDepoClient.model model;
    public  PresenterDepoClient(InterfacesDepoClient.view view, Context contexto){
        this.view = view;
        this.model = new ModelDepoClient(this,contexto);
    }
    @Override
    public Usuario datosCliente(SharedPreferences sp) {
        Usuario data = null;
        data = this.model.datosCliente(sp);
        return data;
    }

    @Override
    public Usuario datosClienteADespositar(SharedPreferences sp) {
        Usuario datos = null;
        datos = this.model.datosClienteADespositar(sp);
        return datos;
    }

    @Override
    public boolean deposito(Usuario user) {
        boolean id;
        id = this.model.deposito(user);
        return id;
    }

    @Override
    public Usuario data_cop(SharedPreferences sp) {
        Usuario data = null;
        data = this.model.data_cop(sp);
        return data;
    }
}
