package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;

public interface InterfacesRegisterCorresponsal {
    interface view{
        void findelements();
    }
    interface presenter {
        long registrar_corresponsal(Usuario user);
    }
    interface model{
        long registrar_corresponsal(Usuario user);
    }
}
