package catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Curs {
    private int id;
    private String denumire;
    private Profesor profesor;

    // Constructor
    public Curs(int id, String denumire, Profesor profesor) {
        this.id = id;
        this.denumire = denumire;
        this.profesor = profesor;
    }

    // Getters
    public int getId() { return id; }
    public String getDenumire() { return denumire; }
    public Profesor getProfesor() { return profesor; }

    // Metoda pentru obținerea listei de cursuri
    public static List<Curs> getAllCursuri() throws SQLException {
        List<Curs> cursuri = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT c.CursID, c.Denumire, p.ProfesorID, p.Nume, p.Prenume FROM Curs c JOIN Profesor p ON c.ProfesorID = p.ProfesorID";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Profesor profesor = new Profesor(
                rs.getInt("ProfesorID"),
                rs.getString("Nume"),
                rs.getString("Prenume")
            );
            Curs curs = new Curs(
                rs.getInt("CursID"),
                rs.getString("Denumire"),
                profesor
            );
            cursuri.add(curs);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return cursuri;
    }
}
