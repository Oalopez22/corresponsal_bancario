package com.agendamvp.mvploginagenda.Interfaces;

import com.agendamvp.mvploginagenda.Entidades.Usuario;

public interface InterfacesMainActivity {
    interface  View{
        void findElement();
    }
    interface Presenter{
        Usuario Ingresar(Usuario user);
    }
    interface Model{
        Usuario Igresar(Usuario user);
    }
}

