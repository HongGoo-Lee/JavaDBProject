package controller;

import model.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleController {

    public boolean addSale(String userId, int productId, long price) {
        String query = "INSERT INTO Sale (user_id, product_id, price, state) VALUES (?, ?, ?, 0)";
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

    public boolean editSale(String userId, int saleId, long price) {
        String query = "UPDATE Sale SET price = ? WHERE sale_id = ? AND user_id = ? AND state = 0";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, price);
            statement.setInt(2, saleId);
            statement.setString(3, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSale(String userId, int saleId) {
        String query = "DELETE FROM Sale WHERE sale_id = ? AND user_id = ? AND state = 0";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, saleId);
            statement.setString(2, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> viewSales(String userId) {
        List<String> sales = new ArrayList<>();
        String query = "SELECT * FROM Sale WHERE user_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String sale = "ID: " + resultSet.getInt("sale_id") +
                        ", 제품 ID: " + resultSet.getInt("product_id") +
                        ", 가격: " + resultSet.getLong("price") +
                        ", 상태: " + resultSet.getInt("state");
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }
}
