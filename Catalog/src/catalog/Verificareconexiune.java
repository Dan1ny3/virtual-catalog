package catalog;

import java.sql.Connection;
import java.sql.SQLException;

public class Verificareconexiune {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Conexiunea la baza de date a reușit!");
                connection.close();
            } else {
                System.out.println("Conexiunea la baza de date a eșuat!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
