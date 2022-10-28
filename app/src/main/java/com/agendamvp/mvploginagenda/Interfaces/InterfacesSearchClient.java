package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesSearchClient {
    interface view{
        void findElements();
    }
    interface  presenter{
        boolean buscar_cliente(SharedPreferences sp);
        Usuario mostrardatos(SharedPreferences sp);
    }
    interface model{
        boolean buscar_cliente(SharedPreferences sp);
        Usuario mostrardatos(SharedPreferences sp);
    }
}
