package com.techshop.oops.dao;


import com.techshop.oops.entity.Order;
import com.techshop.oops.exception.DataAccessException;
import com.techshop.oops.exception.OrderNotFoundException;

import java.util.List;

public interface OrderDao {
    void addOrder(Order order) throws DataAccessException;
    Order getOrderById(int orderId) throws DataAccessException, OrderNotFoundException;
    List<Order> getAllOrders() throws DataAccessException;
    void updateOrder(Order order) throws DataAccessException;
    void deleteOrder(int orderId) throws DataAccessException;
}