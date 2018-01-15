package com.example.lib_data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pozigi on 20. 11. 2017.
 */

public class Vozilo {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String naziv;
    private String registrska;
    private int prevozeno;

    public Vozilo(String naziv, String registrska) {
        this.id = count.incrementAndGet();
        this.naziv = naziv;
        this.registrska = registrska;
        this.prevozeno = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getRegistrska() {
        return registrska;
    }

    public void setRegistrska(String registrska) {
        this.registrska = registrska;
    }

    public int getPrevozeno() {
        return prevozeno;
    }

    public void setPrevozeno(int prevozeno) {
        this.prevozeno = prevozeno;
    }

    public void dodajPrevozeno(int dodajPrevozeno){
        prevozeno +=dodajPrevozeno;
    }
}
