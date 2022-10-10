package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesPayCardCop {
    interface view{
        void findElements();
    }
    interface presenter{
        Usuario validar_datos_cliente(SharedPreferences sp);
        Usuario data (SharedPreferences sp);
        long Pago_tarjeta_cop(Usuario user);
    }
    interface model{
        Usuario validar_datos_cliente(SharedPreferences sp);
        Usuario data (SharedPreferences sp);
        long Pago_tarjeta_cop(Usuario user);

    }

}
