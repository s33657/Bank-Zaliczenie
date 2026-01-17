package com.example.s33657_Bank;

import org.springframework.stereotype.Service;

@Service
public class SerwisBankowy {

    private Magazyn magazyn;

    public SerwisBankowy(Magazyn magazyn) {
        this.magazyn = magazyn;
    }

    public void zarejestrujKlienta(String id, double poczatkoweSaldo){
        if (magazyn.pobierzKlienta(id) != null){
            return;
        }
        magazyn.dodajKlienta(new Klient(id, poczatkoweSaldo));
    }

    public WynikTransakcji wykonajPrzelew(String id, double kwota){

        Klient klient = magazyn.pobierzKlienta(id);
        if(klient == null){
            return new WynikTransakcji(StatusTransakcji.ODRZUCONE, 0);
        }

        if(klient.getSaldo() < kwota){
            return new WynikTransakcji(StatusTransakcji.ODRZUCONE, klient.getSaldo());
        }

        double noweSaldo = klient.getSaldo() - kwota;
        klient.setSaldo(noweSaldo);
        return new WynikTransakcji(StatusTransakcji.ZAAKCEPTOWANE, noweSaldo);
    }

    public WynikTransakcji wplacPieniadze(String id, double kwota) {
        Klient klient = magazyn.pobierzKlienta(id);

        if (klient == null) {
            return new WynikTransakcji(StatusTransakcji.ODRZUCONE, 0);
        }

        double noweSaldo = klient.getSaldo() + kwota;
        klient.setSaldo(noweSaldo);

        return new WynikTransakcji(StatusTransakcji.ZAAKCEPTOWANE, noweSaldo);
    }

    public Klient pobierzKlienta(String id){
        return magazyn.pobierzKlienta(id);
    }
}
