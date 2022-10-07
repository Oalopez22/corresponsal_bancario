package com.agendamvp.mvploginagenda.SharedPreferences;

import android.content.Context;

public class SharedPreferences {
    Context context;
    android.content.SharedPreferences datos;
    android.content.SharedPreferences.Editor editor;
    public SharedPreferences(Context contexto){
        this.context = contexto;
        datos = contexto.getSharedPreferences("all_sp",Context.MODE_PRIVATE);
        editor = datos.edit();
    }

    public void setNitCop(String nitcop){
        editor.putString("nit",nitcop);
        editor.apply();
    }
    public String getNitCop(){
        return datos.getString("nit","Dato no encontrado");
    }

    public void setCcUser(String cc){
        editor.putString("cc",cc);
        editor.apply();
    }
    public String getCcUSer(){
        return datos.getString("cc","Dato no encontrado");
    }


    public void setEmailCop(String emailcop){
        editor.putString("email",emailcop);
        editor.apply();
    }
    public String getEmailCop(){
        return datos.getString("email","Dato no encontrado");
    }

}
