package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesCopStart;
import com.agendamvp.mvploginagenda.Model.ModelStartCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

import java.util.ArrayList;

public class PresenterCopStart implements InterfacesCopStart.presenter {
    InterfacesCopStart.view view;
    InterfacesCopStart.model model;
    public PresenterCopStart(InterfacesCopStart.view view, Context contexto){
        this.view = view;
        this.model = new ModelStartCop(this,contexto);
    }

    @Override
    public Usuario data(SharedPreferences sp) {
        Usuario user;
        user = this.model.data(sp);
        return user;
    }
}
