package service;
import entities.Inventory;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DBConn;
import daopackage.InventoryDAO;
import entities.Product;
public class InventoryDAOImpl  implements InventoryDAO {

    
    @Override
    public void createInventory(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO inventories (inventoryID, productID, quantityInStock, lastStockUpdate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inventory.getInventoryID());
            pstmt.setInt(2, inventory.getProduct().getProductID());
            pstmt.setInt(3, inventory.getQuantityInStock());
            pstmt.setString(4, inventory.getLastStockUpdate());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Inventory getInventoryById(int inventoryId) throws SQLException {
        String sql = "SELECT * FROM inventories WHERE inventoryID = ?";
        try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inventoryId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Product product = getProductById(rs.getInt("productID"));
                return new Inventory(
                    rs.getInt("inventoryID"),
                    product,
                    rs.getInt("quantityInStock"),
                    rs.getString("lastStockUpdate")
                );
            }
        }
        return null;
    }

    @Override
    public List<Inventory> getAllInventories() throws SQLException {
        String sql = "SELECT * FROM inventories";
        List<Inventory> inventories = new ArrayList<>();
        try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = getProductById(rs.getInt("productID"));
                inventories.add(new Inventory(
                    rs.getInt("inventoryID"),
                    product,
                    rs.getInt("quantityInStock"),
                    rs.getString("lastStockUpdate")
                ));
            }
        }
        return inventories;
    }

    @Override
    public boolean updateInventory(Inventory inventory) throws SQLException {
        String sql = "UPDATE inventories SET productID = ?, quantityInStock = ?, lastStockUpdate = ? WHERE inventoryID = ?";
        try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inventory.getProduct().getProductID());
            pstmt.setInt(2, inventory.getQuantityInStock());
            pstmt.setString(3, inventory.getLastStockUpdate());
            pstmt.setInt(4, inventory.getInventoryID());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteInventory(int inventoryId) throws SQLException {
        String sql = "DELETE FROM inventories WHERE inventoryID = ?";
        try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inventoryId);
            return pstmt.executeUpdate() > 0;
        }
    }

    private Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM products WHERE productID = ?";
        try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantityInStock")
                );
            }
        }
        return null;
    }
}
