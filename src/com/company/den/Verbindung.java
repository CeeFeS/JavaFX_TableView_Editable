package com.company.den;

public class Verbindung {
    private int nr;
    private String schrank;
    private String geraet1;
    private String slot;
    private String port;
    private String nach;
    private String geraet2;
    private String schrank2;
    private String laenge;

    private String bemerkung;
    private String anbindung;
    private String fiber_type;

    public Verbindung(int nr, String schrank, String geraet1, String slot, String port, String geraet2, String schrank2, String laenge, String bemerkung, String fiber_type, String anbindung) {

        this.nr = nr;
        this.schrank = schrank;
        this.geraet1 = geraet1;
        this.slot = slot;
        this.port = port;
        this.nach = "   \u27FA  ";
        this.geraet2 = geraet2;
        this.schrank2 = schrank2;
        this.laenge = laenge;

        this.bemerkung = bemerkung;
        this.anbindung = anbindung;
        this.fiber_type = fiber_type;
    }

    public String getFiber_type() {
        return fiber_type;
    }

    public void setFiber_type(String fiber_type) {
        this.fiber_type = fiber_type;
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

    public String getGeraet1() {
        return geraet1;
    }

    public void setGeraet1(String geraet) {
        this.geraet1 = geraet;
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

    public String getGeraet2() {
        return geraet2;
    }

    public void setGeraet2(String geraet2) {
        this.geraet2 = geraet2;
    }

    public String getSchrank2() {
        return schrank2;
    }

    public void setSchrank2(String schrank2) {
        this.schrank2 = schrank2;
    }

    public String getLaenge() {
        return laenge;
    }

    public void setLaenge(String laenge) {
        this.laenge = laenge;
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
                this.geraet1 + " " +
                this.slot + " " +
                this.port + " " +
                this.nach + " " +
                this.geraet2 + " " +
                this.schrank2 + " " +
                this.laenge + " " +
                this.bemerkung + " " +
                this.anbindung;
    }
}
