package com.techshop.oops.dao;

import com.techshop.oops.entity.Inventory;
import com.techshop.oops.exception.DataAccessException;
import com.techshop.oops.exception.InventoryNotFoundException;

import java.util.List;

public interface InventoryDao {
    void addInventory(Inventory inventory) throws DataAccessException;
    Inventory getInventoryById(int inventoryId) throws DataAccessException, InventoryNotFoundException;
    List<Inventory> getAllInventories() throws DataAccessException;
    void updateInventory(Inventory inventory) throws DataAccessException;
    void deleteInventory(int inventoryId) throws DataAccessException;
}
