package com.example.lib_data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pozigi on 20. 11. 2017.
 */

public class Oseba {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String ime;
    private String priimek;
    private String naslov;
    private String kraj;
    private String posta;

    public Oseba(String ime, String priimek, String naslov, String kraj, String posta) {
        this.id = count.incrementAndGet();
        this.ime = ime;
        this.priimek = priimek;
        this.naslov = naslov;
        this.kraj = kraj;
        this.posta = posta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getPosta() {
        return posta;
    }

    public void setPosta(String posta) {
        this.posta = posta;
    }
}
