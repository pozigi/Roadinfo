package com.example.lib_data;

/**
 * Created by pozigi on 20. 11. 2017.
 */

public class Oseba {
    private String id;
    private String ime;
    private String priimek;
    private String naslov;
    private String kraj;
    private String posta;

    public Oseba(String id, String ime, String priimek, String naslov, String kraj, String posta) {
        this.id = id;
        this.ime = ime;
        this.priimek = priimek;
        this.naslov = naslov;
        this.kraj = kraj;
        this.posta = posta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
