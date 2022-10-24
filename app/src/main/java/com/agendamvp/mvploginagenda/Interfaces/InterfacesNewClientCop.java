package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesNewClientCop {
    interface View{
        void findElements();
    }
    interface Presenter{
        long registrar_Usuario(Usuario user);
         Usuario data(SharedPreferences sp);
    }
    interface  Model{
        long registrar_Usuario(Usuario user);
         Usuario data(SharedPreferences sp);
    }
}
