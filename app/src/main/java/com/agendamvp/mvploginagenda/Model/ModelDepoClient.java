package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDepoClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterDepoClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelDepoClient implements InterfacesDepoClient.model {
    InterfacesDepoClient.presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelDepoClient(InterfacesDepoClient.presenter presenter , Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }

    @Override
    public Usuario datosCliente(SharedPreferences sp) {
        Usuario data = null;
        data = db.datos_cliente_cop(sp);
        return data;
    }

    @Override
    public Usuario datosClienteADespositar(SharedPreferences sp) {
        Usuario datos = null;
        datos = db.datos_cliente_deposit_cop(sp);
        return datos;
    }

    @Override
    public boolean deposito(Usuario user) {
        boolean id;
        id = db.deposito(user);
        return id;
    }

    @Override
    public Usuario data_cop(SharedPreferences sp) {
        Usuario data = null;
        data = db.infoCop(sp);
        return data;
    }
}
