package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesBalanceClient;
import com.agendamvp.mvploginagenda.Presenter.PresenterBalanceClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelBalanceClient implements InterfacesBalanceClient.model {
    InterfacesBalanceClient.presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelBalanceClient(InterfacesBalanceClient.presenter presenter, Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }



    @Override
    public Usuario datosDevueltos(SharedPreferences sp) {
        Usuario data = null;
        data = db.consultar_saldo(sp);
        return data;
    }


}
