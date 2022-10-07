package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;

public interface InterfacesPayCardCop {
    interface view{
        void findElements();
    }
    interface presenter{
        boolean validar_datos_cliente(Usuario user);

        long Pago_tarjeta_cop(Usuario user);
    }
    interface model{
        boolean validar_datos_cliente(Usuario user);

        long Pago_tarjeta_cop(Usuario user);

    }

}
