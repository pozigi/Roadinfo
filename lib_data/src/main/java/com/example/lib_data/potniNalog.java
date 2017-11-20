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
    private String potovanje;
    private String naloga;
    private long danOd;
    private long danDo;
    private double visinaDnevnice;
    private Vozilo vozilo;
    private ArrayList<Relacija> relacije;

    public potniNalog(Oseba oseba, String potovanje, String naloga, long danOd, long danDo, double visinaDnevnice, Vozilo vozilo) {
        stNaloga = count.incrementAndGet();
        this.oseba = oseba;
        this.potovanje = potovanje;
        this.naloga = naloga;
        this.danOd = danOd;
        this.danDo = danDo;
        this.visinaDnevnice = visinaDnevnice;
        this.vozilo = vozilo;
        this.relacije = new ArrayList<Relacija>();
    }
}
