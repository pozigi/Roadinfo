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
    private double razdalja;
    private long odhodDatum;
    private long prihodDatum;
    private String dokazilo; // kej pa če slika ? pot do slike ?

    public Relacija() {

    }
}
