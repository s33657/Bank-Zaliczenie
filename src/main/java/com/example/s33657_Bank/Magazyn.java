package com.example.s33657_Bank;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Magazyn {
    private Map<String, Klient> mapaKlientow = new HashMap<>();

    public void dodajKlienta(Klient klient){
        mapaKlientow.put(klient.getId(), klient);
    }
    public Klient pobierzKlienta(String id){
        return mapaKlientow.get(id);
    }
}
