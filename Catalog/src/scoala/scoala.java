package scoala;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class scoala {
    public static void main(String[] args){
        
      String url = "jdbc:mysql://localhost:3306/scoala";
        String user = "root";
        String password = "1234";


          try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // SELECT - Afișare pacienți
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Elev")) {
                while (rs.next()) {
                    System.out.println("ElevID: " + rs.getInt("ElevID") + ", Nume: " + rs.getString("nume") + ", Prenume: " + rs.getString("prenume") + ", DataNasterii: " + rs.getDate("DataNasterii") + ", Clasa: " + rs.getString("clasa"));
                }
            }

            // INSERT - Adăugare pacient
          /*  String insertSQL = "INSERT INTO Elev (Nume, Prenume, DataNasterii, Clasa) VALUES ('Dobre', 'Octavian', '1985-05-20', 'a X a')";
            stmt.executeUpdate(insertSQL);
            System.out.println("Elev adaugat cu succes!"); 
         

            // UPDATE - Actualizare pacient
             String updateSQL = "UPDATE Elev SET Clasa = 'XII B' WHERE Nume = 'Dobre' AND Prenume = 'Daniel'";
            stmt.executeUpdate(updateSQL);
            System.out.println("Elev actualizat cu succes!");

            // DELETE - Ștergere pacient
            String deleteSQL = "DELETE FROM Elev WHERE nume = 'Diaconu' AND prenume = 'Octavian'";
            stmt.executeUpdate(deleteSQL);
            System.out.println("Elev sters cu succes!");
         */   

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
