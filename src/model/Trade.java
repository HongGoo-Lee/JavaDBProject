package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Trade {
    private long tradeId;
    private long buyId;
    private long saleId;
    private long price;
    private long productId;

    public Trade(long tradeId, long buyId, long saleId, long price, long productId) {
        this.tradeId = tradeId;
        this.buyId = buyId;
        this.saleId = saleId;
        this.price = price;
        this.productId = productId;
    }

    public static List<Trade> findAll() {
        List<Trade> trades = new ArrayList<>();
        String sql = "SELECT * FROM Trade";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Trade trade = new Trade(
                    rs.getLong("trade_id"),
                    rs.getLong("buy_id"),
                    rs.getLong("sale_id"),
                    rs.getLong("price"),
                    rs.getLong("product_id")
                );
                trades.add(trade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trades;
    }

    public static List<Trade> findAllByUser(String userId) {
        List<Trade> trades = new ArrayList<>();
        String sql = "SELECT t.* FROM Trade t JOIN Buy b ON t.buy_id = b.buy_id JOIN Sale s ON t.sale_id = s.sale_id";
        if (userId != null) {
            sql += " WHERE b.user_id = ? OR s.user_id = ?";
        }
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (userId != null) {
                pstmt.setString(1, userId);
                pstmt.setString(2, userId);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Trade trade = new Trade(
                        rs.getLong("trade_id"),
                        rs.getLong("buy_id"),
                        rs.getLong("sale_id"),
                        rs.getLong("price"),
                        rs.getLong("product_id")
                    );
                    trades.add(trade);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trades;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", buyId=" + buyId +
                ", saleId=" + saleId +
                ", price=" + price +
                ", productId=" + productId +
                '}';
    }
}
