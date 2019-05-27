package DP_P2;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDaoOracleDB extends OracleBaseDAO implements ReizigerDao {

    private Reiziger toReiziger(ResultSet resultSet) throws SQLException {
        Reiziger reiziger = new Reiziger(
        resultSet.getInt("REIZIGERID"),
        resultSet.getString("ACHTERNAAM"),
        resultSet.getString("TUSSENVOEGSEL"),
        resultSet.getString("VOORLETTERS"),
        resultSet.getDate("GEBORTEDATUM")
        );
        return reiziger;
    }

    @Override
    public List<Reiziger> findAll() {

        Connection connection = super.getConnection();
        ArrayList<Reiziger> reizigers = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " + " FROM REIZIGER");

            while (resultSet.next()) {
                // maak nieuwe reiziger
                Reiziger r = new Reiziger();

                // Set reiziger id
                r.setId(resultSet.getInt("REIZIGERID"));

                // set reiziger naam
                r.setAchternaam(resultSet.getString("ACHTERNAAM"));
                r.setVoorletters(resultSet.getString("VOORLETTERS"));

                // set reiziger geboortedatum
                r.setGBdatum(resultSet.getDate("GEBORTEDATUM"));

                // haal "alle" ovchipkaarten van reiziger op
                for (OVChipkaart kaart : new OVChipkaartDaoOracleDB().findByReiziger(r)) {
                    r.addOvChipkaart(kaart);
                }

                // voeg reiziger toe aan lijst van reizigers
                reizigers.add(r);
            }

            return reizigers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGBdatum(Date date) {

        Connection connection = getConnection();
        ArrayList<Reiziger> reizigers = new ArrayList<>();

        try {
            String query = "SELECT * FROM REIZIGER WHERE GEBORTEDATUM = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reiziger r = new Reiziger();
                r.setId(resultSet.getInt("REIZIGERID"));
                r.setAchternaam(resultSet.getString("ACHTERNAAM"));
                r.setGBdatum(resultSet.getDate("GEBORTEDATUM"));
                r.setVoorletters(resultSet.getString("VOORLETTERS"));

                List<OVChipkaart> ovkaarten1 = new OVChipkaartDaoOracleDB().findByReiziger(r);
                for (OVChipkaart kaart : ovkaarten1) {
                    r.addOvChipkaart(kaart);
                }
                reizigers.add(r);
            }
            return reizigers;
        }
        catch (SQLException e) { e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reiziger save(Reiziger reiziger) {

        String query = "INSERT INTO reiziger (REIZIGERID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBORTEDATUM)" + " VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, reiziger.getId());
            preparedStatement.setString(2, reiziger.getVoorletters());
            preparedStatement.setString(3, reiziger.getTussenvoegsel());
            preparedStatement.setString(4, reiziger.getActhernaam());
            preparedStatement.setDate(5, reiziger.getGBdatum());
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return reiziger;

    }

    @Override
    public Reiziger update(Reiziger reiziger) throws SQLException {
        Connection connection = getConnection();
        String query = "UPDATE REIZIGER SET TUSSENVOEGSEL = ?, ACHTERNAAM = ?, GEBORTEDATUM = ? WHERE REIZIGERID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, reiziger.getTussenvoegsel());
        statement.setString(2, reiziger.getActhernaam());
        statement.setDate(3,reiziger.getGBdatum());
        statement.setInt(4, reiziger.getId());
        return statement.executeUpdate() == 1 ? reiziger : null;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM REIZIGER WHERE REIZIGERID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, reiziger.getId());
        return statement.executeUpdate() == 1;
    }
}
