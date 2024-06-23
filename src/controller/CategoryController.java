package controller;

import model.Category;
import model.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryController {
    public boolean addCategory(String categoryName) {
        String query = "INSERT INTO Category (category_name) VALUES (?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categoryName);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editCategory(int categoryId, String categoryName) {
        String query = "UPDATE Category SET category_name = ? WHERE category_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categoryName);
            statement.setInt(2, categoryId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(int categoryId) {
        String query = "DELETE FROM Category WHERE category_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT category_id, category_name FROM Category";
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                categories.add(new Category(categoryId, categoryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
