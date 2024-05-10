package mcqs;


import java.sql.*;

public class SQLInjectionAttack {
    public static void main(String[] args) {
        String username = "gaurav' OR '1'='1";

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "username";
        String password = "password";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
Issue: SQL Injection Attack
This input is crafted to manipulate the SQL query to always return true for the condition '1'='1', effectively bypassing any authentication logic.
When the query is executed, it retrieves all user records from the database instead of just the one matching the provided username.

Mitigation:
1. Using parameterized queries or prepared statements instead of concatenating user input directly into SQL queries. Parameterized queries separate SQL code from user input, reducing the risk of SQL injection.
Example:
    public static void main(String[] args) {
        String username = "gaurav' OR '1'='1";

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "username";
        String password = "password";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, username);

                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("User ID: " + rs.getInt("id"));
                        System.out.println("Username: " + rs.getString("username"));
                        System.out.println("Email: " + rs.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
2. Sanitize user input: data type, format, length, special chars
 */
