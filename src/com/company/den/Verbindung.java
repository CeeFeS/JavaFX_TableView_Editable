package com.company.den;

public class Verbindung {
    private int nr;
    private String schrank;
    private String geraet;
    private String slot;
    private String port;
    private String nach;
    private String bucht;
    private String footprint;
    private String laenge;
    private String lwl;
    private String bemerkung;
    private String anbindung;

    public Verbindung(int nr, String schrank, String geraet, String slot, String port, String bucht, String footprint, String laenge, String lwl, String bemerkung, String anbindung) {

        this.nr = nr;
        this.schrank = schrank;
        this.geraet = geraet;
        this.slot = slot;
        this.port = port;
        this.nach = "<-->";
        this.bucht = bucht;
        this.footprint = footprint;
        this.laenge = laenge;
        this.lwl = lwl;
        this.bemerkung = bemerkung;
        this.anbindung = anbindung;
    }

    public String getAnbindung() {
        return anbindung;
    }

    public void setAnbindung(String anbindung) {
        this.anbindung = anbindung;
    }

    public String getNach() {
        return nach;
    }

    public void setNach(String nach) {
        this.nach = nach;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getSchrank() {
        return schrank;
    }

    public void setSchrank(String schrank) {
        this.schrank = schrank;
    }

    public String getGeraet() {
        return geraet;
    }

    public void setGeraet(String geraet) {
        this.geraet = geraet;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBucht() {
        return bucht;
    }

    public void setBucht(String bucht) {
        this.bucht = bucht;
    }

    public String getFootprint() {
        return footprint;
    }

    public void setFootprint(String footprint) {
        this.footprint = footprint;
    }

    public String getLaenge() {
        return laenge;
    }

    public void setLaenge(String laenge) {
        this.laenge = laenge;
    }

    public String getLwl() {
        return lwl;
    }

    public void setLwl(String lwl) {
        this.lwl = lwl;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    @Override
    public String toString() {
        return this.nr + " " +
                this.schrank + " " +
                this.geraet + " " +
                this.slot + " " +
                this.port + " " +
                this.nach + " " +
                this.bucht + " " +
                this.footprint + " " +
                this.laenge + " " +
                this.lwl + " " +
                this.bemerkung + " " +
                this.anbindung;
    }
}
