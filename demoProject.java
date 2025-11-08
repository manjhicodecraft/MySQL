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

       // System.out.print("Enter your ID: ");
      //  int rollnum = input.nextInt();

        System.out.print("Enter your Name: ");
        String Name = input.nextLine();

        System.out.print("Enter your Branch Name: ");
        String Branch = input.nextLine();

        // System.out.print("Enter your Age: ");
       // String age = input.nextLine();

        System.out.print("Enter your Father's Name: ");
        String FatherName = input.nextLine();

        // Save the data to the database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // ✅ INSERT Query
            String sql = "INSERT INTO  StudentRegistration (Name, Branch,  FatherName) VALUES ( ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            //statement.setInt(1, rollnum);
            statement.setString(1, Name);
            statement.setString(2, Branch);
           // statement.setString(4, age);
            statement.setString(3, FatherName);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("\n✅ Registration successful for " + Name + "!");
            } else {
                System.out.println("\n⚠ Registration failed. Try again.");
            }

            // ✅ Optionally show all students after registration
            String viewQuery = "SELECT * FROM  StudentRegistration";
            Statement viewStmt = connection.createStatement();
            ResultSet rs = viewStmt.executeQuery(viewQuery);

            System.out.println("\n--- Student Records ---");
            while (rs.next()) {
                System.out.println("RollNum: " + rs.getInt("RollNum") +
                        ", Name: " + rs.getString("Name") +
                        ", Branch: " + rs.getString("Branch") +
                       // ", Age: " + rs.getString("age") +
                        ", FatherName: " + rs.getString("FatherName"));
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
