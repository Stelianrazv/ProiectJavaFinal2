package com.Inregistrare.Auto;

import java.sql.*;

/* Clasa principala de unde incepe aplicatia sa se execute*/
public class MainApp {
    static int masinaCount = 1;
    static int camionCount = 1;
    Statement myStmt;
    public static void main(String [] args) {

        // Deschide prima fereastra - WelcomeGui
        // Conexiune la baza de date
        String dbUrl = "jdbc:postgresql:cars";
        String user = "postgres";
        String password = "cursjava";

        try {
            Connection myConn = DriverManager.getConnection(dbUrl, user, password);
            Statement myStmt = myConn.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from masina");
            while (myRs.next()) {
                masinaCount++;
            }
            ResultSet myRs2 = myStmt.executeQuery("select * from camion");
            while (myRs2.next()) {
                camionCount++;
            }
            new WelcomeGui(myStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


