package DP_P2;

import java.sql.*;

public class OracleBaseDAO {
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String DB_USER = "P2";
    private static final String DB_PASS = "STEPHEN";
    private static Connection conn;

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        connection.close();
        statement.close();
        resultSet.close();
    }
}
