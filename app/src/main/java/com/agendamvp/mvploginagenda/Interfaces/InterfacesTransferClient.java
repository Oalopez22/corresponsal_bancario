package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesTransferClient {
    interface view{
        void findElements();

    }
    interface presenter{
        Usuario data_cop(SharedPreferences sp);
        Usuario datosCliente(SharedPreferences sp);
        Usuario datosClienteATransferir(SharedPreferences sp);
        boolean transferencia(Usuario user);
    }
    interface model{
        Usuario data_cop(SharedPreferences sp);
        Usuario datosCliente(SharedPreferences sp);
        Usuario datosClienteATransferir(SharedPreferences sp);
        boolean transferencia(Usuario user);
    }
}
