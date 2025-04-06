package com.techshop.oops.dao;

import com.techshop.oops.entity.OrderDetail;
import com.techshop.oops.exception.DataAccessException;
import com.techshop.oops.exception.OrderDetailNotFoundException;

import java.util.List;

public interface OrderDetailDao {
    void addOrderDetail(OrderDetail orderDetail) throws DataAccessException;
    OrderDetail getOrderDetailById(int orderDetailId) throws DataAccessException, OrderDetailNotFoundException;
    List<OrderDetail> getAllOrderDetails() throws DataAccessException;
    void updateOrderDetail(OrderDetail orderDetail) throws DataAccessException;
    void deleteOrderDetail(int orderDetailId) throws DataAccessException;
}