import java.sql.*;
import java.util.Scanner;

class StudentDetails {
    // Future use
}

class Digilocker {

    // JDBC Connection Details
    private static final String URL = "jdbc:mysql://localhost:3306/collage";
    private static final String USER = "root";
    private static final String PASSWORD = "Coder@1122";

    void registration() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your ID: ");
        String id = input.nextLine();

        System.out.print("Enter your Name: ");
        String name = input.nextLine();

        System.out.print("Enter your Branch Name: ");
        String branch = input.nextLine();

        System.out.print("Enter your Age: ");
        String age = input.nextLine();

        System.out.print("Enter your Father's Name: ");
        String fatherName = input.nextLine();

        // Save the data to the database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // ✅ INSERT Query
            String sql = "INSERT INTO student (id, name, branch, age, fathername) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, branch);
            statement.setString(4, age);
            statement.setString(5, fatherName);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("\n✅ Registration successful for " + name + "!");
            } else {
                System.out.println("\n⚠ Registration failed. Try again.");
            }

            // ✅ Optionally show all students after registration
            String viewQuery = "SELECT * FROM student";
            Statement viewStmt = connection.createStatement();
            ResultSet rs = viewStmt.executeQuery(viewQuery);

            System.out.println("\n--- Student Records ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id") +
                        ", Name: " + rs.getString("name") +
                        ", Branch: " + rs.getString("branch") +
                        ", Age: " + rs.getString("age") +
                        ", Father: " + rs.getString("fathername"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class demoProject {
    public static void main(String[] args) {
        Digilocker bc = new Digilocker();
        bc.registration();
    }
}
