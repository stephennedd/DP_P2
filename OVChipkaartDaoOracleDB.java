package DP_P2;

import java.sql.*;
import java.util.*;

public class OVChipkaartDaoOracleDB extends OracleBaseDAO implements OVChipkaartDao {

    private OVChipkaart toOVChipkaart( ResultSet resultSet) throws SQLException {
        OVChipkaart ovChipkaart = new OVChipkaart(
                resultSet.getInt("KAARTNUMMER"),
                resultSet.getDate("GELDIGTOT"),
                resultSet.getInt("KLASSE"),
                resultSet.getDouble("SALDO"),
                resultSet.getInt("REIZIGERID")
        );
        return ovChipkaart;
    }

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
                ovChipkaarten.add((ov));

                query = "SELECT * FROM REIZIGER WHERE REIZIGERID = ?";
                PreparedStatement stmt = this.getConnection().prepareStatement(query);
                stmt.setInt(1, rs.getInt("REIZIGERID"));
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()){
                    Reiziger r = new Reiziger();
                    r.setId(resultSet.getInt("REIZIGERID"));
                    r.setVoorletters(resultSet.getString("VOORLETTERS"));
                    r.setTussenvoegsel(resultSet.getString("TUSSEN"));

                }
            }
            return ovChipkaarten;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        Connection connection = getConnection();
        ArrayList<OVChipkaart> ovChipkaarten = new ArrayList<>();

        try {

            String query = "SELECT * FROM OV_CHIPKAART WHERE REIZIGERID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reiziger.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ovChipkaarten.add(toOVChipkaart(resultSet));
            }
            return ovChipkaarten;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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


    public List<OVChipkaart> findByReizigerID(int reizigerID) {

        try {
            Connection connection = getConnection();
            ArrayList<OVChipkaart> ovChipkaarts = new ArrayList<>();
            String query = "SELECT * FROM OV_CHIPKAART WHERE REIZIGERID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,reizigerID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ovChipkaarts.add(toOVChipkaart(resultSet));
            }
            return ovChipkaarts;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ovChipkaart.getKaartNummer());
        return statement.executeUpdate() == 1;
    }
}
