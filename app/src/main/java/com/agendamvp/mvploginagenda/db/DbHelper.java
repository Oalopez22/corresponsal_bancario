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
    public static final String COLUMNA_CVV = "card_cvv";
    public static final String COLUMNA_EXPIRACION = "fecha_expiracion";

    /* TABLA CORRESPONSAL*/
    public static final String TABLE_CORRESPONSAL = "corresponsal";
    public static final String COLUMNA_NOMBRE_CORRESPONSAL = "nombre_corresponsal";
    public static final String COLUMNA_NIT_CORRESPONSAL = "nit_corresponsal";
    public static final String COLUMNA_CORREO_CORRESPONSAL = "correo_corresponsal";
    public static final String COLUMNA_PASSWORD_CORRESPONSAL = "password_corresponsal";
    public static final String COLUMNA_ESTADO_CORRESPONSAL = "estado_corresponsal";
    public static final String COLUMNA_NCUENTA_CORRESPONSAL = "ncuenta_corresponsal";
    public static final String COLUMNA_SALDO_CORRESPONSAL = "saldo_corresponsal";
    /* TABLA PAGO TARJETA CORRESPONSAL*/
/*
    public static final String TABLE_PAY_CARD_COP = "pago_tarjeta_cop";
    public static final String COLUMNA_ID_PAGO = "id_pago_card";
    public static final String COLUMNA_N_TARJETA_COP = "numero_tarjeta";
    public static final String COLUMNA_FECHA_EXPIRA_COP = "fecha_expiracion_card";
    public static final String COLUMNA_CVV_CARD_COP = "cvv_cliente";
    public static final String COLUMNA_NOMBRE_CLIENTE = "nombre_cliente_cop";
    public static final String COLUMNA_VALOR_PAGO_CARD = "valor_pagado";
    public static final String COLUMNA_VALOR_CUOTAS = "valor_cuotas";
    public static final String COLUMNA_FECHA_PAGO = "fecha_pago_tarjeta";
    public static final String COLUMNA_CANTIDAD_CUOTAS = "cantidad_cuotas";
    public static final String COLUMNA_CORRESPONSAL_DATA = "corresponsal_email";
    public static final String COLUMNA_TIPO_TRANSACCION = "tipo_transaccion";


    */
/* TABLA RETIROS *//*

    public static final String TABLE_RET_CLIENT = "retiro_cliente";
    public static final String COLUMNA_ID_RETIRO = "id_retiro";
    public static final String COLUMNA_DOCUMENTO_RETIRO = "documento_retiro";
    public static final String COLUMNA_MONTO_RETIRO = "monto_retirado";
*/

                    /* TABLA HISTORICO */
    public static final String TABLE_HISTORICO_COP = "historico";
    public static final String COLUMNA_ID_HISTORICO = "id_historico";
    public static final String COLUMNA_DATA_HISTORICO = "dato_relacion";
    public static final String COLUMNA_EMAIL_HISTORICO = "corresponsal_email";
    public static final String COLUMNA_FECHA_HISTORICO = "fecha_realizado";
    public static final String COLUMNA_TIPO_HISTORICO  = "tipo_operacion";
    public static final String COLUMNA_MONTO_HISTORICO = "monto";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CLIENT + "(" + COLUMNA_DOCUMENTO + " text primary key," + COLUMNA_NOMBRE + " text not null ," + COLUMNA_SALDO + " text not null," +
                COLUMNA_PIN + " integer not null ," + COLUMNA_CARD + " text not null," + COLUMNA_CVV + " text not null," + COLUMNA_EXPIRACION + " date not null " + ")");

        db.execSQL(" INSERT INTO " + TABLE_CLIENT + "(documento_cliente,nombre_cliente,saldo_cliente,pin_cliente,card_numero,card_cvv,fecha_expiracion) VALUES ('1232890497','Fabian',60000,9810,'61232890497229810','240','10-06')");

                    /* TABLA CORRESPONSAL */
        db.execSQL("CREATE TABLE " + TABLE_CORRESPONSAL + "(" + COLUMNA_CORREO_CORRESPONSAL + " text primary key ," +
                COLUMNA_NOMBRE_CORRESPONSAL + " text not null ," + COLUMNA_NIT_CORRESPONSAL + " text not null ," +
                COLUMNA_PASSWORD_CORRESPONSAL + " text not null," + COLUMNA_ESTADO_CORRESPONSAL + " integer not null default 0," + COLUMNA_NCUENTA_CORRESPONSAL + " integer," + COLUMNA_SALDO_CORRESPONSAL + " integer not null default 0" + ")");


                    /* TABLA PAGO CON TARJETA */
/*
        db.execSQL(" CREATE TABLE " + TABLE_PAY_CARD_COP + " (" + COLUMNA_ID_PAGO + " integer primary key autoincrement ," + COLUMNA_N_TARJETA_COP + " text not null  ," + COLUMNA_FECHA_EXPIRA_COP + " date ," + COLUMNA_CVV_CARD_COP + " text not null," + COLUMNA_NOMBRE_CLIENTE + " text not null," + COLUMNA_VALOR_PAGO_CARD + " integer not null," + COLUMNA_VALOR_CUOTAS + " integer not null," + COLUMNA_CANTIDAD_CUOTAS + " int not null," + COLUMNA_FECHA_PAGO + " date not null" + ")");

*/

        /*  TABLA RETIRO */

        db.execSQL(" CREATE TABLE " + TABLE_HISTORICO_COP + " (" + COLUMNA_ID_HISTORICO + " integer primary key autoincrement ," + COLUMNA_DATA_HISTORICO + " text not null ," + COLUMNA_EMAIL_HISTORICO + " text not null, " + COLUMNA_FECHA_HISTORICO + " date not null ," + COLUMNA_TIPO_HISTORICO + " text not null ," + COLUMNA_MONTO_HISTORICO + " integer not null" + " )" );
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE " + TABLE_CLIENT);
        db.execSQL(" DROP TABLE " + TABLE_CORRESPONSAL);
        db.execSQL(" DROP TABLE " + TABLE_HISTORICO_COP);
        onCreate(db);
    }
}
