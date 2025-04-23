package catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Elev {
    private int id;
    private String nume;
    private String prenume;
    private String clasa;
    private String dataNasterii;

    // Constructor
    public Elev(int id, String nume, String prenume, String clasa, String dataNasterii) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.clasa = clasa;
        this.dataNasterii = dataNasterii;
    }

    // Getters
    public int getId() { return id; }
    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }
    public String getClasa() { return clasa; }
    public String getDataNasterii() { return dataNasterii; }

    // Metoda pentru obținerea listei de elevi
    public static List<Elev> getAllElevi() throws SQLException {
        List<Elev> elevi = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Elev";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Elev elev = new Elev(
                rs.getInt("ElevID"),
                rs.getString("Nume"),
                rs.getString("Prenume"),
                rs.getString("Clasa"),
                rs.getString("DataNasterii")
            );
            elevi.add(elev);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return elevi;
    }
}
