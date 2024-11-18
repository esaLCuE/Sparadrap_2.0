import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jesaispasmerde {
    private static void selectFromPersonne(Connection con) {

        String selectSQL = "select * from client";

        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while(resultSet.next()) {
                System.out.println(resultSet.getString("Cli_ndf"));
                System.out.println(resultSet.getString("Cli_prénom"));
                System.out.println(resultSet.getString("Cli_Adresse"));
                System.out.println(resultSet.getString("Cli_CodePostal"));
                System.out.println(resultSet.getString("Cli_Ville"));
                System.out.println(resultSet.getString("Cli_Téléphone"));
                System.out.println(resultSet.getString("Email"));
                System.out.println("-------------------");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
