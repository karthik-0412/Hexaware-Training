package com.techshop.oops.dao;

import com.techshop.oops.entity.Inventory;
import com.techshop.oops.entity.Product;
import com.techshop.oops.exception.DataAccessException;
import com.techshop.oops.exception.InventoryNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl implements InventoryDao {
    private Connection connection;

    public InventoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addInventory(Inventory inventory) throws DataAccessException {
        String query = "INSERT INTO inventory (inventoryID, productID, quantityInStock, lastStockUpdate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, inventory.getInventoryID());
            stmt.setInt(2, inventory.getProduct().getProductID());
            stmt.setInt(3, inventory.getQuantityInStock());
            stmt.setDate(4, new java.sql.Date(inventory.getLastStockUpdate().getTime()));
            stmt.executeUpdate();
            System.out.println("Inventory record added");
        } catch (SQLException e) {
            throw new DataAccessException("Failed to add inventory", e);
        }
    }

    @Override
    public Inventory getInventoryById(int inventoryId) throws InventoryNotFoundException, DataAccessException {
        String query = "SELECT * FROM inventory WHERE inventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, inventoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getBoolean("instock")
                );
                return new Inventory(
                    rs.getInt("inventoryID"),
                    product,
                    rs.getInt("quantityInStock"),
                    rs.getDate("lastStockUpdate")
                );
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve inventory by ID", e);
        }
        throw new InventoryNotFoundException("Inventory not found with ID: " + inventoryId);
    }

    @Override
 public List<Inventory> getAllInventories() throws DataAccessException {
        List<Inventory> inventories = new ArrayList<>();
        String query = "SELECT * FROM inventory";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getBoolean("instock")
                );
                inventories.add(new Inventory(
                    rs.getInt("inventoryID"),
                    product,
                    rs.getInt("quantityInStock"),
                    rs.getDate("lastStockUpdate")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve all inventories", e);
        }
        return inventories;
    }

    @Override
    public void updateInventory(Inventory inventory) throws DataAccessException {
        String query = "UPDATE inventory SET productID = ?, quantityInStock = ?, lastStockUpdate = ? WHERE inventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, inventory.getProduct().getProductID());
            stmt.setInt(2, inventory.getQuantityInStock());
            stmt.setDate(3, new java.sql.Date(inventory.getLastStockUpdate().getTime()));
            stmt.setInt(4, inventory.getInventoryID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to update inventory", e);
        }
    }

    @Override
    public void deleteInventory(int inventoryId) throws DataAccessException {
        String query = "DELETE FROM inventory WHERE inventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, inventoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete inventory", e);
        }
    }
}