package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesDataCop {
    interface view{
        void findElements();
    }
    interface presenter{
     public boolean actualizar_estado(Usuario user, SharedPreferences sp);

    }
    interface model{
        public boolean actualizar_estado(Usuario user, SharedPreferences sp);

    }
}
