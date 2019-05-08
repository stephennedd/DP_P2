package DP_P2;

import java.sql.*;
import java.sql.Date;

public class Main {
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String DB_USER = "P2";
    private static final String DB_PASS = "STEPHEN";
    private static Connection conn;

    public static void main(String[] args) throws SQLException {

        try {
            Class.forName(DB_DRIV).newInstance();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

    }
}
