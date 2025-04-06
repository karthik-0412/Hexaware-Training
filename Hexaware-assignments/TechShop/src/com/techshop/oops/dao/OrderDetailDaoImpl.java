package com.techshop.oops.dao;


import com.techshop.oops.entity.Customer;
import com.techshop.oops.entity.Order;
import com.techshop.oops.entity.OrderDetail;
import com.techshop.oops.entity.Product;
import com.techshop.oops.exception.DataAccessException;
import com.techshop.oops.exception.OrderDetailNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    private Connection connection;

    public OrderDetailDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) throws DataAccessException {
        String query = "INSERT INTO orderDetails (orderDetailID, orderID, productID, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderDetail.getOrderDetailID());
            stmt.setInt(2, orderDetail.getOrder().getOrderID());
            stmt.setInt(3, orderDetail.getProduct().getProductID());
            stmt.setInt(4, orderDetail.getQuantity());
            stmt.executeUpdate();
            System.out.println("OrderDetail record added");
        } catch (SQLException e) {
            throw new DataAccessException("Failed to add order detail", e);
        }
    }

    @Override
    public OrderDetail getOrderDetailById(int orderDetailId) throws OrderDetailNotFoundException, DataAccessException {
        String query = "SELECT * FROM orderDetails WHERE orderDetailID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderDetailId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Order order = new Order(
                    rs.getInt("orderID"),
                    new Customer(rs.getInt("customerID"), "", "", "", "", ""), // Placeholder for Customer object
                    rs.getDate("orderDate"),
                    rs.getDouble("totalAmount")
                );
                Product product = new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getBoolean("instock")
                );
                return new OrderDetail(
                    rs.getInt("orderDetailID"),
                    order,
                    product,
                    rs.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve order detail by ID", e);
        }
        throw new OrderDetailNotFoundException("OrderDetail not found with ID: " + orderDetailId);
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() throws DataAccessException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM orderDetails";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("orderID"),
                    new Customer(rs.getInt("customerID"), "", "", "", "", ""), // Placeholder for Customer object
                    rs.getDate("orderDate"),
                    rs.getDouble("totalAmount")
                );
                Product product = new Product(
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getBoolean("instock")
                );
                orderDetails.add(new OrderDetail(
                    rs.getInt("orderDetailID"),
                    order,
                    product,
                    rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve all order details", e);
        }
        return orderDetails;
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) throws DataAccessException {
        String query = "UPDATE orderDetails SET orderID = ?, productID = ?, quantity = ? WHERE orderDetailID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderDetail.getOrder().getOrderID());
            stmt.setInt(2, orderDetail.getProduct().getProductID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setInt(4, orderDetail.getOrderDetailID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to update order detail", e);
        }
    }

    @Override
    public void deleteOrderDetail(int orderDetailId) throws DataAccessException {
        String query = "DELETE FROM orderDetails WHERE orderDetailID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderDetailId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete order detail", e);
        }
    }
}