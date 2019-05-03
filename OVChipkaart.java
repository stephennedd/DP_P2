package DP_P2;

import java.sql.Date;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private Reiziger reizigerID;

    public OVChipkaart(int kn, Date gt, int kl) {
        kaartNummer = kn;
        geldigTot = gt;
        klasse = kl;
    }

    public int getKaartNummer() {return kaartNummer;}
    public void setKaartNummer(int num) {kaartNummer = num;}

    public Date getGeldigTot() {return geldigTot;}
    public void setGeldigTot(Date datum) { geldigTot = datum;}

    public int getKlasse() {return klasse;}
    public void setKlasse(int kl) {klasse = kl;}

    public double getSaldo() {return saldo;}
    public void setSaldo(double s) {saldo = s;}

    public String toString() {
        String a = "OV-Chipkaart: [ Kaartnummer : " + this.kaartNummer + ", Geldig tot: " + this.geldigTot + ", Saldo: " + this.saldo + ", klasse: " + this.klasse + " ]";
        return a;
    }
}
