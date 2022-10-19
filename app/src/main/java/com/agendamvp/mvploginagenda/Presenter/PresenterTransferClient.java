package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesTransferClient;
import com.agendamvp.mvploginagenda.Model.ModelTransferClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterTransferClient implements InterfacesTransferClient.presenter {

    InterfacesTransferClient.view view;
    InterfacesTransferClient.model model;
    public  PresenterTransferClient(InterfacesTransferClient.view view, Context contexto){
        this.view = view;
        this.model = new ModelTransferClient(this,contexto);
    }
    @Override
    public Usuario data_cop(SharedPreferences sp) {
        Usuario data = null;
        data = this.model.data_cop(sp);
        return data;
    }

    @Override
    public Usuario datosCliente(SharedPreferences sp) {
        Usuario data = null;
        data = this.model.datosCliente(sp);
        return data;
    }

    @Override
    public Usuario datosClienteATransferir(SharedPreferences sp) {
        Usuario datos = null;
        datos = this.model.datosClienteATransferir(sp);
        return datos;
    }

    @Override
    public boolean transferencia(Usuario user) {
        boolean id;
        id = this.model.transferencia(user);
        return id;
    }
}
