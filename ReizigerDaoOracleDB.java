package DP_P2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
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

    public List<Reiziger> findAll() throws SQLException {
        ArrayList<Reiziger> reizigers = new ArrayList<>();
        Connection connection = getConnection();
        String query = "SELECT * FROM REIZIGER";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            reizigers.add(toReiziger(resultSet));
        }
        closeConnection(connection, statement, resultSet);
        return reizigers;
    }

    public List<Reiziger> findByGBdatum(Date GBdatum) throws SQLException {
        Connection connection = getConnection();
        ArrayList<Reiziger> reizigers = new ArrayList<>();
        String query = "SELECT * FROM REIZIGER WHERE GEBORTEDATUM = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDate(1, GBdatum);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            reizigers.add(toReiziger(resultSet));
        }
        closeConnection(connection, statement, resultSet);
        return reizigers;
    }

    public Reiziger save(Reiziger reiziger) throws SQLException {
        Connection connection = getConnection();
        String query = "INSERT INTO REIZIGER (REIZIGERID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBORTEDATUM) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, reiziger.getId());
        statement.setString(2, reiziger.getVoorletters());
        statement.setString(3, reiziger.getTussenvoegsel());
        statement.setString(4, reiziger.getActhernaam());
        statement.setDate(5, reiziger.getGBdatum());
        connection.close();
        return statement.executeUpdate() == 1 ? reiziger : null;

    }

    public Reiziger update(Reiziger reiziger, int reizigerId) throws SQLException {
        Connection connection = getConnection();
        String query = "UPDATE REIZIGER SET TUSSENVOEGSEL = ?, ACHTERNAAM = ?, GEBORTEDATUM = ? WHERE REIZIGERID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, reiziger.getTussenvoegsel());
        statement.setString(2, reiziger.getActhernaam());
        statement.setDate(3,reiziger.getGBdatum());
        statement.setInt(4, reizigerId);
        return statement.executeUpdate() == 1 ? reiziger : null;
    }

    public boolean delete(Reiziger reiziger) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM REIZIGER WHERE REIZIGERID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, reiziger.getId());
        return statement.executeUpdate() == 1;
    }
}
