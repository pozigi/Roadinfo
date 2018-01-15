package com.example.lib_data;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pozigi on 20. 11. 2017.
 */

public class Relacija {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int idRelacija;
    private ArrayList<Lokacija> GPS;
    private String stranka;
    private double strosek;
    private double razdalja;
    private long odhodDatum;
    private long prihodDatum;
    private String dokazilo; // kej pa če slika ? pot do slike ?

    public Relacija() {

    }

    public Relacija(String stranka, double razdalja, long odhodDatum, long prihodDatum) {
        this.idRelacija = count.incrementAndGet();
        this.GPS = new ArrayList<Lokacija>();
        this.strosek = 0.0;
        this.stranka = stranka;
        this.razdalja = razdalja;
        this.odhodDatum = odhodDatum;
        this.prihodDatum = prihodDatum;
        this.dokazilo = "Vnesi dokazilo!";
    }

    public int getIdRelacija() {
        return idRelacija;
    }

    public void setIdRelacija(int idRelacija) {
        this.idRelacija = idRelacija;
    }

    public ArrayList<Lokacija> getGPS() {
        return GPS;
    }

    public void setGPS(ArrayList<Lokacija> GPS) {
        this.GPS = GPS;
    }

    public String getStranka() {
        return stranka;
    }

    public void setStranka(String stranka) {
        this.stranka = stranka;
    }

    public double getRazdalja() {
        return razdalja;
    }

    public void setRazdalja(double razdalja) {
        this.razdalja = razdalja;
    }

    public long getOdhodDatum() {
        return odhodDatum;
    }

    public void setOdhodDatum(long odhodDatum) {
        this.odhodDatum = odhodDatum;
    }

    public long getPrihodDatum() {
        return prihodDatum;
    }

    public void setPrihodDatum(long prihodDatum) {
        this.prihodDatum = prihodDatum;
    }

    public String getDokazilo() {
        return dokazilo;
    }

    public void setDokazilo(String dokazilo) {
        this.dokazilo = dokazilo;
    }

    public double getStrosek() {
        return strosek;
    }

    public void setStrosek(double strosek) {
        this.strosek = 0.37 * strosek;
    }
}
