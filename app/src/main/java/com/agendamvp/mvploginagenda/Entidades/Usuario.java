package com.agendamvp.mvploginagenda.Entidades;

import java.time.LocalDate;
import java.util.Date;

public class Usuario {
    /* CLIENTE */
    private String nombre;
    private String documento;
    private int saldo;
    private int pin;
    private String card_number;
    private LocalDate fecha_expiracion;
    private String cvv_cliente;
    /* Deposito cliente */
    private String nombre_cliente_deposito;
    private String documento_cliente_deposito;
    private int saldo_cliente_deposito;
    private int pin_cliente_deposito;
    /* CORRESPONSAL*/
    private String corresponsal_name;
    private String corresponsal_nit;
    private String corresponsal_email;
    private String corresponsal_password;
    private int corresponsal_status;
    private int corresponsal_balance;
    private int corresponsal_rol;
    private String correponsal_ncuenta;
    private String corresponsal_transaccion_fecha;
    private String corresponsa_transaccion_type;
    private String corresponsal_transaccion_value;
    private String corresponsal_transaccion_ref;
    private String corresponsal_transaccion_id;
    private String fecha_expiracion_client_cop;
    private int valor_pay_card_cop;
    private int valor_pay_cuotes_cop;
    private String card_number_pay_cop;
    private String cvv_client_number_cop;
    private int cantidad_cuotas;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getCorresponsal_nit() {
        return corresponsal_nit;
    }

    public void setCorresponsal_nit(String corresponsal_nit) {
        this.corresponsal_nit = corresponsal_nit;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getCorresponsal_name() {
        return corresponsal_name;
    }

    public void setCorresponsal_name(String corresponsal_name) {
        this.corresponsal_name = corresponsal_name;
    }


    public String getCorresponsal_email() {
        return corresponsal_email;
    }

    public void setCorresponsal_email(String corresponsal_email) {
        this.corresponsal_email = corresponsal_email;
    }

    public String getCorresponsal_password() {
        return corresponsal_password;
    }

    public void setCorresponsal_password(String corresponsal_password) {
        this.corresponsal_password = corresponsal_password;
    }


    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCorresponsal_status() {
        return corresponsal_status;
    }

    public void setCorresponsal_status(int corresponsal_status) {
        this.corresponsal_status = corresponsal_status;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public int getCorresponsal_balance() {
        return corresponsal_balance;
    }

    public void setCorresponsal_balance(int corresponsal_balance) {
        this.corresponsal_balance = corresponsal_balance;
    }

    public LocalDate getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(LocalDate fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }


    public int getValor_pay_card_cop() {
        return valor_pay_card_cop;
    }

    public void setValor_pay_card_cop(int valor_pay_card_cop) {
        this.valor_pay_card_cop = valor_pay_card_cop;
    }


    public String getCard_number_pay_cop() {
        return card_number_pay_cop;
    }

    public void setCard_number_pay_cop(String card_number_pay_cop) {
        this.card_number_pay_cop = card_number_pay_cop;
    }

    public String getCvv_client_number_cop() {
        return cvv_client_number_cop;
    }

    public void setCvv_client_number_cop(String cvv_client_number_cop) {
        this.cvv_client_number_cop = cvv_client_number_cop;
    }

    public String getFecha_expiracion_client_cop() {
        return fecha_expiracion_client_cop;
    }

    public void setFecha_expiracion_client_cop(String fecha_expiracion_client_cop) {
        this.fecha_expiracion_client_cop = fecha_expiracion_client_cop;
    }

    public int getValor_pay_cuotes_cop() {
        return valor_pay_cuotes_cop;
    }

    public void setValor_pay_cuotes_cop(int valor_pay_cuotes_cop) {
        this.valor_pay_cuotes_cop = valor_pay_cuotes_cop;
    }

    public String getCvv_cliente() {
        return cvv_cliente;
    }

    public void setCvv_cliente(String cvv_cliente) {
        this.cvv_cliente = cvv_cliente;
    }

    public int getCantidad_cuotas() {
        return cantidad_cuotas;
    }

    public void setCantidad_cuotas(int cantidad_cuotas) {
        this.cantidad_cuotas = cantidad_cuotas;
    }

    public String getCorreponsal_ncuenta() {
        return correponsal_ncuenta;
    }

    public void setCorreponsal_ncuenta(String correponsal_ncuenta) {
        this.correponsal_ncuenta = correponsal_ncuenta;
    }

    public int getSaldo_cliente_deposito() {
        return saldo_cliente_deposito;
    }

    public void setSaldo_cliente_deposito(int saldo_cliente_deposito) {
        this.saldo_cliente_deposito = saldo_cliente_deposito;
    }

    public String getNombre_cliente_deposito() {
        return nombre_cliente_deposito;
    }

    public void setNombre_cliente_deposito(String nombre_cliente_deposito) {
        this.nombre_cliente_deposito = nombre_cliente_deposito;
    }

    public String getDocumento_cliente_deposito() {
        return documento_cliente_deposito;
    }

    public void setDocumento_cliente_deposito(String documento_cliente_deposito) {
        this.documento_cliente_deposito = documento_cliente_deposito;
    }

    public int getPin_cliente_deposito() {
        return pin_cliente_deposito;
    }

    public void setPin_cliente_deposito(int pin_cliente_deposito) {
        this.pin_cliente_deposito = pin_cliente_deposito;
    }

    public String getCorresponsal_transaccion_fecha() {
        return corresponsal_transaccion_fecha;
    }

    public void setCorresponsal_transaccion_fecha(String corresponsal_transaccion_fecha) {
        this.corresponsal_transaccion_fecha = corresponsal_transaccion_fecha;
    }


    public String getCorresponsal_transaccion_value() {
        return corresponsal_transaccion_value;
    }

    public void setCorresponsal_transaccion_value(String corresponsal_transaccion_value) {
        this.corresponsal_transaccion_value = corresponsal_transaccion_value;
    }

    public String getCorresponsal_transaccion_ref() {
        return corresponsal_transaccion_ref;
    }

    public void setCorresponsal_transaccion_ref(String corresponsal_transaccion_ref) {
        this.corresponsal_transaccion_ref = corresponsal_transaccion_ref;
    }


    public String getCorresponsa_transaccion_type() {
        return corresponsa_transaccion_type;
    }

    public void setCorresponsa_transaccion_type(String corresponsa_transaccion_type) {
        this.corresponsa_transaccion_type = corresponsa_transaccion_type;
    }

    public String getCorresponsal_transaccion_id() {
        return corresponsal_transaccion_id;
    }

    public void setCorresponsal_transaccion_id(String corresponsal_transaccion_id) {
        this.corresponsal_transaccion_id = corresponsal_transaccion_id;
    }

    public int getCorresponsal_rol() {
        return corresponsal_rol;
    }

    public void setCorresponsal_rol(int corresponsal_rol) {
        this.corresponsal_rol = corresponsal_rol;
    }
}
