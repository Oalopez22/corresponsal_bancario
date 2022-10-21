package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesBalanceClient {
    interface view{
        void FindElements();
    }
    interface presenter{
        Usuario datosCorresponsal(SharedPreferences sp);
        Usuario datosDevueltos(SharedPreferences sp);
        boolean consultarsaldo(Usuario user);
    }
    interface model{
        Usuario datosCorresponsal(SharedPreferences sp);
        Usuario datosDevueltos(SharedPreferences sp);
        boolean consultarsaldo(Usuario user);
    }
}
