package com.agendamvp.mvploginagenda.Model;

import android.content.Context;
import android.os.IInterface;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesMainActivity;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesRegister;
import com.agendamvp.mvploginagenda.Presenter.PresenterMainActivity;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelMainActivity implements InterfacesMainActivity.Model {
    InterfacesMainActivity.Presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelMainActivity(InterfacesMainActivity.Presenter presenter, Context contexto) {
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }

    @Override
    public boolean Igresar(Usuario user) {
/*        boolean id = false;
        id = db.validar_Login(user);*/
        return false;
    }
}
