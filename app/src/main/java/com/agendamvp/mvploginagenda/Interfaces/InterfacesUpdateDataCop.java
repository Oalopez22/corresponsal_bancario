package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesUpdateDataCop {
    interface view{
        void findElements();
    }
    interface presenter{
        Usuario datos_corresponsal(SharedPreferences sp);
        boolean actualizar_password(Usuario user);
    }
    interface model{
        Usuario datos_corresponsal(SharedPreferences sp);
        boolean actualizar_password(Usuario user);
    }
}
