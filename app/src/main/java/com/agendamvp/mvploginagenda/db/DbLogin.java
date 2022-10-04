package com.agendamvp.mvploginagenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

import java.util.ArrayList;
import java.util.Locale;

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

 /*   public boolean validar_Login(Usuario user){
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_USER + " WHERE " + COLUMNA_EMAIL + " =?" + " AND " + COLUMNA_PASSWORD + " =?",new String[]{user.getCorreo(), user.getPassword()});
        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
*/


    public long crearUsuario(Usuario user){
        long id = 0;
        try {
            db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("documento_cliente",user.getDocumento());
            values.put("nombre_cliente",user.getNombre());
            values.put("saldo_cliente",user.getSaldo());
            values.put("pin_cliente",user.getPin());
            values.put("card_numero",user.getCard_number());
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
    public  boolean actualizarEstado(Usuario user, SharedPreferences sp){
        boolean correcto = true;
        try{
            db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET estado_corresponsal" + " =" + user.getCorresponsal_status() + "' WHERE id_libro='" +sp.getNitCop() + "' ");
        correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }
        finally {
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
        data = new Usuario();Cursor cursor = null;
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



}
