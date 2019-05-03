package DP_P2;

import java.sql.Date;
import java.util.List;

public interface ReizigerDao {
    public List<Reiziger> findAll();
    public List<Reiziger> findByGBdatum(Date GBdatum);
    public Reiziger getReiziger(int id);
    public void save(Reiziger reiziger);
    public void update(Reiziger reiziger);
    public void delete(Reiziger reiziger);
}
