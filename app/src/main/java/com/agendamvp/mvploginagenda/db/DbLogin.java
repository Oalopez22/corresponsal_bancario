package com.agendamvp.mvploginagenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.agendamvp.mvploginagenda.Entidades.Usuario;
import com.agendamvp.mvploginagenda.SharedPreferences.SharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    Calendar calendario = Calendar.getInstance();
    int year = calendario.get(Calendar.YEAR);
    int mes = calendario.get(Calendar.MONTH)+1;
    int dia = calendario.get(Calendar.DATE);
    int hora = calendario.get(Calendar.HOUR);
    int minutos = calendario.get(Calendar.MINUTE);
    String fechaCompleta = year +"-"+mes+"-"+dia+" "+hora+":"+minutos;
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
    public Usuario validar_datos_cliente_cop(SharedPreferences sp){
        Usuario datos = null;

        db =getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_CLIENT + " WHERE card_numero =? ",new String[]{sp.getCardClient()});
        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                datos = new Usuario();
                datos.setNombre(cursor.getString(2));
                datos.setSaldo(cursor.getInt(3));
                datos.setPin(cursor.getInt(4));
                datos.setCard_number(cursor.getString(5));
                datos.setCvv_client_number_cop(cursor.getString(6));
                datos.setFecha_expiracion_client_cop(cursor.getString(7));
            }
        }
        cursor.close();
        return datos;
    }



    public Usuario infoCop(SharedPreferences sp){
        Usuario user = null;
        Cursor cursor = null;
            cursor = db.rawQuery(" SELECT * FROM " + TABLE_CORRESPONSAL + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'", null);
            if (cursor.moveToFirst()){
                user = new Usuario();
                user.setCorresponsal_name(cursor.getString(2));
                user.setCorresponsal_balance(cursor.getInt(7));
                user.setCorreponsal_ncuenta(cursor.getString(6));
                user.setCorresponsal_email(cursor.getString(1));
                user.setCorresponsal_password(cursor.getString(4));
            }

        cursor.close();
        return user;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public long crearUsuario(Usuario user){
        int aleatorio = 0;
        String tarjeta = "";
        String cc = user.getDocumento();
        int pin = user.getPin();
        Random card = new Random();
        int Ntarjeta = card.nextInt(6-3+1)+3;
        int casoCc = cc.length();
        switch (casoCc){
            case 10:
                aleatorio = card.nextInt(9999 - 1000 + 1)+1000;
                tarjeta = Ntarjeta + cc + String.valueOf(aleatorio);
            break;
            case 8:
                aleatorio = card.nextInt(999 - 100 + 1)+100;
                tarjeta = Ntarjeta + cc + String.valueOf(aleatorio);
            break;
            case 11:
                aleatorio = card.nextInt(99 - 10 + 1)+10;
                tarjeta = Ntarjeta + cc + String.valueOf(aleatorio);
            break;
            case 12:
                aleatorio = card.nextInt(9 - 10 + 1)+1;
                tarjeta = Ntarjeta + cc + String.valueOf(aleatorio);
            break;
            default:
                Toast.makeText(context, "Numero de caracteres superado", Toast.LENGTH_LONG).show();
        }

        String numeros  =  tarjeta + pin;
        long id = 0;
        try {
            Random cardRandom = new Random();
            int numeroCvv = cardRandom.nextInt(999 - 100 + 1 )+100;
            LocalDate fecha = LocalDate.now();
            fecha = fecha.plusYears(5);
            String fechaConvertida = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            String dos_digitos = fechaConvertida.substring(2,4);
            String dos_ultimos = fechaConvertida.substring(5,7);
            String fecha_creacion = dos_digitos + "-" + dos_ultimos;
            db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("documento_cliente",user.getDocumento());
            values.put("nombre_cliente",user.getNombre());
            values.put("saldo_cliente",user.getSaldo());
            values.put("pin_cliente",pin);
            values.put("card_numero",numeros);
            values.put("card_cvv",numeroCvv);
            values.put("fecha_expiracion",fecha_creacion);
            id = db.insert(TABLE_CLIENT,null,values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public long crearCorresponsal(Usuario user){
        long id = 0;
        try {
            Random cardRandom = new Random();
            int numeroCard = cardRandom.nextInt( 999999999 - 100000000 + 1 )+100000000;
            String nit = user.getCorresponsal_nit();
            String inicial = "240";
            String extra = nit.substring(0,5);
            String Ncuenta = inicial + numeroCard + extra;
            db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            String corresponsal_mayus = user.getCorresponsal_name();
            corresponsal_mayus.toUpperCase(Locale.ROOT);
            values.put("correo_corresponsal",user.getCorresponsal_email());
            values.put("nombre_corresponsal",corresponsal_mayus);
            values.put("nit_corresponsal",user.getCorresponsal_nit());
            values.put("ncuenta_corresponsal",Ncuenta);
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
            data.setCorresponsal_email(cursor.getString(1));
            data.setCorresponsal_name(cursor.getString(2));
            data.setCorresponsal_nit(cursor.getString(3));
            data.setCorresponsal_status(cursor.getInt(5));
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
            data.setDocumento(cursor.getString(1));
            data.setNombre(cursor.getString(2));
            data.setSaldo(cursor.getInt(3));
            data.setCard_number(cursor.getString(5));
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
                data.setDocumento(cursorlistaclientes.getString(1));
                data.setNombre(cursorlistaclientes.getString(2));
                data.setSaldo(cursorlistaclientes.getInt(3));
                data.setPin(cursorlistaclientes.getInt(4));
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
                data.setCorresponsal_email(cursorlistacops.getString(1));
                data.setCorresponsal_name(cursorlistacops.getString(2));
                data.setCorresponsal_nit(cursorlistacops.getString(3));
                data.setCorresponsal_status(cursorlistacops.getInt(5));
                data.setCorresponsal_balance(cursorlistacops.getInt(7));
                listaCop.add(data);
            } while (cursorlistacops.moveToNext());
        }
        cursorlistacops.close();
        return listaCop;
    }


                                            /*  MODULO DE CORRESPONSAL */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long Pago_tarjeta_cop(Usuario user){
        int contador = 0;
        long id = 0;
        try {
            db = dbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            int valorCop = user.getCorresponsal_balance();
            contador = valorCop + user.getValor_pay_card_cop();
            String corresponsal_nombre = user.getNombre();

            String operacion = "Pago con tarjeta";
            corresponsal_nombre.toUpperCase();
            values.put("dato_relacion",sp.getCardClient());
            values.put("tipo_operacion",operacion.toUpperCase(Locale.ROOT));
            values.put("monto",user.getValor_pay_cuotes_cop());
            values.put("corresponsal_email",user.getCorresponsal_email());
            values.put("fecha_realizado",fechaCompleta);
            id = db.insert(TABLE_HISTORICO_COP,null,values);

            db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET saldo_corresponsal = " + contador + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'");
            mostrarDataCop(sp);

        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }


    /*          RETIRO POR PARTE DEL CLIENTE EN EL CORRESPONSAL */
    public Usuario datos_cliente_cop(SharedPreferences sp){
        Usuario datos = null;
        db =getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_CLIENT + " WHERE documento_cliente =? ",new String[]{sp.getCcUSer()});
        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                datos = new Usuario();
                datos.setNombre(cursor.getString(2));
                datos.setSaldo(cursor.getInt(3));
                datos.setPin(cursor.getInt(4));
                datos.setCard_number(cursor.getString(5));
            }
        }
        cursor.close();
        return datos;
    }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public  boolean retiro_cliente(Usuario user){
            int montoCop = 2000;
            int saldoCliente = user.getSaldo();
            int saldoTotal = saldoCliente - montoCop;
            int corresponsalBalance = user.getCorresponsal_balance();
            int nuevosaldocop = corresponsalBalance+montoCop;
            String tipo_op = "RETIRO";
            boolean correcto;
            try{
                ContentValues values = new ContentValues();
                db.execSQL(" UPDATE " + TABLE_CLIENT + " SET saldo_cliente =" + saldoTotal + " WHERE documento_cliente " +"= '"+sp.getCcUSer()+"'");
                values.put("dato_relacion",sp.getCcUSer());
                values.put("corresponsal_email", sp.getEmailCop());
                values.put("fecha_realizado",fechaCompleta);
                values.put("monto",user.getValor_pay_cuotes_cop());
                values.put("tipo_operacion",tipo_op);
                db.insert(TABLE_HISTORICO_COP,null,values);

                db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET saldo_corresponsal = " + nuevosaldocop + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'");
                correcto = true;
            }catch (Exception ex){
                ex.toString();
                correcto = false;
            }finally {
                db.close();
            }
            return correcto;
        }
                    /*  DEPOSITO CLIENTE*/
                    public Usuario datos_cliente_deposit_cop(SharedPreferences sp){
                        Usuario datos = null;
                        db =getWritableDatabase();
                        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_CLIENT + " WHERE documento_cliente =? ",new String[]{sp.getDeposit()});
                        if (cursor.getCount()>0){
                            if (cursor.moveToFirst()){
                                datos = new Usuario();
                                datos.setNombre_cliente_deposito(cursor.getString(2));
                                datos.setSaldo_cliente_deposito(cursor.getInt(3));
                                datos.setPin_cliente_deposito(cursor.getInt(4));
                            }
                        }
                        cursor.close();
                        return datos;
                    }


    public boolean deposito(Usuario user){
        int montoCop = 1000;
        int balanceCop = user.getCorresponsal_balance() + montoCop;
        String operacion = "Deposito";
        boolean correcto;
        int saldo = user.getSaldo();
        int valor = user.getSaldo_cliente_deposito();
        int monto = saldo + valor;
        try{
            db.execSQL( "UPDATE " + TABLE_CLIENT + " SET saldo_cliente = " + monto + " WHERE " + COLUMNA_DOCUMENTO + " ='" + sp.getDeposit() + "'");
            ContentValues values = new ContentValues();
            values.put("dato_relacion",sp.getCcUSer());
            values.put("corresponsal_email", sp.getEmailCop());
            values.put("fecha_realizado",fechaCompleta);
            values.put("monto",valor);
            values.put("tipo_operacion",operacion.toUpperCase());
            db.insert(TABLE_HISTORICO_COP,null,values);
            db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET saldo_corresponsal = " + balanceCop + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'");

            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }

    /* transferemcia */
    public boolean transferencia(Usuario user){
        int montoCop = 1000;
        int balanceCop = user.getCorresponsal_balance() + montoCop;
        String operacion = "Transferencia";
        boolean correcto;
        int saldoCliente = user.getSaldo();         //Saldo del cliente que transfiere
        int SclienteTransferido = user.getSaldo_cliente_deposito();         // saldo del cliente que recibe la transferencia
        int TransferValue = user.getValor_pay_card_cop();           //valor a transferir
        int transferencia = TransferValue + SclienteTransferido;        //nuevo valor del cliente que recibe la transferencia
        int descuentoCliete = saldoCliente - TransferValue - montoCop;         // saldo que se le descuenta al cliente que transfiere
        try{
            db.execSQL( "UPDATE " + TABLE_CLIENT + " SET saldo_cliente = " + descuentoCliete + " WHERE " + COLUMNA_DOCUMENTO + " ='" + sp.getCcUSer() + "'");           // descuento al cliente que transfiere

            db.execSQL( "UPDATE " + TABLE_CLIENT + " SET saldo_cliente = " + transferencia + " WHERE " + COLUMNA_DOCUMENTO + " ='" + sp.getDeposit() + "'");        // nuevo monto del cliente que recibe la transferencia
            ContentValues values = new ContentValues();
            values.put("dato_relacion",sp.getCcUSer());
            values.put("corresponsal_email", sp.getEmailCop());
            values.put("fecha_realizado",fechaCompleta);
            values.put("monto",TransferValue);
            values.put("tipo_operacion",operacion.toUpperCase());
            db.insert(TABLE_HISTORICO_COP,null,values);
            db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET saldo_corresponsal = " + balanceCop + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'");

            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
    public ArrayList<Usuario> historial(SharedPreferences sp){
        ArrayList<Usuario> historico = new ArrayList<>();
        Usuario user = null;
        Cursor cursorhistorial = null;
        cursorhistorial = db.rawQuery(" SELECT * FROM " + TABLE_HISTORICO_COP+ " WHERE " + COLUMNA_EMAIL_HISTORICO + " = '" + sp.getEmailCop() + "'",null);
        if (cursorhistorial.moveToFirst()){
            do{
                user = new Usuario();
                user.setCorresponsal_transaccion_fecha(cursorhistorial.getString(3));
                user.setCorresponsa_transaccion_type(cursorhistorial.getString(4));
                user.setCorresponsal_transaccion_value(cursorhistorial.getString(5));
                user.setCorresponsal_transaccion_ref(cursorhistorial.getString(1));
                user.setCorresponsal_transaccion_id(cursorhistorial.getString(0));
                historico.add(user);
            }while (cursorhistorial.moveToNext());
        }
        cursorhistorial.close();
        return historico;
    }

    public Usuario consultar_saldo(SharedPreferences sp){
        Usuario datos = null;
        /* Consultar datos del cliente */
        db =getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_CLIENT + " WHERE documento_cliente =? ",new String[]{sp.getCcUSer()});
            if (cursor.moveToFirst()){
                datos = new Usuario();
                datos.setNombre(cursor.getString(2));
                datos.setSaldo(cursor.getInt(3));
                datos.setDocumento(cursor.getString(1));
                datos.setPin(cursor.getInt(4));
                datos.setCard_number(cursor.getString(5));
            }
        cursor.close();
        return datos;
    }
    public boolean saldo(Usuario user){
        int montoCop = 1000;
        int balanceCop = user.getCorresponsal_balance() + montoCop;
        String operacion = "Consulta de saldo";
        boolean correcto;
        int saldo = user.getSaldo();
        int monto = saldo - montoCop;
        try{
            db.execSQL( "UPDATE " + TABLE_CLIENT + " SET saldo_cliente = " + monto + " WHERE " + COLUMNA_DOCUMENTO + " ='" + sp.getCcUSer() + "'");
            ContentValues values = new ContentValues();
            values.put("dato_relacion",sp.getCcUSer());
            values.put("corresponsal_email", sp.getEmailCop());
            values.put("fecha_realizado",fechaCompleta);
            values.put("monto",montoCop);
            values.put("tipo_operacion",operacion.toUpperCase());
            db.insert(TABLE_HISTORICO_COP,null,values);
            db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET saldo_corresponsal = " + balanceCop + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public long crearClienteCop(Usuario user){
        int aleatorio = 0;
        String tarjeta = "";
        String cc = user.getDocumento();
        int saldoCop = user.getCorresponsal_balance();
        int pin = user.getPin();
        int montoCop = 10000;
        String EmailCop = user.getCorresponsal_email();
        Random card = new Random();
        int Ntarjeta = card.nextInt(6-3+1)+3;
        int casoCc = cc.length();
        switch (casoCc){
            case 10:
                aleatorio = card.nextInt(9999 - 1000 + 1)+1000;
                tarjeta = Ntarjeta + cc + String.valueOf(aleatorio);
                break;
            case 8:
                aleatorio = card.nextInt(999 - 100 + 1)+100;
                tarjeta = Ntarjeta + cc + String.valueOf(aleatorio);
                break;
            case 11:
                aleatorio = card.nextInt(99 - 10 + 1)+10;
                tarjeta = Ntarjeta + cc + String.valueOf(aleatorio);
                break;
            case 12:
                aleatorio = card.nextInt(9 - 10 + 1)+1;
                tarjeta = Ntarjeta + cc + String.valueOf(aleatorio);
                break;
            default:
                Toast.makeText(context, "Numero de caracteres superado", Toast.LENGTH_LONG).show();
        }

        String numeros  =  tarjeta + pin;
        long id = 0;
        try {
            Random cardRandom = new Random();
            int numeroCvv = cardRandom.nextInt(999 - 100 + 1 )+100;
            LocalDate fecha = LocalDate.now();
            fecha = fecha.plusYears(5);
            String fechaConvertida = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            String dos_digitos = fechaConvertida.substring(2,4);
            String dos_ultimos = fechaConvertida.substring(5,7);
            String fecha_creacion = dos_digitos + "-" + dos_ultimos;
            db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("documento_cliente",cc);
            values.put("nombre_cliente",user.getNombre());
            values.put("saldo_cliente",user.getSaldo());
            values.put("pin_cliente",pin);
            values.put("card_numero",numeros);
            values.put("card_cvv",numeroCvv);
            values.put("fecha_expiracion",fecha_creacion);
            id = db.insert(TABLE_CLIENT,null,values);

            /* Actualizacion del nuevo monto del corresponsal */
            int valorcop = montoCop + saldoCop;
            db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET saldo_corresponsal = " + valorcop + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'");
            ContentValues valuesCliente = new ContentValues();
            /*INSERSION A LA TABLA HISTORICO */
            int monto = 0;
            String operacion = "Usuario nuevo";
            valuesCliente.put("dato_relacion",cc);
            valuesCliente.put("corresponsal_email", EmailCop);
            valuesCliente.put("fecha_realizado",fechaCompleta);
            valuesCliente.put("monto",monto);
            valuesCliente.put("tipo_operacion",operacion.toUpperCase());
            db.insert(TABLE_HISTORICO_COP,null,values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public boolean actualizar_password_cop(Usuario user){
        String pass = user.getCorresponsal_password();
        boolean correcto = false;
        try{
            //db.execSQL(" UPDATE " + TABLE_CORRESPONSAL + " SET password_corresponsal = " + pass + " WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'");
            db.execSQL("UPDATE " + TABLE_CORRESPONSAL + " SET password_corresponsal" + "='" + pass + "' WHERE " + COLUMNA_CORREO_CORRESPONSAL + " = '" + sp.getEmailCop() + "'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
}
