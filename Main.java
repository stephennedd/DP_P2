package DP_P2;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("FIND ALL FUNCTIE");
        ReizigerDaoOracleDB reizigerDaoOracleDB = new ReizigerDaoOracleDB();
        List<Reiziger> reizigers = reizigerDaoOracleDB.findAll();
        for (Reiziger reiziger : reizigers) {
            System.out.println(reiziger);
        }
        System.out.println();
        System.out.println("FIND BY GBDATUM");
        List<Reiziger> reizigers1 = reizigerDaoOracleDB.findByGBdatum(java.sql.Date.valueOf("2002-12-03"));
        for (Reiziger reiziger : reizigers1) {
            System.out.println(reiziger);
        }
//        System.out.println();
//        System.out.println("SAVE FUNCTIE");
//        Reiziger r1 = new Reiziger(10, "Nedd", "", "S", java.sql.Date.valueOf("1995-01-01"));
//        reizigerDaoOracleDB.save(r1);
//        List<Reiziger> reizigers2 = reizigerDaoOracleDB.findAll();
//        for (Reiziger reiziger : reizigers2) {
//            System.out.println(reiziger);
//        }
        System.out.println();
        System.out.println("UPDATE FUNCTIE **Stephen vervangen met Jon**");
        Reiziger r2 = new Reiziger(11, "Jones", "", "Jon", java.sql.Date.valueOf("1987-07-19"));
        reizigerDaoOracleDB.update(r2, 10);
        List<Reiziger> reizigers3 = reizigerDaoOracleDB.findAll();
        for (Reiziger reiziger : reizigers3) {
            System.out.println(reiziger);
        }
    }
}
