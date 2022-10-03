package com.agendamvp.mvploginagenda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "loginmvp.db";
                    /* TABLA CLIENTE*/
    public static final String TABLE_CLIENT = "cliente";
    public static final String COLUMNA_DOCUMENTO = "documento_cliente";
    public static final String COLUMNA_NOMBRE = "nombre_cliente";
    public static final String COLUMNA_SALDO = "saldo_cliente";
    public static final String COLUMNA_PIN = "pin_cliente";
    public static final String COLUMNA_CARD = "card_numero";

                    /* TABLA CORRESPONSAL*/
    public static final String TABLE_CORRESPONSAL = "corresponsal";
    public static final String COLUMNA_NOMBRE_CORRESPONSAL = "nombre_corresponsal";
    public static final String COLUMNA_NIT_CORRESPONSAL = "nit_corresponsal";
    public static final String COLUMNA_CORREO_CORRESPONSAL = "correo_corresponsal";
    public static final String COLUMNA_PASSWORD_CORRESPONSAL = "password_corresponsal";
    public static final String COLUMNA_ESTADO_CORRESPONSAL = "estado_corresponsal";
    public static final String COLUMNA_NCUENTA_CORRESPONSAL = "ncuenta_corresponsal";
    public static final String COLUMNA_SALDO_CORRESPONSAL = "saldo_corresponsal";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CLIENT + "(" + COLUMNA_DOCUMENTO + " text primary key," + COLUMNA_NOMBRE + " text not null ," + COLUMNA_SALDO + " text not null," +
                COLUMNA_PIN + " integer not null ," + COLUMNA_CARD + " text not null "+")");



        db.execSQL(" INSERT INTO " + TABLE_CLIENT + "(documento_cliente,nombre_cliente,saldo_cliente,pin_cliente,card_numero)" + " VALUES ('9810','Andres','30000',9810,000)");



        db.execSQL("CREATE TABLE " + TABLE_CORRESPONSAL + "(" + COLUMNA_CORREO_CORRESPONSAL+ " text primary key ," +
                COLUMNA_NOMBRE_CORRESPONSAL + " text not null ," + COLUMNA_NIT_CORRESPONSAL + " text not null ," +
                COLUMNA_PASSWORD_CORRESPONSAL + " text not null," + COLUMNA_ESTADO_CORRESPONSAL + " integer not null default 0,"+ COLUMNA_NCUENTA_CORRESPONSAL + " integer," + COLUMNA_SALDO_CORRESPONSAL + " integer not null default 0" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE " + TABLE_CLIENT );
        db.execSQL(" DROP TABLE " + TABLE_CORRESPONSAL);
        onCreate(db);
    }
}
