package com.example.s33657_Bank;

public class WynikTransakcji {
    private StatusTransakcji status;
    private double noweSaldo;

    public WynikTransakcji(StatusTransakcji status, double noweSaldo) {
        this.status = status;
        this.noweSaldo = noweSaldo;
    }

    public StatusTransakcji getStatus() {
        return status;
    }
    public double getNoweSaldo() {
        return noweSaldo;
    }
}
