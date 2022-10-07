package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

import java.util.ArrayList;

public interface InterfacesCopStart {
    interface view{
        void findElements();
    }
    interface presenter{
        public Usuario data(SharedPreferences sp);
    }
    interface model{
        public Usuario data(SharedPreferences sp);
    }
}
