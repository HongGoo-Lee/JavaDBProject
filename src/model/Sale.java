package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private long productId;
    private String userId;
    private long price;

    public Sale(long productId, String userId, long price) {
        this.productId = productId;
        this.userId = userId;
        this.price = price;
    }

    public void insert() {
        String sql = "INSERT INTO Sale (product_id, user_id, price, state) VALUES (?, ?, ?, 0)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, productId);
            pstmt.setString(2, userId);
            pstmt.setLong(3, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        String sql = "DELETE FROM Sale WHERE product_id = ? AND user_id = ? AND price = ? AND state = 0";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, productId);
            pstmt.setString(2, userId);
            pstmt.setLong(3, price);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Sale record deleted successfully.");
            } else {
                System.out.println("No matching Sale record found with state = 0.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(long newPrice) {
        String sql = "UPDATE Sale SET price = ? WHERE product_id = ? AND user_id = ? AND state = 0";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, newPrice);
            pstmt.setLong(2, productId);
            pstmt.setString(3, userId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Sale record updated successfully.");
            } else {
                System.out.println("No matching Sale record found with state = 0.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Sale> findAll(String userId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM Sale";
        if (userId != null) {
            sql += " WHERE user_id = ?";
        }
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (userId != null) {
                pstmt.setString(1, userId);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale(
                        rs.getLong("product_id"),
                        rs.getString("user_id"),
                        rs.getLong("price")
                    );
                    sales.add(sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "productId=" + productId +
                ", userId='" + userId + '\'' +
                ", price=" + price +
                '}';
    }
}
