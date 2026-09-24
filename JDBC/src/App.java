import java.sql.*;

public class App {

    private static final String URL =
            "jdbc:mysql://localhost:3306/college?useSSL=false&serverTimezone=UTC";

    private static final String USER =
            System.getenv().getOrDefault("DB_USER", "root");

    private static final String PASSWORD =
            System.getenv().getOrDefault("DB_PASSWORD", "root");

    public static void main(String[] args) {

        String insertQuery = """
                INSERT INTO student (student_id, student_name, student_branch)
                VALUES (?, ?, ?)
                """;

        String selectQuery = """
                SELECT student_id, student_name, student_branch
                FROM student
                """;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = con.prepareStatement(insertQuery)
            ) {
                System.out.println("Connection Created");

                ps.setInt(1, 104);
                ps.setString(2, "Ram");
                ps.setString(3, "CSE");

                ps.executeUpdate();
                System.out.println("Record Inserted");

                try (
                    PreparedStatement selectStatement =
                            con.prepareStatement(selectQuery);
                    ResultSet rs = selectStatement.executeQuery()
                ) {
                    System.out.println("\nStudent Records:");

                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("student_id") + " " +
                                rs.getString("student_name") + " " +
                                rs.getString("student_branch")
                        );
                    }
                }

                System.out.println("\nConnection Closed");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("A student with this ID already exists.");

        } catch (SQLException e) {
            System.out.println("Database error occurred.");
            e.printStackTrace();
        }
    }
}
