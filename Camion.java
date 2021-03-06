package com.Inregistrare.Auto;

import java.sql.SQLException;
import java.sql.Statement;

public class Camion {
    int id;
    String Nume;
    String Prenume;
    String Judet;
    String Marca;
    String Model;
    int An;


    public Camion (int id, String Nume, String Prenume, String Judet, String Marca, String Model, String An) {
        this.id = id;
        this.Nume = Nume;
        this.Prenume = Prenume;
        this.Judet = Judet;
        this.Marca = Marca;
        this.Model = Model;
        this.An = Integer.parseInt(An);
    }

    public void saveCamion(Statement myStmt) {
        String insertDb = "insert into camion"
                + "(id,nume,prenume,judet,marca,model,an)"
                + "values ("
                + this.id + ", '" + this.Nume + "','" +
                this.Prenume + "', '" + this.Judet + "', '" + this.Marca + "', '" + this.Model + "', " + this.An  +")";
        try {
            myStmt.executeUpdate(insertDb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
