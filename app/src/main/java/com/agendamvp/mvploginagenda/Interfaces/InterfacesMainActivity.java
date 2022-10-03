package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;

public interface InterfacesMainActivity {
    interface  View{
        void findElement();
    }
    interface Presenter{
        boolean ingresar(Usuario user);
    }
    interface Model{
        boolean Igresar(Usuario user);
    }
}

