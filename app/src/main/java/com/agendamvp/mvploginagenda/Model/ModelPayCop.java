package com.agendamvp.mvploginagenda.Model;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.Interfaces.InterfacesPayCardCop;
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
    public boolean validar_datos_cliente(Usuario user) {
        boolean id = false;
/*        id = db.validar_datos_cliente_cop(user);*/
        return id;
    }

    @Override
    public long Pago_tarjeta_cop(Usuario user) {
        long id = 0;
        id = db.Pago_tarjeta_cop(user);
        return id;
    }
}
