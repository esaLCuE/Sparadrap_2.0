import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBDD {
    private void firstConnection() {
        String BDD = "Sparadrap_BDD";
        String url = "jdbc:mysql://localhost:3306/Sparadrap_BDD";
        String user =  "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to the database");

            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
