package com.techshop.oops.dao;

import com.techshop.oops.entity.Customer;
import com.techshop.oops.entity.Order;
import com.techshop.oops.exception.DataAccessException;
import com.techshop.oops.exception.OrderNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
	private Connection connection;

	public OrderDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void addOrder(Order order) throws DataAccessException {
		String query = "INSERT INTO orders (orderID, customerID, orderDate, totalAmount) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, order.getOrderID());
			stmt.setInt(2, order.getCustomer().getCustomerID());
			stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
			stmt.setDouble(4, order.getTotalAmount());
			stmt.executeUpdate();
			System.out.println("Order record added");
		} catch (SQLException e) {
			throw new DataAccessException("Failed to add order", e);
		}
	}

	@Override
	public Order getOrderById(int orderId) throws OrderNotFoundException, DataAccessException {
		String query = "SELECT * FROM orders WHERE orderID = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer(rs.getInt("customerID"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("email"), rs.getString("phone"),
						rs.getString("address"));
				return new Order(rs.getInt("orderID"), customer, rs.getDate("orderDate"), rs.getDouble("totalAmount"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to retrieve order by ID", e);
		}
		throw new OrderNotFoundException("Order not found with ID: " + orderId);
	}

	@Override
	public List<Order> getAllOrders() throws DataAccessException {
		List<Order> orders = new ArrayList<>();
		String query = "SELECT * FROM orders";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("customerID"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("email"), rs.getString("phone"),
						rs.getString("address"));
				orders.add(new Order(rs.getInt("orderID"), customer, rs.getDate("orderDate"),
						rs.getDouble("totalAmount")));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to retrieve all orders", e);
		}
		return orders;
	}

	@Override
	public void updateOrder(Order order) throws DataAccessException {
		if (order.getCustomer() == null) {
			throw new IllegalArgumentException("Customer is not initialized for the order");
		}

		String query = "UPDATE orders SET customerID = ?, orderDate = ?, totalAmount = ? WHERE orderID = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, order.getCustomer().getCustomerID());
			stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
			stmt.setDouble(3, order.getTotalAmount());
			stmt.setInt(4, order.getOrderID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Failed to update order", e);
		}
	}

	@Override
	public void deleteOrder(int orderId) throws DataAccessException {
		String query = "DELETE FROM orders WHERE orderID = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, orderId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Failed to delete order", e);
		}
	}
}