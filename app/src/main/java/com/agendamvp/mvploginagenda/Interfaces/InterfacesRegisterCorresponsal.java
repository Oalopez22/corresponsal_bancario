package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesRegisterCorresponsal {
    interface view{
        void findelements();
    }
    interface presenter {
        boolean validar_existencia(SharedPreferences sp);
        long registrar_corresponsal(Usuario user);
    }
    interface model{
        boolean validar_existencia(SharedPreferences sp);
        long registrar_corresponsal(Usuario user);
    }
}
