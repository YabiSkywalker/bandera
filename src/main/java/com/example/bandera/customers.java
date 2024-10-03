package com.example.bandera;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;

import java.sql.*;


@RestController
@RequestMapping("/api")
//@Entity
//@Table(name = "Customers")
public class customers {
    /* ---------------------------------- CREATE OPERATION ----------------------------------*/
    /* ----------- CREATE A NEW CUSTOMER PROFILE INTO CUSTOMERS TABLE -------------*/

    @GetMapping("/health")
    public String sayHello() {
        return "Bandera is live!";
    }

    public static class ProfileRequest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;

        // Getters and Setters

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


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

    @PostMapping("/addProfile")
    public String profileProcessor (@RequestBody ProfileRequest request) throws SQLException, ClassNotFoundException {

        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String phoneNumber = request.getPhoneNumber();
        String email = request.getEmail();

        return addProfile(firstName, lastName, phoneNumber, email);

    }
    public String addProfile(String firstName, String lastName, String phoneNumber, String email) throws ClassNotFoundException, SQLException {


        String url = "jdbc:mysql://localhost:3306/jdbcStaticInfoDemo";
        String username = "root";
        String password = "";
        //Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "INSERT INTO Customers " +
                "(firstName, lastName, phoneNumber, email)" +
                "VALUES(?,?,?,?)";


        try (Connection connection = DriverManager.getConnection(url, username, password);)
        {
            statement = connection.prepareStatement(sql);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,phoneNumber);
            statement.setString(4,email);
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
        return "SUCCESS";
    }
    /* -------------------------------------- READ OPERATION ------------------------------------------*/
    public String  getResults() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/jdbcStaticInfoDemo";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers ");
        try {

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1) + " " + resultSet.getString(2) + " " +
                                resultSet.getString(3) + " " + resultSet.getString(4) + " " +
                                resultSet.getString(5) + " " + resultSet.getInt(6) + " " +
                                resultSet.getString(7) + " " + resultSet.getString(8) + " " +
                                resultSet.getString(9) + " " + resultSet.getString(10) + " " +
                                resultSet.getString(11) + " " + resultSet.getString(12) + " " +
                                resultSet.getString(13) + " " + resultSet.getString(14) + " " +
                                resultSet.getString(15)
                );

            }

            connection.close();;

        } catch (Exception e) {
            System.out.println(e);
        }
        return resultSet.toString();
    }
    /* ---------------------------------- UPDATE OPERATION ----------------------------------*/

    public static class updateProfile {

        private int primaryKey;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;

        // Getters and Setters
        public int getPrimaryKey() {
            return primaryKey;
        }
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

    @PatchMapping("/updateProfile")
    public String profileModifier (@RequestBody updateProfile updateProfile) throws SQLException, ClassNotFoundException {

        int primaryKey = updateProfile.getPrimaryKey();
        String firstName = updateProfile.getFirstName();
        String lastName = updateProfile.getLastName();
        String phoneNumber = updateProfile.getPhoneNumber();
        String email = updateProfile.getEmail();

        return updateCustomersRow(primaryKey, firstName, lastName, phoneNumber, email);

    }
    public String updateCustomersRow(int primaryKey, String firstName, String lastName, String phoneNumber, String email) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcStaticInfoDemo";
        String username = "root";
        String password = "";
        Connection connection = null;
        PreparedStatement statement = null;
        int paramIndex = 1; //paramIndex = the column to be updated

        try {
            connection = DriverManager.getConnection(url, username, password);
            StringBuilder sql = new StringBuilder("UPDATE Customers SET ");
            boolean isNull = true;

            if (firstName != null && !firstName.isEmpty()) {
                sql.append("firstName = ?, ");
                isNull = false;
            }

            if (lastName != null && !lastName.isEmpty()) {
                sql.append("lastName = ?, ");
                isNull = false;
            }
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                sql.append("phoneNumber = ?, ");
                isNull = false;
            }
            if (email != null && !email.isEmpty()) {
                sql.append("email = ? ");
                isNull = false;
            }

            sql.append("WHERE id = ?");
            statement = connection.prepareStatement(sql.toString());

            //Binding the values to the parameters

            if (firstName != null && !firstName.isEmpty()) {
                statement.setString(paramIndex++, firstName);
            }

            if (lastName != null && !lastName.isEmpty()) {
                statement.setString(paramIndex++, lastName);
            }
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                statement.setString(paramIndex++, phoneNumber);
            }
            if (email != null && !email.isEmpty()) {
                statement.setString(paramIndex++, email);
            }

            statement.setInt(paramIndex, primaryKey);
            int rowsAffected = statement.executeUpdate();
            System.out.println(statement);

        } catch (SQLException e) {
            System.out.println("Error: You are trying to make an entry to a column that does not exist.");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return "Updated!";
    }
    /* ---------------------------------- DELETE OPERATION ----------------------------------*/
    /*
    public static class deleteProfile {
        private int id;

        public int getId() {
            return id;
        }
    }
    */

    @DeleteMapping("/deleteProfile")
    public String deleteProfile (int userId) throws SQLException, ClassNotFoundException {


        return deleteFromCustomers(userId);

    }

    public String deleteFromCustomers(int primaryKey) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcStaticInfoDemo";
        String username = "root";
        String password = "";
        Connection connection = null;
        PreparedStatement statement = null;
        connection = DriverManager.getConnection(url, username, password);

        String sql = "DELETE from Customers where id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, primaryKey);

            int rs = statement.executeUpdate();
            if (rs > 0) {
                System.out.println("Deletion Comoleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        return "Deleted";
    }

    /* ---------------------------------- Vehicle CREATE Form -------------------------------------- */
    /* ----------- CREATE A NEW VEHICLE PROFILE INTO INVENTORY TABLE -------------*/
    public static class Vehicle {
        private String year;
        private String make;
        private String model;
        private String trim;
        private int zipCode;
        private String bodyStyle;
        private String exteriorColor;
        private String interiorColor;
        private String transmission;
        private String drivetrain;
        private String cylinders;
        private int vin;

        // Getters only

        public String getYear() {
            return year;
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public String getTrim() {
            return trim;
        }

        public int getZipCode() {
            return zipCode;
        }

        public String getBodyStyle() {
            return bodyStyle;
        }

        public String getExteriorColor() {
            return exteriorColor;
        }

        public String getInteriorColor() {
            return interiorColor;
        }

        public String getTransmission() {
            return transmission;
        }

        public String getDrivetrain() {
            return drivetrain;
        }

        public String getCylinders() {
            return cylinders;
        }
        public int getVin() {
            return vin;
        }
    }
    @PostMapping("/addVehicle")
    public String vehicleProcessor (@RequestBody Vehicle vehicle) throws SQLException, ClassNotFoundException {

        String year = vehicle.getYear();
        String make = vehicle.getMake();
        String model = vehicle.getModel();
        String trim = vehicle.getTrim();
        int zipCode = vehicle.getZipCode();
        String bodyStyle = vehicle.getBodyStyle();
        String exteriorColor = vehicle.getExteriorColor();
        String interiorColor = vehicle.getInteriorColor();
        String transmission = vehicle.getTransmission();
        String drivetrain = vehicle.getDrivetrain();
        String cylinders = vehicle.getCylinders();
        int vin = vehicle.getVin();

        return addVehicle(year, make, model, trim, zipCode, bodyStyle, exteriorColor, interiorColor, transmission, drivetrain, cylinders, vin);

    }
    public String addVehicle(String year, String make, String model, String trim, int zipCode, String bodyStyle, String exteriorColor, String interiorColor, String transmission, String drivetrain, String cylinders, int vin) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/jdbcStaticInfoDemo";
        String username = "root";
        String password = "";
        //Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "INSERT INTO Inventory "  +
                "(year, make, model, trim, zipCode, bodyStyle, exteriorColor, interiorColor, transmission, drivetrain, cylinders, vin)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet r = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, year);
            statement.setString(2, make);
            statement.setString(3, model);
            statement.setString(4, trim);
            statement.setInt(5, zipCode);
            statement.setString(6, bodyStyle);
            statement.setString(7, exteriorColor);
            statement.setString(8, interiorColor);
            statement.setString(9, transmission);
            statement.setString(10, drivetrain);
            statement.setString(11, cylinders);
            statement.setInt(12, vin);
            int exec = statement.executeUpdate();
            if (exec > 0) {
                System.out.println("Added a new vehicle");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

}
