package com.example.lib_data;

import java.util.ArrayList;

/**
 * Created by pozigi on 3. 12. 2017.
 */

public class Mymain {
    private ArrayList<String> imena;
    public static void main(String args[]){

        Koren tmp= new Koren();
        tmp.scenarijA();
        //  tmp.dodaj(Pot.Scenarij());
        // tmp.dodaj(Pot.ScenarijA());
        //  System.out.println(tmp.toString());
    }
    public Mymain() {
        imena = new ArrayList<String>() ;
        dodaj();
    }
    void dodaj (){
        imena.add("ena");
        imena.add("dva");
        imena.add("tri");
    }
    ArrayList<String> getVse(){return imena;}

}
