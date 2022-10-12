package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfaceRetiroClientCop {
    interface view{
        void findElements();
    }
    interface  presenter{
        boolean retiro_cliente(Usuario user);
        Usuario data_cop(SharedPreferences sp);
        Usuario datos_cliente(SharedPreferences sp);
    }
    interface model{
        boolean retiro_cliente(Usuario user);
        Usuario data_cop(SharedPreferences sp);
        Usuario datos_cliente(SharedPreferences sp);
    }
}
