package DP_P2;

import java.sql.Date;
import java.util.ArrayList;

public class Reiziger {
    private String achternaam;
    private String tussenvoegsel;
    private String voorletters;
    private Date gbdatum;
    private int reizigerid;
    private ArrayList<OVChipkaart> ovChipkaarten;

    public Reiziger(int id, String an, String tv, String vl, Date gbdatum) {
        this.achternaam = an;
        this.voorletters = vl;
        this.tussenvoegsel = tv;
        this.gbdatum = gbdatum;
        this.reizigerid = id;
        ovChipkaarten = new ArrayList<OVChipkaart>();
    }

    public Reiziger() {}

    public int getId() { return reizigerid;}
    public void setId(int a) { this.reizigerid = a;}

    public String getActhernaam() { return achternaam;}
    public void setAchternaam(String n) { achternaam = n;}

    public String getVoorletters() { return voorletters;}
    public void setVoorletters(String vl) { this.voorletters = vl;}

    public String getTussenvoegsel() { return tussenvoegsel;}
    public void setTussenvoegsel(String t) { this.tussenvoegsel = t;}

    public Date getGBdatum() { return gbdatum;}
    public void setGBdatum(Date d) {this.gbdatum = d;}

    @Override
    public String toString() {
        return this.reizigerid + ", " + this.voorletters + ". " + this.tussenvoegsel + " " + this.achternaam + ", " +  this.gbdatum.toString();
    }
}
