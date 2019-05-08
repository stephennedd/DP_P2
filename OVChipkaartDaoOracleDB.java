package DP_P2;

import java.util.List;

public class OVChipkaartDaoOracleDB implements OVChipkaartDao {

    List<Reiziger> ovChipkaarten;

    public OVChipkaart save(OVChipkaart ovChipkaart, Reiziger reiziger) {
    }

    public List<Reiziger> findAll() {
        return ovChipkaarten;
    }
}
