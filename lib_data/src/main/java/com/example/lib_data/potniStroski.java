package com.example.lib_data;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pozigi on 20. 11. 2017.
 */


public class potniStroski {
    //  private static final AtomicInteger count = new AtomicInteger(0);
    private static final AtomicInteger count = new AtomicInteger(0);
    private int idPotniStroski;
    private ArrayList<potniNalog> potniNalogi;


    public potniStroski() {
        idPotniStroski = count.incrementAndGet();
        this.potniNalogi = new ArrayList<potniNalog>();
    }
}
