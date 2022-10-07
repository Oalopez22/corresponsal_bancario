package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesMainActivity;
import com.agendamvp.mvploginagenda.MainActivity;
import com.agendamvp.mvploginagenda.Model.ModelMainActivity;

public class PresenterMainActivity implements InterfacesMainActivity.Presenter{
    InterfacesMainActivity.View view;
    InterfacesMainActivity.Model model;
    public PresenterMainActivity(InterfacesMainActivity.View view, Context contexto) {
        this.view = view;
        this.model = new ModelMainActivity(this,contexto);
    }

    @Override
    public boolean Ingresar(Usuario user) {
        boolean id = false;
        id = this.model.Igresar(user);
        return id;
    }
}
