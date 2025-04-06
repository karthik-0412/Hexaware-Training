package com.techshop.oops.dao;

import java.util.List;

import com.techshop.oops.entity.*;
import com.techshop.oops.exception.DataAccessException;
import com.techshop.oops.exception.ProductNotFoundException;

public interface ProductDao {
	void addProduct(Product product) throws DataAccessException;
    Product getProductById(int productId) throws ProductNotFoundException, DataAccessException;
    List<Product> getAllProducts() throws DataAccessException;
    void updateProduct(Product product) throws DataAccessException;
    void deleteProduct(int productId) throws DataAccessException;

}
