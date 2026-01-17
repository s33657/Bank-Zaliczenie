package com.example.s33657_Bank;

public class Klient {
    private String id;
    private double saldo;

    public Klient(String id, double saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
