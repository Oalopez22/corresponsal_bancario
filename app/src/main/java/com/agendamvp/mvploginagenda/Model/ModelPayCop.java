package com.agendamvp.mvploginagenda.Model;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesPayCardCop;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;
import com.agendamvp.mvploginagenda.db.DbLogin;

public class ModelPayCop implements InterfacesPayCardCop.model {

    InterfacesPayCardCop.presenter presenter;
    Context contexto;
    DbLogin db;
    public ModelPayCop(InterfacesPayCardCop.presenter presenter,Context contexto){
        this.presenter = presenter;
        this.contexto = contexto;
        db = new DbLogin(contexto);
    }

    @Override
    public Usuario validar_datos_cliente(SharedPreferences sp) {
        Usuario data = null;
        data = db.validar_datos_cliente_cop(sp);
        return data;
    }

    @Override
    public Usuario data(SharedPreferences sp) {
        Usuario user;
        user = db.infoCop(sp);
        return user;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public long Pago_tarjeta_cop(Usuario user) {
        long id = 0;
        id = db.Pago_tarjeta_cop(user);
        return id;
    }
}
