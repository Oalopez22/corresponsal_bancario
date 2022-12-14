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

    public void setNitCopAdmin(String nitcopAdmin){
        editor.putString("nitAdmin",nitcopAdmin);
        editor.apply();
    }

    public String getNitCopAdmin(){return datos.getString("nitAdmin", "Dato no encontrado");}
    public void setCcUser(String cc){
        editor.putString("cc",cc);
        editor.apply();
    }
    public String getCcUSer(){
        return datos.getString("cc","Dato no encontrado");
    }

    public void setCcDeposit(String deposit){
        editor.putString("deposit",deposit);
        editor.apply();
    }
    public String getDeposit(){
        return datos.getString("deposit","Dato no encontrado");
    }


    public void setCardClient(String card){
        editor.putString("Card",card);
        editor.apply();
    }
    public String getCardClient(){
        return datos.getString("Card","Dato no encontrado");
    }


    public void setEmailCop(String emailcop){
        editor.putString("email",emailcop);
        editor.apply();
    }
    public String getEmailCop(){
        return datos.getString("email","Dato no encontrado");
    }

}
