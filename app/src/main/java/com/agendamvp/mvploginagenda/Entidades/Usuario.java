package com.agendamvp.mvploginagenda.Entidades;

public class Usuario {
                /* CLIENTE */
    private String nombre;
    private String documento;
    private int saldo;
    private int pin;
    private String card_number;

            /* CORRESPONSAL*/
    private  String corresponsal_name;
    private String corresponsal_nit;
    private String corresponsal_email;
    private String corresponsal_password;
    private int corresponsal_status;
    private int corresponsal_balance;


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
}
