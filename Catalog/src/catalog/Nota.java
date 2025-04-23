package catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Nota {
    private int id;
    private Elev elev;
    private Curs curs;
    private int nota;
    private String data;

    // Constructor
    public Nota(int id, Elev elev, Curs curs, int nota, String data) {
        this.id = id;
        this.elev = elev;
        this.curs = curs;
        this.nota = nota;
        this.data = data;
    }

    // Getters
    public int getId() { return id; }
    public Elev getElev() { return elev; }
    public Curs getCurs() { return curs; }
    public int getNota() { return nota; }
    public String getData() { return data; }

    // Metoda pentru obținerea listei de note
    public static List<Nota> getAllNote() throws SQLException {
        List<Nota> note = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT n.NotaID, n.Nota, n.Data, e.ElevID, e.Nume AS ElevNume, e.Prenume AS ElevPrenume, e.Clasa, e.DataNasterii, " +
                     "c.CursID, c.Denumire, p.ProfesorID, p.Nume AS ProfesorNume, p.Prenume AS ProfesorPrenume " +
                     "FROM Nota n " +
                     "JOIN Elev e ON n.ElevID = e.ElevID " +
                     "JOIN Curs c ON n.CursID = c.CursID " +
                     "JOIN Profesor p ON c.ProfesorID = p.ProfesorID";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Elev elev = new Elev(
                rs.getInt("ElevID"),
                rs.getString("ElevNume"),
                rs.getString("ElevPrenume"),
                rs.getString("Clasa"),
                rs.getString("DataNasterii")
            );
            Profesor profesor = new Profesor(
                rs.getInt("ProfesorID"),
                rs.getString("ProfesorNume"),
                rs.getString("ProfesorPrenume")
            );
            Curs curs = new Curs(
                rs.getInt("CursID"),
                rs.getString("Denumire"),
                profesor
            );
            Nota nota = new Nota(
                rs.getInt("NotaID"),
                elev,
                curs,
                rs.getInt("Nota"),
                rs.getString("Data")
            );
            note.add(nota);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return note;
    }
}
