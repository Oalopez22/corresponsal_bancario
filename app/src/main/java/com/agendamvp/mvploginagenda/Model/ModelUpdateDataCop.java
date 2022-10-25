package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesUpdateDataCop;
import com.agendamvp.mvploginagenda.Presenter.PresenterUpdateDataCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelUpdateDataCop implements InterfacesUpdateDataCop.model {
    InterfacesUpdateDataCop.presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelUpdateDataCop(InterfacesUpdateDataCop.presenter presenter, Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }

    @Override
    public Usuario datos_corresponsal(SharedPreferences sp) {
        Usuario data = null;
        data = db.infoCop(sp);
        return data;
    }

    @Override
    public boolean actualizar_password(Usuario user) {
        return db.actualizar_password_cop(user);
    }


}
