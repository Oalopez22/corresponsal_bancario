package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesDepoClient {
    interface view{
        void findElements();

    }
    interface presenter{
        Usuario datosCliente(SharedPreferences sp);
        Usuario datosClienteADespositar(SharedPreferences sp);
        boolean deposito(Usuario user);
        Usuario data_cop(SharedPreferences sp);
    }
    interface model{
        Usuario datosCliente(SharedPreferences sp);
        Usuario datosClienteADespositar(SharedPreferences sp);
        boolean deposito(Usuario user);
        Usuario data_cop(SharedPreferences sp);
    }
}
