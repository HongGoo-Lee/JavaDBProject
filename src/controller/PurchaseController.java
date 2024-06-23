package controller;

import model.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseController {

    public boolean addPurchase(String userId, int productId, long price) {
        String query = "INSERT INTO Buy (user_id, product_id, price, state) VALUES (?, ?, ?, 0)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setInt(2, productId);
            statement.setLong(3, price);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editPurchase(String userId, int purchaseId, long price) {
        String query = "UPDATE Buy SET price = ? WHERE buy_id = ? AND user_id = ? AND state = 0";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, price);
            statement.setInt(2, purchaseId);
            statement.setString(3, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePurchase(String userId, int purchaseId) {
        String query = "DELETE FROM Buy WHERE buy_id = ? AND user_id = ? AND state = 0";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, purchaseId);
            statement.setString(2, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> viewPurchases(String userId) {
        List<String> purchases = new ArrayList<>();
        String query = "SELECT * FROM Buy WHERE user_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String purchase = "ID: " + resultSet.getInt("buy_id") +
                        ", 제품 ID: " + resultSet.getInt("product_id") +
                        ", 가격: " + resultSet.getLong("price") +
                        ", 상태: " + resultSet.getInt("state");
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }
}
