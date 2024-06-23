package controller;

import model.DatabaseManager;
import model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    private User loggedInUser;

    public boolean registerUser(String userId, String password, String name, Date birthDate, String phoneNumber, boolean gender, boolean state, boolean manager) {
        String checkQuery = "SELECT COUNT(*) FROM Users WHERE user_id = ? OR phone_num = ?";
        String insertQuery = "INSERT INTO Users (user_id, pw, name, birth_day, phone_num, gender, state, manager) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            checkStatement.setString(1, userId);
            checkStatement.setString(2, phoneNumber);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return false; // 이미 존재하는 user_id 또는 phone_num
            }

            insertStatement.setString(1, userId);
            insertStatement.setString(2, password);
            insertStatement.setString(3, name);
            insertStatement.setDate(4, birthDate);
            insertStatement.setString(5, phoneNumber);
            insertStatement.setBoolean(6, gender);
            insertStatement.setBoolean(7, state);
            insertStatement.setBoolean(8, manager);
            insertStatement.executeUpdate();
            return true; // 회원가입 성공
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 회원가입 실패
        }
    }

    public User loginUser(String userId, String password) {
        String query = "SELECT * FROM Users WHERE user_id = ? AND pw = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                loggedInUser = new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("pw"),
                        resultSet.getString("name"),
                        resultSet.getDate("birth_day"),
                        resultSet.getString("phone_num"),
                        resultSet.getBoolean("gender"),
                        resultSet.getBoolean("state"),
                        resultSet.getBoolean("manager")
                );
                return loggedInUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findUserId(String phoneNumber) {
        String query = "SELECT user_id FROM Users WHERE phone_num = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findUserPassword(String userId, String phoneNumber) {
        String query = "SELECT pw FROM Users WHERE user_id = ? AND phone_num = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("pw");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
