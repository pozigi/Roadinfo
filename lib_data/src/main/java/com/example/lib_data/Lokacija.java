package com.example.lib_data;

/**
 * Created by pozigi on 20. 11. 2017.
 */

public class Lokacija {
    private double lang;
    private double latt;
    private long datum;

    public Lokacija(double lang, double latt, long datum) {
        this.lang = lang;
        this.latt = latt;
        this.datum = datum;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public double getLatt() {
        return latt;
    }

    public void setLatt(double latt) {
        this.latt = latt;
    }

    public long getDatum() {
        return datum;
    }

    public void setDatum(long datum) {
        this.datum = datum;
    }
}
