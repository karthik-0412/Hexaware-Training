package com.techshop.oops.dao;

import java.util.List;

import com.techshop.oops.entity.*;
import com.techshop.oops.exception.CustomerNotFoundException;
import com.techshop.oops.exception.DataAccessException;

public interface CustomerDao {
	void addUser(Customer customer) throws DataAccessException;
    Customer getUserById(int userId) throws CustomerNotFoundException, DataAccessException;
    Customer getUserByUsername(String username) throws CustomerNotFoundException, DataAccessException;
    List<Customer> getAllUsers() throws DataAccessException;
    void updateUser(Customer user) throws DataAccessException;
    void deleteUser(int userId) throws DataAccessException;
}
