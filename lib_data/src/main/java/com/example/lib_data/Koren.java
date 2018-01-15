package com.example.lib_data;

import android.system.Os;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pozigi on 20. 11. 2017.
 */

public class Koren {
    private static final AtomicInteger count = new AtomicInteger(0);
    private String uporabnik;
    private Boolean loginan;
    private ArrayList<potniStroski> potniStroski;
    private ArrayList<Vozilo> vozila;
    private ArrayList<Oseba>  osebe;

    public  Koren(){
        this.uporabnik = "";
        this.loginan = false;
        this.potniStroski = new ArrayList<>();
        this.vozila = new ArrayList<>();
        this.osebe = new ArrayList<>();
    }
    public void dodajPotnega(potniStroski ps){
        potniStroski.add(ps);
    }
    public ArrayList<potniStroski> getPotneStroske(){
        return potniStroski;
    }


    public void scenarijA(){
        potniStroski ps = new potniStroski();

        Vozilo v = new Vozilo("ime","registerska");
        vozila.add(v);

        Oseba o = new Oseba("ime","priimek","naslov","kraj","posta");
        osebe.add(o);

        // ime, String priimek, String naslov, String kraj, String posta)

        potniNalog potni = new potniNalog(osebe.get(0),3300,3000,vozila.get(0));
        potniNalog potni1 = new potniNalog(o,3300,3000,v);


        Relacija r = new Relacija("Janko",200,500,500);
        Relacija r1 = new Relacija("Branko",200,500,500);

        potni.dodajRelacija(r);
        potni.dodajRelacija(r1);
        ps.dodajNalog(potni1);
        dodajPotnega(ps);
        ps.dodajNalog(potni);
    }
    public potniStroski getPotniS(int position){
        return potniStroski.get(position);
    }

    public ArrayList<potniStroski> getVsePotne (){
        return potniStroski;
    }
    public ArrayList<Vozilo> getVsaVozila(){
        return vozila;
    }
    public ArrayList<Oseba> getVseOsebe(){
        return osebe;
    }
    public void dodajVozilo(Vozilo tmp){ vozila.add(tmp);}
    public void dodajOsebo(Oseba tmp){osebe.add(tmp);}
    public void dodajPotniStrosek(){potniStroski.add(new potniStroski());}
    public Vozilo getVoziloNaMestu(int tmp){return vozila.get(tmp);}
    public Oseba getOsebaNaMestu(int tmp){return osebe.get(tmp);}
    public void dodajVPotnega(int stroska,potniNalog pn){potniStroski.get(stroska).dodajNalog(pn);}
    public void pobrisiStrosekPozicija(int pozicija){potniStroski.remove(pozicija);}
    public void pobrisiNalog(int pozicija,int pozicija1){potniStroski.get(pozicija).getPotneNaloge().remove(pozicija1);}
    public void dodajRelacijo(int ps,int pn){potniStroski.get(ps).getPotneNaloge().get(pn).dodajRelacija(new Relacija("Vnesi namen",0,System.currentTimeMillis(),System.currentTimeMillis()));}
    public void pobrisiVozilo(int pozicija){
           vozila.remove(pozicija);

    }
    public void pobrisiRelacijo(int ps,int pn,int pozicija){potniStroski.get(ps).getPotneNaloge().get(pn).getRelacije().remove(pozicija);}
    public void pobrisiOseboPozicija(int pozicija){osebe.remove(pozicija);}

    public String getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(String uporabnik) {
        this.uporabnik = uporabnik;
    }

    public Boolean getLoginan() {
        return loginan;
    }

    public void setLoginan(Boolean loginan) {
        this.loginan = loginan;
    }
}
