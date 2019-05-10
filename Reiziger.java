package DP_P2;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {
    private String achternaam;
    private String tussenvoegsel;
    private String voorletters;
    private Date gbdatum;
    private int reizigerId;
    private ArrayList<OVChipkaart> ovChipkaarten;

    public Reiziger(int id, String an, String tv, String vl, Date gbdatum) {
        this.achternaam = an;
        this.voorletters = vl;
        this.tussenvoegsel = tv;
        this.gbdatum = gbdatum;
        this.reizigerId = id;
        ovChipkaarten = new ArrayList<OVChipkaart>();
    }

    public Reiziger() {}

    public int getId() { return reizigerId;}
    public void setId(int id) { this.reizigerId = id;}

    public String getActhernaam() { return achternaam;}
    public void setAchternaam(String n) { achternaam = n;}

    public String getVoorletters() { return voorletters;}
    public void setVoorletters(String vl) { this.voorletters = vl;}

    public String getTussenvoegsel() { return tussenvoegsel;}
    public void setTussenvoegsel(String t) { this.tussenvoegsel = t;}

    public Date getGBdatum() { return gbdatum;}
    public void setGBdatum(Date date) {this.gbdatum = date;}

    public ArrayList<OVChipkaart> getOvChipkaarten() { return ovChipkaarten;}


    @Override
    public String toString() {
        return this.reizigerId + ", " + this.voorletters + ". " + this.tussenvoegsel + " " + this.achternaam + ", " +  this.gbdatum.toString();
    }
}
