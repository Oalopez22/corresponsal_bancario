package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;

public interface InterfacesPayCardCop {
    interface view{
        void findElements();
    }
    interface presenter{
        long Pago_tarjeta_cop(Usuario user);
    }
    interface model{
        long Pago_tarjeta_cop(Usuario user);
    }

}
