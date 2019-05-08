package DP_P2;

import java.util.List;

public interface OVChipkaartDao {
    public OVChipkaart save(OVChipkaart ovChipkaart, Reiziger reiziger);
    public List<OVChipkaart> findAll();
    public List<OVChipkaart> findByReiziger(Reiziger reiziger);
    public OVChipkaart update(OVChipkaart ovChipkaart);
    public boolean delete(OVChipkaart ovChipkaart);
}
