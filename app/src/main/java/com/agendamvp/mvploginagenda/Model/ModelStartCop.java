package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesCopStart;
import com.agendamvp.mvploginagenda.Presenter.PresenterCopStart;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

import java.util.ArrayList;

public class ModelStartCop implements InterfacesCopStart.model {
    InterfacesCopStart.presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelStartCop(InterfacesCopStart.presenter presenter , Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }

    @Override
    public Usuario data(SharedPreferences sp) {
        Usuario user;
        user = db.infoCop(sp);
        return user;
    }
}
