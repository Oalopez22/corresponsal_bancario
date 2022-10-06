package com.agendamvp.mvploginagenda.Presenter;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesDataCop;
import com.agendamvp.mvploginagenda.Model.ModelDataCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public class PresenterDataCop implements InterfacesDataCop.presenter {
    InterfacesDataCop.view view;
    InterfacesDataCop.model model;
    public PresenterDataCop(InterfacesDataCop.view view, Context contexto){
        this.view = view;
        this.model = new ModelDataCop(this,contexto);
    }

    @Override
    public boolean update_status(SharedPreferences sp, int status) {
        boolean id = false;
        id = this.model.actualizar_estado(sp,status);
        return id;
    }


/*    @Override
    public boolean actualizar_estado(SharedPreferences sp,int status) {
        boolean id;
        id = this.model.actualizar_estado(sp, status);
        return id;
    }*/
}
