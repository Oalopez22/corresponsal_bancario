package com.agendamvp.mvploginagenda.Interfaces;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesRegister {
    interface View{
        void findElements();
    }
    interface Presenter{
        boolean validar_existencia(SharedPreferences sp);
        long registrar_Usuario(Usuario user);

    }
    interface  Model{
        boolean validar_existencia(SharedPreferences sp);
        long Usuario(Usuario user);
    }
}
