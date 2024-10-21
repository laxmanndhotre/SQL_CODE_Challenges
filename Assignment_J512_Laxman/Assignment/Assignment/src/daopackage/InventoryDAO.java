package daopackage;

import entities.Inventory;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO {
    void createInventory(Inventory inventory) throws SQLException;
    Inventory getInventoryById(int inventoryId) throws SQLException;
    List<Inventory> getAllInventories() throws SQLException;
    boolean updateInventory(Inventory inventory) throws SQLException;
    boolean deleteInventory(int inventoryId) throws SQLException;
}
