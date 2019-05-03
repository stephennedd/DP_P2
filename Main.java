package DP_P2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String DB_USER = "P2";
    private static final String DB_PASS = "STEPHEN";
    private static Connection conn;

    public static void main(String[] args) throws SQLException {
        // Implementatie van de DAO klasse
        ReizigerDao reizigerDao = new ReizigerOracleDaoImpl();
    }
}
