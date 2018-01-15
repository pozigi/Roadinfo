package com.example.lib_data;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pozigi on 20. 11. 2017.
 */

public class potniNalog {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int stNaloga;
    private Oseba oseba;
  /*  private String potovanje;
    private String naloga; */
    private long danOd;
    private long danDo;
    private double denarSkupno;
    private Vozilo vozilo;
    private ArrayList<Relacija> relacije;

    public potniNalog(Oseba oseba, long danOd, long danDo, Vozilo vozilo) {
        stNaloga = count.incrementAndGet();
        this.oseba = oseba;
       // this.potovanje = potovanje;
       // this.naloga = naloga;
        this.danOd = danOd;
        this.danDo = danDo;
        this.denarSkupno = 0;
        this.vozilo = vozilo;
        this.relacije = new ArrayList<Relacija>();
    }

    public void dodajRelacija(Relacija tmp){
        relacije.add(tmp);
    }

    public Relacija relacijaNaPoziciji(int pozicija){
       return relacije.get(pozicija);
    }

    public int getStNaloga() {
        return stNaloga;
    }

    public void setStNaloga(int stNaloga) {
        this.stNaloga = stNaloga;
    }

    public Oseba getOseba() {
        return oseba;
    }

    public void setOseba(Oseba oseba) {
        this.oseba = oseba;
    }



    public long getDanOd() {
        return danOd;
    }

    public void setDanOd(long danOd) {
        this.danOd = danOd;
    }

    public long getDanDo() {
        return danDo;
    }

    public void setDanDo(long danDo) {
        this.danDo = danDo;
    }

    public double getVisinaDnevnice() {
        return denarSkupno;
    }

    public void setVisinaDnevnice(double visinaDnevnice) {
        this.denarSkupno = visinaDnevnice;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public ArrayList<Relacija> getRelacije() {
        return relacije;
    }

    public void setRelacije(ArrayList<Relacija> relacije) {
        this.relacije = relacije;
    }
}
