package com.agendamvp.mvploginagenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class DbLogin extends DbHelper{
    DbHelper dbhelper;
    SQLiteDatabase db;
    SharedPreferences sp;
    Usuario data;
    Context context;
    public DbLogin(@Nullable Context context) {
        super(context);
        this.context = context;
        dbhelper = new DbHelper(context);
        db = dbhelper.getWritableDatabase();
        sp = new SharedPreferences(context);
    }

    public boolean validar_Login(Usuario user){
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " =?" + " AND " + COLUMNA_PASSWORD_CORRESPONSAL + " =?",new String[]{user.getCorresponsal_email(), user.getCorresponsal_password()});
        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
/*
    public Usuario validar_datos_cliente_cop(Usuario user){

        db =getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_CLIENT + " WHERE card_numero =? AND card_cvv =? AND fecha_expiracion =? ",new String[]{user.getCard_number(),user.getCvv_cliente(),String.valueOf(user.getFecha_expiracion())});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }*/

    public Usuario infoCop(SharedPreferences sp){
        Usuario user = null;
        Cursor cursor = null;
            cursor = db.rawQuery(" SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'", null);
            if (cursor.moveToFirst()){
                user = new Usuario();
                user.setCorresponsal_name(cursor.getString(1));
                user.setCorresponsal_balance(cursor.getInt(6));
            }

        cursor.close();
        return user;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public long crearUsuario(Usuario user){
        int cvv = (int) (Math.random()*100);
        long id = 0;
        try {
            LocalDate fecha = LocalDate.now();
            fecha = fecha.plusYears(5);
            db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("documento_cliente",user.getDocumento());
            values.put("nombre_cliente",user.getNombre());
            values.put("saldo_cliente",user.getSaldo());
            values.put("pin_cliente",user.getPin());
            values.put("card_numero",user.getCard_number());
            values.put("card_cvv",cvv);
            values.put("fecha_expiracion",String.valueOf(fecha));
            id = db.insert(TABLE_CLIENT,null,values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
    public long crearCorresponsal(Usuario user){
        long id = 0;
        try {
            db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            String corresponsal_mayus = user.getCorresponsal_name();
            corresponsal_mayus.toUpperCase(Locale.ROOT);
            values.put("correo_corresponsal",user.getCorresponsal_email());
            values.put("nombre_corresponsal",corresponsal_mayus);
            values.put("nit_corresponsal",user.getCorresponsal_nit());
            values.put("password_corresponsal",user.getCorresponsal_password());
            id = db.insert(TABLE_CORRESPONSAL,null,values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

/*    public boolean buscarCCcliente(Usuario user){
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CLIENT + " WHERE " + COLUMNA_DOCUMENTO + " = ?", new String[]{user.getDocumento()});
        if (cursor.getCount() > 0){
            return  true;
        }else{
            return false;
        }

    }*/
    public boolean buscarCorresponsal(Usuario user){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMNA_NIT_CORRESPONSAL + " = ?", new String[]{user.getCorresponsal_nit()} );
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<Usuario> mostrarDataCop(SharedPreferences sp){
        ArrayList<Usuario> dataUser = new ArrayList<>();
        data = null;
        Cursor cursor = null;
        cursor = db.rawQuery(" SELECT * FROM " + TABLE_CORRESPONSAL+ " WHERE " + COLUMNA_NIT_CORRESPONSAL + " = " + sp.getNitCop(),null);

        if (cursor.moveToNext()){
            data = new Usuario();
            data.setCorresponsal_email(cursor.getString(0));
            data.setCorresponsal_name(cursor.getString(1));
            data.setCorresponsal_nit(cursor.getString(2));
            data.setCorresponsal_status(cursor.getInt(4));
            dataUser.add(data);
        }
        cursor.close();
        return dataUser;
    }
    public  boolean actualizarEstado(SharedPreferences sp ,int status){
        boolean correcto;
        try{
            db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET estado_corresponsal =" + status + " WHERE nit_corresponsal " +"= '"+sp.getNitCop()+"'");
            mostrarDataClient(sp);
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }

    public boolean buscarCliente(SharedPreferences sp){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CLIENT + " WHERE " + COLUMNA_DOCUMENTO + " = ?", new String[]{sp.getCcUSer()} );
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public Usuario mostrarDataClient(SharedPreferences sp){
        data = new Usuario();
        Cursor cursor = null;
        cursor = db.rawQuery(" SELECT * FROM " + TABLE_CLIENT+ " WHERE " + COLUMNA_DOCUMENTO + " = " + sp.getCcUSer(),null);
        if (cursor.moveToNext()){
            data.setDocumento(cursor.getString(0));
            data.setNombre(cursor.getString(1));
            data.setSaldo(cursor.getInt(2));
            data.setCard_number(cursor.getString(4));
        }
        cursor.close();
        return data;
    }



    public ArrayList<Usuario> listadoClientes(){
        ArrayList<Usuario> listaClientes = new ArrayList<>();
        data = null;
        Cursor cursorlistaclientes = null;
        cursorlistaclientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENT ,null);
        if (cursorlistaclientes.moveToFirst()) {
            do {
                data = new Usuario();
                data.setDocumento(cursorlistaclientes.getString(0));
                data.setNombre(cursorlistaclientes.getString(1));
                data.setSaldo(cursorlistaclientes.getInt(2));
                data.setPin(cursorlistaclientes.getInt(3));
                listaClientes.add(data);
            } while (cursorlistaclientes.moveToNext());
            }
            cursorlistaclientes.close();
            return listaClientes;
            }
    public ArrayList<Usuario> listadoCorresponsales(){
        ArrayList<Usuario> listaCop = new ArrayList<>();
        data = null;
        Cursor cursorlistacops = null;
        cursorlistacops = db.rawQuery("SELECT * FROM " + TABLE_CORRESPONSAL ,null);
        if (cursorlistacops.moveToFirst()) {
            do {
                data = new Usuario();
                data.setCorresponsal_email(cursorlistacops.getString(0));
                data.setCorresponsal_name(cursorlistacops.getString(1));
                data.setCorresponsal_nit(cursorlistacops.getString(2));
                data.setCorresponsal_status(cursorlistacops.getInt(4));
                data.setCorresponsal_balance(cursorlistacops.getInt(6));
                listaCop.add(data);
            } while (cursorlistacops.moveToNext());
        }
        cursorlistacops.close();
        return listaCop;
    }


                                            /*  MODULO DE CORRESPONSAL */

    public long Pago_tarjeta_cop(Usuario user){

        long id = 0;
        try {
            db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            String corresponsal_nombre = user.getNombre();
            corresponsal_nombre.toUpperCase();
            values.put("numero_tarjeta",user.getCard_number_pay_cop());
            //values.put("fecha_expiracion_card",user.getFecha_expiracion_client_cop());
            values.put("nombre_cliente_cop",user.getNombre());
            values.put("valor_pagado",user.getValor_pay_card_cop());
            values.put("valor_cuotas",user.getValor_pay_cuotes_cop());
            id = db.insert(TABLE_PAY_CARD_COP,null,values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
