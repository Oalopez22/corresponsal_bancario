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
    public static final String COLUMNA_ID_CLIENTE = "id_cliente";
    public static final String COLUMNA_DOCUMENTO = "documento_cliente";
    public static final String COLUMNA_NOMBRE = "nombre_cliente";
    public static final String COLUMNA_SALDO = "saldo_cliente";
    public static final String COLUMNA_PIN = "pin_cliente";
    public static final String COLUMNA_CARD = "card_numero";
    public static final String COLUMNA_CVV = "card_cvv";
    public static final String COLUMNA_EXPIRACION = "fecha_expiracion";

    /* TABLA CORRESPONSAL*/
    public static final String TABLE_CORRESPONSAL = "corresponsal";
    public static final String COLUMNA_ID_CORRESPONSAL = "id_corresponsal";
    public static final String COLUMNA_NOMBRE_CORRESPONSAL = "nombre_corresponsal";
    public static final String COLUMNA_NIT_CORRESPONSAL = "nit_corresponsal";
    public static final String COLUMNA_CORREO_CORRESPONSAL = "correo_corresponsal";
    public static final String COLUMNA_PASSWORD_CORRESPONSAL = "password_corresponsal";
    public static final String COLUMNA_ESTADO_CORRESPONSAL = "estado_corresponsal";
    public static final String COLUMNA_NCUENTA_CORRESPONSAL = "ncuenta_corresponsal";
    public static final String COLUMNA_SALDO_CORRESPONSAL = "saldo_corresponsal";
    public static final String COLUMNA_TIPO_CORRESPONSAL = "tipo_corresponsal";


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
        db.execSQL("CREATE TABLE " + TABLE_CLIENT + "(" + COLUMNA_ID_CLIENTE + "  integer primary key autoincrement ," + COLUMNA_DOCUMENTO + " text," + COLUMNA_NOMBRE + " text not null ," + COLUMNA_SALDO + " text not null," +
                COLUMNA_PIN + " integer not null ," + COLUMNA_CARD + " text not null," + COLUMNA_CVV + " text not null," + COLUMNA_EXPIRACION + " date not null " + ")");

        db.execSQL(" INSERT INTO " + TABLE_CLIENT + "(documento_cliente,nombre_cliente,saldo_cliente,pin_cliente,card_numero,card_cvv,fecha_expiracion) VALUES ('1232890497','Fabian',60000,9810,'61232890497229810','240','27-06')");
        db.execSQL(" INSERT INTO " + TABLE_CLIENT + "(documento_cliente,nombre_cliente,saldo_cliente,pin_cliente,card_numero,card_cvv,fecha_expiracion) VALUES ('1095944947','Andres',120000,1232,'61095944947221232','315','27-11')");

                    /* TABLA CORRESPONSAL */
        db.execSQL("CREATE TABLE " + TABLE_CORRESPONSAL + "(" + COLUMNA_ID_CORRESPONSAL + "  integer primary key autoincrement ," +COLUMNA_CORREO_CORRESPONSAL + " text," + COLUMNA_NOMBRE_CORRESPONSAL + " text not null ," + COLUMNA_NIT_CORRESPONSAL + " text not null ," + COLUMNA_PASSWORD_CORRESPONSAL + " text not null," + COLUMNA_ESTADO_CORRESPONSAL + " integer not null default 1," + COLUMNA_NCUENTA_CORRESPONSAL + " integer," + COLUMNA_SALDO_CORRESPONSAL + " integer not null default 0," + COLUMNA_TIPO_CORRESPONSAL + " integer not null default 1" + ")");


        db.execSQL(" INSERT INTO " + TABLE_CORRESPONSAL + "(correo_corresponsal,nombre_corresponsal,nit_corresponsal,password_corresponsal,estado_corresponsal,ncuenta_corresponsal,saldo_corresponsal,tipo_corresponsal) VALUES ('bga@wposs.com','BGACOP','1232890497','Alopez123',0,'24022388887912328',4000,1)");
        db.execSQL(" INSERT INTO " + TABLE_CORRESPONSAL + "(correo_corresponsal,nombre_corresponsal,nit_corresponsal,password_corresponsal,estado_corresponsal,ncuenta_corresponsal,saldo_corresponsal,tipo_corresponsal) VALUES ('admin@wposs.com','ADMINISTRADOR','000-000-000','Admin123*',0,'20022388887912320',0,0)");

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
