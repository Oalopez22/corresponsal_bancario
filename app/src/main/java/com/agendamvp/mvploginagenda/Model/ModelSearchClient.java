package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSeachCop;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesSearchClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterSeachClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelSearchClient implements InterfacesSearchClient.model {
    InterfacesSearchClient.presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelSearchClient(InterfacesSearchClient.presenter presenter, Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }


    @Override
    public boolean buscar_cliente(SharedPreferences sp) {
        boolean id;
        id = db.buscarCliente(sp);
        return id;
    }
}

