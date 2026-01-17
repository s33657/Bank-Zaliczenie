package com.example.s33657_Bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SerwisBankTestIntegracyjny {

    @Autowired
    private SerwisBankowy serwis;

    @Test
    void powinienPrzejscCalyScenariuszBankowy(){

        String id = "klient_testowy";
        serwis.zarejestrujKlienta(id, 500.0);

        Klient k = serwis.pobierzKlienta(id);
        assertNotNull(k);
        assertEquals(500.0, k.getSaldo());

        WynikTransakcji wynikWplaty = serwis.wplacPieniadze(id, 200.0);
        assertEquals(StatusTransakcji.ZAAKCEPTOWANE, wynikWplaty.getStatus());
        assertEquals(700.0, wynikWplaty.getNoweSaldo());


        WynikTransakcji WynikPrzelewu = serwis.wykonajPrzelew(id, 300);
        assertEquals(StatusTransakcji.ZAAKCEPTOWANE, WynikPrzelewu.getStatus());
        assertEquals(400.0, WynikPrzelewu.getNoweSaldo());

        WynikTransakcji wynikZaDuzy = serwis.wykonajPrzelew(id, 1000);
        assertEquals(StatusTransakcji.ODRZUCONE, wynikZaDuzy.getStatus());
        assertEquals(400.0, wynikZaDuzy.getNoweSaldo());


    }

}
