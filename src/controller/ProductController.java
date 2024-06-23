package controller;

import model.DatabaseManager;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    public boolean addProduct(String productName, String category) {
        String query = "INSERT INTO Product (product_name, category_id) VALUES (?, (SELECT category_id FROM Category WHERE category_name = ?))";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productName);
            statement.setString(2, category);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editProduct(int productId, String productName, String category) {
        String query = "UPDATE Product SET product_name = ?, category_id = (SELECT category_id FROM Category WHERE category_name = ?) WHERE product_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productName);
            statement.setString(2, category);
            statement.setInt(3, productId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM Product WHERE product_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, c.category_name FROM Product p JOIN Category c ON p.category_id = c.category_id";
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String category = resultSet.getString("category_name");
                products.add(new Product(productId, productName, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
