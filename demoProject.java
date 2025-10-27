import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

class student_details{

}
class digilocker{

    //    long accountNumber;
// JDBC Connection Details
    private static final String URL = "jdbc:mysql://localhost:3306/collage";
    private static final String USER = "root";
    private static final String PASSWORD = "Coder@1122";
    void ragistration(){

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your id: ");
        String id = input.nextLine();

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.print("Enter your branchName ");
        String branch = input.nextLine();

        System.out.print("Enter your age ");// consume newline
        String age = input.nextLine();

        System.out.print("Enter your fatherName: ");
        String fathername= input.nextLine();

        // Save the data to the database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String sql = "INSERT INTO  student(id,name, branch, age, fathername) VALUES (?,?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, branch);
            statement.setString(4, age);
            statement.setString(5, fathername);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("✅ ragistration is successful " + name);
            } else {
                System.out.println("⚠ Failed to create account. Try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        input.close();

    }
}
class CurrencySupport{

}

public class demoProject{
    public static void main(String[] args) {
        digilocker BC = new digilocker();
        BC.ragistration();
    }
}