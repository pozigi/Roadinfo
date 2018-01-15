package com.example.lib_data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pozigi on 20. 11. 2017.
 */


public class potniStroski {
    //  private static final AtomicInteger count = new AtomicInteger(0);
    private static final AtomicInteger count = new AtomicInteger(0);
    private int idPotniStroski;
    private ArrayList<potniNalog> potniNalogi = new ArrayList<potniNalog>();
    private long datum;


    public potniStroski() {
        idPotniStroski = count.incrementAndGet();
        this.potniNalogi = new ArrayList<potniNalog>();
        datum = Calendar.getInstance().getTimeInMillis();
    }
    public int getId(){
        return this.idPotniStroski;
    }

    public void dodajNalog(potniNalog pn){ potniNalogi.add(pn);}

    public ArrayList<potniNalog> getPotneNaloge(){ return potniNalogi;}

    public long getDatum() {
        return datum;
    }

    public void setDatum(long datum) {
        this.datum = datum;
    }
}
