package com.agendamvp.mvploginagenda.Interfaces;

import android.content.Context;

import com.agendamvp.mvploginagenda.Entidades.Usuario;

public interface InterfacesRegister {
    interface View{
        void findElements();
    }
    interface Presenter{
        long registrar_Usuario(Usuario user);
    }
    interface  Model{
        long Usuario(Usuario user);
    }
}
