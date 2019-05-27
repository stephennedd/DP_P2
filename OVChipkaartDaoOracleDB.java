package DP_P2;

import java.sql.*;
import java.util.*;

public class OVChipkaartDaoOracleDB extends OracleBaseDAO implements OVChipkaartDao {

    @Override
    public List<OVChipkaart> findAll() {
        Connection connection = super.getConnection();
        ArrayList<OVChipkaart> ovChipkaarten = new ArrayList<>();


        try {
            String query = "SELECT * FROM OV_CHIPKAART";
            Statement statement = this.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                OVChipkaart ov = new OVChipkaart();
                ov.setKaartNummer(rs.getInt("KAARTNUMMER"));
                ov.setKlasse(rs.getInt("KLASSE"));
                ov.setGeldigTot(rs.getDate("GELDIGTOT"));
                ov.setSaldo(rs.getDouble("SALDO"));

                query = "SELECT * FROM REIZIGER WHERE REIZIGERID = ?";
                PreparedStatement stmt = this.getConnection().prepareStatement(query);
                stmt.setInt(1, rs.getInt("REIZIGERID"));
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()){
                    Reiziger r = new Reiziger();
                    r.setId(resultSet.getInt("REIZIGERID"));
                    r.setVoorletters(resultSet.getString("VOORLETTERS"));
                    r.setTussenvoegsel(resultSet.getString("TUSSENVOEGSEL"));
                    r.setAchternaam(resultSet.getString("ACHTERNAAM"));
                    r.setGBdatum(resultSet.getDate("GEBORTEDATUM"));
                    for(OVChipkaart kaart : new OVChipkaartDaoOracleDB().findByReiziger(r)){
                        r.addOvChipkaart(kaart);
                    }
                }
            }
            return ovChipkaarten;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        ArrayList<OVChipkaart> ovChipkaarten = new ArrayList<>();

        try {

            String query = "SELECT * FROM OV_CHIPKAART WHERE REIZIGERID = ?";
            PreparedStatement statement = this.getConnection().prepareStatement(query);
            statement.setInt(1, reiziger.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OVChipkaart chipkaart = new OVChipkaart();
                chipkaart.setEigenaar(reiziger);
                chipkaart.setKaartNummer(resultSet.getInt("KAARTNUMMER"));
                chipkaart.setGeldigTot(resultSet.getDate("GELDIGTOT"));
                chipkaart.setSaldo(resultSet.getDouble("SALDO"));
                chipkaart.setKlasse(resultSet.getInt("KLASSE"));
                ovChipkaarten.add(chipkaart);
            }
            return ovChipkaarten;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OVChipkaart save(OVChipkaart ovChipkaart) throws SQLException {
        Connection connection = getConnection();

        String query = "INSERT INTO OV_CHIPKAART (KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID) VALUES ( ?, ?, ?, ?, ?)";


        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, ovChipkaart.getKaartNummer());
            stmt.setDate(2, ovChipkaart.getGeldigTot());
            stmt.setInt(3, ovChipkaart.getKlasse());
            stmt.setDouble(4, ovChipkaart.getSaldo());
            stmt.setInt(5, ovChipkaart.getReizigerId());

            stmt.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ovChipkaart;
    }

    @Override
    public OVChipkaart update(OVChipkaart ovChipkaart) throws SQLException {
       Connection connection = getConnection();

       try {
           String query = "UPDATE OV_CHIPKAART SET GELDIGTOT = ?, KLASSE = ?, SALDO = ?, REIZIGERID = ?";
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setDate(1, ovChipkaart.getGeldigTot());
           statement.setInt(2, ovChipkaart.getKlasse());
           statement.setDouble(3, ovChipkaart.getSaldo());
           statement.setInt(4, ovChipkaart.getReizigerId());
       }
       catch (SQLException e) {
           e.printStackTrace();
       }
       return ovChipkaart;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ovChipkaart.getKaartNummer());
        return statement.executeUpdate() == 1;
    }
}
