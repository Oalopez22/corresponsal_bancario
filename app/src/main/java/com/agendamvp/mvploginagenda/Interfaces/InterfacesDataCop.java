package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

public interface InterfacesDataCop {
    interface view{
        void findElements();
    }
    interface presenter{
        boolean update_status(SharedPreferences sp, int status);
        /*boolean actualizar_estado(SharedPreferences sp, int status);*/

    }
    interface model{
        boolean actualizar_estado( SharedPreferences sp,int status);

    }
}
