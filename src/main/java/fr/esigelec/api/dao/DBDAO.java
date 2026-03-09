package fr.esigelec.api.dao;

import fr.esigelec.api.jee.CountryModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAO {
    
    // Update these with your actual database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/dbdao";
    private static final String USER = "root";
    private static final String PASS = "Esi_Lec-28-29INGE";

    // 1. Connection Method
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    //For Country
    public boolean insertCountry(String code, String name, String capital) {
        if (code == null || code.isBlank() || name == null || name.isBlank() || capital == null || capital.isBlank() || code.length() != 2) {
            return false;
        }
        String sql = "INSERT INTO countries (code, name, capital) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, capital);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCountry(String code, String name, String capital) {
        String sql = "UPDATE countries SET name = ?, capital = ? WHERE code = ?";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, capital);
            ps.setString(3, code);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCountry(String code) {
        String sql = "DELETE FROM countries WHERE code = ?";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CountryModel getCountry(String code) {
        String sql = "SELECT * FROM countries WHERE code = ?";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CountryModel country = new CountryModel();
                country.setCode(rs.getString("code"));
                country.setName(rs.getString("name"));
                country.setCapital(rs.getString("capital"));
                return country;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getCountryCodes() {
        List<String> codes = new ArrayList<>();
        String sql = "SELECT code FROM countries";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                codes.add(rs.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codes;
    }

    // For the User
    public boolean createUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateUserCredentials(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Returns true if a match is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}