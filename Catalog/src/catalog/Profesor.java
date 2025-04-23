package catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private int id;
    private String nume;
    private String prenume;

    // Constructor
    public Profesor(int id, String nume, String prenume) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
    }

    // Getters
    public int getId() { return id; }
    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }

    // Metoda pentru obținerea listei de profesori
    public static List<Profesor> getAllProfesori() throws SQLException {
        List<Profesor> profesori = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Profesor";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Profesor profesor = new Profesor(
                rs.getInt("ProfesorID"),
                rs.getString("Nume"),
                rs.getString("Prenume")
            );
            profesori.add(profesor);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return profesori;
    }
}
