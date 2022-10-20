package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesBalanceClient {
    interface view{
        void FindElements();
        Usuario DatosDevueltos(SharedPreferences sp);
    }
    interface presenter{
        Usuario datosDevueltos(SharedPreferences sp);
    }
    interface model{

        Usuario datosDevueltos(SharedPreferences sp);

    }
}
