package DP_P2;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        // initialize ReizigerDao and OVChipkaartDAO

        ReizigerDaoOracleDB reizigerDaoOracleDB = new ReizigerDaoOracleDB();
        OVChipkaartDaoOracleDB ovChipkaartDaoOracleDB = new OVChipkaartDaoOracleDB();

        // Create OVChipkaart and Reiziger objects

        OVChipkaart ov1 = new OVChipkaart(12345, java.sql.Date.valueOf("2019-12-12"), 1, 10.00, 2);
        Reiziger r1 = new Reiziger(10, "Nedd", "", "S", java.sql.Date.valueOf("1995-01-01"));

        // Test 1 Toon alle reizigers
        System.out.println("FIND ALL FUNCTIE");

        List<Reiziger> reizigers = reizigerDaoOracleDB.findAll();
        for (Reiziger reiziger : reizigers) {
            System.out.println(reiziger);
        }

        System.out.println();

        // Test 2 Toon reiziger gezocht op geboortedatum
        System.out.println("FIND BY GBDATUM");
        List<Reiziger> reizigers1 = reizigerDaoOracleDB.findByGBdatum(java.sql.Date.valueOf("2002-12-03"));
        for (Reiziger reiziger : reizigers1) {
            System.out.println(reiziger);
        }

        System.out.println();

        // Test 3 Verwijder reiziger van DB
        System.out.println("DELETE FUNCTIE");
        reizigerDaoOracleDB.delete(r1);
        for (Reiziger reiziger : reizigers) {
            System.out.println(reiziger);
        }
        System.out.println();


        System.out.println("SAVE FUNCTIE:");
        reizigerDaoOracleDB.save(r1);

        List<Reiziger> reizigers2 = reizigerDaoOracleDB.findAll();
        for (Reiziger reiziger : reizigers2) {
            System.out.println(reiziger);
        }

        System.out.println();

        System.out.println("UPDATE FUNCTIE **Stephen tussenvoegsel 'de' geven**:");
        r1.setTussenvoegsel("de");
        reizigerDaoOracleDB.update(r1);
        List<Reiziger> reizigers3 = reizigerDaoOracleDB.findAll();
        for (Reiziger reiziger : reizigers3) {
            System.out.println(reiziger);
        }
        System.out.println();

        System.out.println("OVChipkaart findAll functie:");
        List<OVChipkaart> ovChipkaarten = ovChipkaartDaoOracleDB.findAll();
        for  (OVChipkaart ovChipkaart : ovChipkaarten) {
            System.out.println(ovChipkaart);
        }
        System.out.println();

        System.out.println("OVChipkaart findByReizigerID functie ** OVChipkaarten van ReizigerID 2:");
        List<OVChipkaart> ovChipkaarten1 = ovChipkaartDaoOracleDB.findByReiziger(r1);
        for (OVChipkaart ovChipkaart : ovChipkaarten1) {
            System.out.println(ovChipkaart);
        }
        System.out.println();

        System.out.println("OVChipkaart delete functie ** ov1 verwijderen:");
        ovChipkaartDaoOracleDB.delete(ov1);
        List<OVChipkaart> ovChipkaarten2 = ovChipkaartDaoOracleDB.findAll();
        for (OVChipkaart ovChipkaart : ovChipkaarten2) {
            System.out.println(ovChipkaart);
        }
        System.out.println();

        System.out.println("OVChipkaart save functie ** ov1 toevoegen aan ReizigerID 2:");
        ovChipkaartDaoOracleDB.save(ov1);
        List<OVChipkaart> ovChipkaarten3 = ovChipkaartDaoOracleDB.findByReiziger(r1);
        for  (OVChipkaart ovChipkaart : ovChipkaarten3) {
            System.out.println(ovChipkaart);
        }
        System.out.println();

        System.out.println("OVChipkaart update functie ** ov1 set klasse naar 2");
        ov1.setKlasse(2);
        ovChipkaartDaoOracleDB.update(ov1);
        List<OVChipkaart> ovChipkaarten4 = ovChipkaartDaoOracleDB.findAll();
        for (OVChipkaart ovChipkaart : ovChipkaarten4) {
            System.out.println(ovChipkaart);
        }

    }
}
