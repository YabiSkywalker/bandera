package com.example.bandera;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;


@RestController
@RequestMapping("/api")
public class employees {
    public static class createEmployees {

        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }
    }
    @PostMapping("/Mechanic/addProfile")
    public String profileProcessor(@RequestBody createEmployees employees) throws SQLException, ClassNotFoundException {

        String firstName = employees.getFirstName();
        String lastName = employees.getLastName();
        String phoneNumber = employees.getPhoneNumber();
        String email = employees.getEmail();

        return insertEmployee(firstName, lastName, phoneNumber, email);

    }
    public String insertEmployee(String firstName, String lastName, String phoneNumber, String email) throws ClassNotFoundException, SQLException {

        String employeeFirstName = firstName;
        String employeeLastName = lastName;
        String employeePhoneNumber = phoneNumber;
        String employeeEmail = email;

        String url = "jdbc:mysql://localhost:3306/jdbcStaticInfoDemo";
        String username = "root";
        String password = "";
        //Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "INSERT INTO Mechanics " +
                "(firstName, lastName, phoneNumber, email)" +
                "VALUES(?,?,?,?)";


        try (Connection connection = DriverManager.getConnection(url, username, password);)
        {
            statement = connection.prepareStatement(sql);
            statement.setString(1,employeeFirstName);
            statement.setString(2,employeeLastName);
            statement.setString(3,employeePhoneNumber);
            statement.setString(4,employeeEmail);
            int exec = statement.executeUpdate();

            System.out.println("Profile Created");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(statement != null) {
                statement.close();
            }
        }
        return "Success!";
    }

}
