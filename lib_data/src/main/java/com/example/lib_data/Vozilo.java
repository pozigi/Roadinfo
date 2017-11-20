package com.example.lib_data;

/**
 * Created by pozigi on 20. 11. 2017.
 */

public class Vozilo {
    private String naziv;
    private String registrska;

    public Vozilo(String naziv, String registrska) {
        this.naziv = naziv;
        this.registrska = registrska;
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
}
