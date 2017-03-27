package com.urbas.zadanie1;

/**
 * Created by marm1 on 23.03.2017.
 */

public class ModelOceny {
    private String nazwa;
    private int ocena;

    public ModelOceny(String nazwa) {
        this.nazwa = nazwa;
        ocena = 2;
    }
    public ModelOceny(String nazwa, int ocena) {
        this.nazwa = nazwa;
        this.ocena = ocena;
    }

    public void ustawOcene(int i) {
        ocena = i;
    }

    public void ustawNazwe(String nazwa){
        this.nazwa = nazwa;
    }

    public int dajOcene() {
        return ocena;
    }

    public String dajNazwe() {
        return nazwa;
    }

//... - metody dostępowe daj/ustaw
}

