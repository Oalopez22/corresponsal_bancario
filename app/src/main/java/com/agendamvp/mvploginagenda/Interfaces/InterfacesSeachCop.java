package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesSeachCop {
    interface view{
        void findElements();
    }
    interface presenter{
        boolean buscarcop(SharedPreferences sp);
        Usuario info(SharedPreferences sp);
    }
    interface  model{
        boolean buscarCorresponsal(SharedPreferences sp);
        Usuario info(SharedPreferences sp);
    }

}
