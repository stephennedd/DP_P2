package DP_P2;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDao {
    public OVChipkaart save(OVChipkaart ovChipkaart) throws SQLException;
    public List<OVChipkaart> findAll() throws SQLException;
    public List<OVChipkaart> findByReiziger(int reizigerId) throws SQLException;
    public OVChipkaart update(OVChipkaart ovChipkaart) throws SQLException;
    public boolean delete(OVChipkaart ovChipkaart) throws SQLException;
}
