package DP_P2;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl implements ReizigerDao {

    List<Reiziger> reizigers;
    List<Reiziger> result;

    public ReizigerOracleDaoImpl() {
        reizigers = new ArrayList<Reiziger>();
    }

    public List<Reiziger> findAll() {
        return reizigers;
    }

    public Reiziger getReiziger(int id) {
        return reizigers.get(id);
    }

    public List<Reiziger> findByGBdatum(Date GBdatum) {
        for (Reiziger r : reizigers) {
            if (r.getGBdatum().equals(GBdatum)) {
                result.add(r);
            }
        }
        return result;
    }

    public void save(Reiziger reiziger) {
        if (!reizigers.contains(reiziger)) {
            reizigers.add(reiziger);
            System.out.println("Reiziger: " + reiziger.getId() + ", Naam: " + reiziger.getNaam() + ", Toegevoegd aan database.");
        }
    }

    public void update(Reiziger reiziger) {
        for(int i = 0; i<reizigers.size(); i++) {
            if(reizigers.get(i).equals(reiziger)) {
                reiziger.setNaam(reiziger.getNaam());
                System.out.println("Reiziger: " + reiziger.getId() + ", " + reiziger.getNaam() + ", bijgewerkt in het database");
            }
        }
    }

    public void delete(Reiziger reiziger) {
        for(int i = 0; i<reizigers.size(); i++) {
            if(reizigers.get(i).equals(reiziger)) {
                reizigers.remove(i);
                i--;
                System.out.println("Reiziger: " + reiziger.getNaam() + ", " + reiziger.getGBdatum() + ", verwijderd van database");
            }
        }
    }
}
