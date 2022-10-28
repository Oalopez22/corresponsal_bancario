package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSearchClient;
import com.agendamvp.mvploginagenda.Model.ModelSeachrCop;
import com.agendamvp.mvploginagenda.Model.ModelSearchClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterSeachClient implements InterfacesSearchClient.presenter {
    InterfacesSearchClient.view view;
    InterfacesSearchClient.model model;
    public PresenterSeachClient(InterfacesSearchClient.view view, Context contexto){
      this.view = view;
      this.model = new ModelSearchClient(this,contexto);
    }

    @Override
    public boolean buscar_cliente(SharedPreferences sp) {
        boolean id;
        id = model.buscar_cliente(sp);
        return id;
    }

    @Override
    public Usuario mostrardatos(SharedPreferences sp) {
        Usuario info = null;
        info = this.model.mostrardatos(sp);
        return info;
    }

}
