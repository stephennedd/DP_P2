package DP_P2;

import java.sql.Date;

public class Reiziger {
    private String naam;
    private Date gbdatum;
    private int id = 0;

    public Reiziger(int id, String naam, Date gbdatum) {
        this.naam = naam;
        this.gbdatum = gbdatum;
        this.id = id;
    }

    public Reiziger() {}

    public int getId() { return id;}
    public void setId(int a) { id = a;}

    public String getNaam() { return naam;}
    public void setNaam(String n) { naam = n;}

    public Date getGBdatum() { return gbdatum;}
    public void setGBdatum(Date d) {gbdatum = d;}

    @Override
    public String toString() {
        return this.id + ", " + this.naam + ", " + this.gbdatum.toString();
    }
}
