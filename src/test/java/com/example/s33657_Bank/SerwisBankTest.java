package com.example.s33657_Bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SerwisBankTest {

    @Mock
    private Magazyn magazyn;

    @InjectMocks
    private SerwisBankowy serwis;

    @Test
    void przelewPowinienSieUdacGdySaSrodki(){
        String id = "1";
        Klient klient = new Klient(id, 500.0);

        when(magazyn.pobierzKlienta(id)).thenReturn(klient);

        WynikTransakcji wynik = serwis.wykonajPrzelew(id, 200.0);

        assertEquals(StatusTransakcji.ZAAKCEPTOWANE, wynik.getStatus());
        assertEquals(300.0, wynik.getNoweSaldo());
    }
    @Test
    void przelewPowinienZostacOdrzuconyGdyBrakSrodkow() {

        String id = "1";
        Klient klient = new Klient(id, 100.0);
        when(magazyn.pobierzKlienta(id)).thenReturn(klient);


        WynikTransakcji wynik = serwis.wykonajPrzelew(id, 200.0);


        assertEquals(StatusTransakcji.ODRZUCONE, wynik.getStatus());
        assertEquals(100.0, wynik.getNoweSaldo());
    }

    @Test
    void wplataPowinnaZostacOdrzuconaGdyKlientNieIstnieje() {

        String nieznaneId = "999";
        when(magazyn.pobierzKlienta(nieznaneId)).thenReturn(null);


        WynikTransakcji wynik = serwis.wplacPieniadze(nieznaneId, 500.0);


        assertEquals(StatusTransakcji.ODRZUCONE, wynik.getStatus());
    }
}
