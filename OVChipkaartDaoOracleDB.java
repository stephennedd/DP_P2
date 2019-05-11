package DP_P2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<OVChipkaart> findAll() throws SQLException {
        Connection connection = getConnection();
        List<OVChipkaart> ovChipkaarten = new ArrayList<>();
        String query = "SELECT * FROM OV_CHIPKAART";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ovChipkaarten.add(toOVChipkaart(rs));
        }
        closeConnection(connection, stmt, rs);
        return ovChipkaarten;
    }
    public OVChipkaart save(OVChipkaart ovChipkaart) throws SQLException {
        Connection connection = getConnection();
        String query = "INSERT INTO OV_CHIPKAART (KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID) VALUES ( ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,ovChipkaart.getKaartNummer());
        stmt.setDate(2,ovChipkaart.getGeldigTot());
        stmt.setInt(3, ovChipkaart.getKlasse());
        stmt.setDouble(4, ovChipkaart.getSaldo());
        stmt.setInt(5, ovChipkaart.getReizigerId());
        return stmt.executeUpdate() == 1 ? ovChipkaart : null;
    }

    public List<OVChipkaart> findByReiziger(int reizigerId) throws SQLException {
        Connection connection = getConnection();
        List<OVChipkaart> ovChipkaarten = new ArrayList<>();
        String query = "SELECT * FROM OV_CHIPKAART WHERE REIZIGERID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,reizigerId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ovChipkaarten.add(toOVChipkaart(resultSet));
        }
        closeConnection(connection, statement, resultSet);
        return ovChipkaarten;
    }

    public OVChipkaart update(OVChipkaart ovChipkaart) throws SQLException {
       Connection connection = getConnection();
       String query = "UPDATE OV_CHIPKAART SET GELDIGTOT = ?, KLASSE = ?, SALDO = ?, REIZIGERID = ?";
       PreparedStatement statement = connection.prepareStatement(query);
       statement.setDate(1,ovChipkaart.getGeldigTot());
       statement.setInt(2, ovChipkaart.getKlasse());
       statement.setDouble(3, ovChipkaart.getSaldo());
       statement.setInt(4, ovChipkaart.getReizigerId());
       return statement.executeUpdate() == 1 ? ovChipkaart : null;
    }

    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ovChipkaart.getKaartNummer());
        return statement.executeUpdate() == 1;
    }
}
