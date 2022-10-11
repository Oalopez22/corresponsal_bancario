package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfaceRetiroClientCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterRetiroClient;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelRetiroClientCop implements InterfaceRetiroClientCop.model {

    InterfaceRetiroClientCop.presenter presenter;
    Context contexto;
    DbLogin db;

    public ModelRetiroClientCop(InterfaceRetiroClientCop.presenter presenter, Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }


    @Override
    public boolean retiro_cliente(Usuario user) {
        boolean id;
        id = db.retiro_cliente(user);
        return id;
    }

    @Override
    public Usuario datos_cliente(SharedPreferences sp) {
            Usuario data = null;
            data = db.validar_datos_cliente_cop(sp);
            return data;
    }
}
