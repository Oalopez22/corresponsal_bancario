package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;

public interface InterfacesSeachCop {
    interface view{
        void findElements();
    }
    interface presenter{
        boolean buscarCorresponsal(Usuario user);
    }
    interface  model{
        boolean buscarCorresponsal(Usuario user);
    }

}
