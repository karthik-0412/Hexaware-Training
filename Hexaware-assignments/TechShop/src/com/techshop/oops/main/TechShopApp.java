package com.techshop.oops.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.techshop.oops.dao.*;
import com.techshop.oops.entity.*;
import com.techshop.oops.exception.*;
import com.techshop.oops.util.DatabaseConnection;

public class TechShopApp {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            CustomerDao customerDao = new CustomerDaoImpl(connection);
            ProductDao productDao = new ProductDaoImpl(connection);
            OrderDao orderDao = new OrderDaoImpl(connection);
            OrderDetailDao orderDetailDao = new OrderDetailDaoImpl(connection);
            InventoryDao inventoryDao = new InventoryDaoImpl(connection);

            boolean running = true;

            while (running) {
                System.out.println("\n--- TechShop Management ---");
                System.out.println("1. Manage Customers");
                System.out.println("2. Manage Products");
                System.out.println("3. Manage Orders");
                System.out.println("4. Manage Order Details");
                System.out.println("5. Manage Inventory");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        manageCustomers(scanner, customerDao);
                        break;
                    case 2:
                        manageProducts(scanner, productDao);
                        break;
                    case 3:
                        manageOrders(scanner, orderDao);
                        break;
                    case 4:
                        manageOrderDetails(scanner, orderDetailDao);
                        break;
                    case 5:
                        manageInventory(scanner, inventoryDao);
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Customer Management
    private static void manageCustomers(Scanner scanner, CustomerDao customerDao) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Customer Management ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer by ID");
            System.out.println("3. View All Customers");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCustomer(scanner, customerDao);
                    break;
                case 2:
                    viewCustomerById(scanner, customerDao);
                    break;
                case 3:
                    viewAllCustomers(customerDao);
                    break;
                case 4:
                    updateCustomer(scanner, customerDao);
                    break;
                case 5:
                    deleteCustomer(scanner, customerDao);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCustomer(Scanner scanner, CustomerDao customerDao) {
        System.out.print("Enter customerId: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Customer newCustomer = new Customer(customerId, firstName, lastName, email, phone, address);
        try {
            customerDao.addUser(newCustomer);
            System.out.println("Customer added successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    private static void viewCustomerById(Scanner scanner, CustomerDao customerDao) {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Customer customer = customerDao.getUserById(id);
            if (customer != null) {
                System.out.println("Customer Details: " + customer.toString());
            } else {
                System.out.println("Customer not found.");
            }
        } catch (CustomerNotFoundException e) {
            System.out.println("Customer not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error retrieving customer: " + e.getMessage());
        }
    }

    private static void viewAllCustomers(CustomerDao customerDao) {
        try {
            List<Customer> customers = customerDao.getAllUsers();
            System.out.println("\nCustomer List:");
            for (Customer c : customers) {
                System.out.println(c.toString());
            }
        } catch (DataAccessException e) {
            System.out.println("Error retrieving all customers: " + e.getMessage());
        }
    }

    private static void updateCustomer(Scanner scanner, CustomerDao customerDao) {
        System.out.print("Enter Customer ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Customer existingCustomer = customerDao.getUserById(updateId);
            if (existingCustomer != null) {
                System.out.print("Enter new Phone: ");
                String newPhone = scanner.nextLine();
                existingCustomer.setPhone(newPhone);
                customerDao.updateUser(existingCustomer);
                System.out.println("Customer updated successfully!");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (CustomerNotFoundException e) {
            System.out.println("Customer not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    private static void deleteCustomer(Scanner scanner, CustomerDao customerDao) {
        System.out.print("Enter Customer ID to delete: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            customerDao.deleteUser(deleteId);
            System.out.println("Customer deleted successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    // Product Management
    private static void manageProducts(Scanner scanner, ProductDao productDao) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Product by ID");
            System.out.println("3. View All Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct(scanner, productDao);
                    break;
                case 2:
                    viewProductById(scanner, productDao);
                    break;
                case 3:
                    viewAllProducts(productDao);
                    break;
                case 4:
                    updateProduct(scanner, productDao);
                    break;
                case 5:
                    deleteProduct(scanner, productDao);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct(Scanner scanner, ProductDao productDao) {
        System.out.print("Enter productID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Is the product in stock? (true/false): ");
        boolean instock = scanner.nextBoolean();
        scanner.nextLine();

        Product newProduct = new Product(productID, productName, description, price, instock);
        try {
            productDao.addProduct(newProduct);
            System.out.println("Product added successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private static void viewProductById(Scanner scanner, ProductDao productDao) {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Product product = productDao.getProductById(id);
            if (product != null) {
                System.out.println("Product Details: " + product.toString());
            } else {
                System.out.println("Product not found.");
            }
        } catch (ProductNotFoundException e) {
            System.out.println("Product not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error retrieving product: " + e.getMessage());
        }
    }

    private static void viewAllProducts(ProductDao productDao) {
        try {
            List<Product> products = productDao.getAllProducts();
            System.out.println("\nProduct List:");
            for (Product p : products) {
                System.out.println(p.toString());
            }
        } catch (DataAccessException e) {
            System.out.println("Error retrieving all products: " + e.getMessage());
        }
    }

    private static void updateProduct(Scanner scanner, ProductDao productDao) {
        System.out.print("Enter Product ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Product existingProduct = productDao.getProductById(updateId);
            if (existingProduct != null) {
                System.out.print("Enter new Price: ");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();
                existingProduct.setPrice(newPrice);
                productDao.updateProduct(existingProduct);
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product not found.");
            }
        } catch (ProductNotFoundException e) {
            System.out.println("Product not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }

    private static void deleteProduct(Scanner scanner, ProductDao productDao) {
        System.out.print("Enter Product ID to delete: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            productDao.deleteProduct(deleteId);
            System.out.println("Product deleted successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    // Order Management
    private static void manageOrders(Scanner scanner, OrderDao orderDao) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Order Management ---");
            System.out.println("1. Add Order");
            System.out.println("2. View Order by ID");
            System.out.println("3. View All Orders");
            System.out.println("4. Update Order");
            System.out.println("5. Delete Order");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addOrder(scanner, orderDao);
                    break;
                case 2:
                    viewOrderById(scanner, orderDao);
                    break;
                case 3:
                    viewAllOrders(orderDao);
                    break;
                case 4:
                    updateOrder(scanner, orderDao);
                    break;
                case 5:
                    deleteOrder(scanner, orderDao);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @SuppressWarnings("deprecation")
	private static void addOrder(Scanner scanner, OrderDao orderDao) {
        System.out.print("Enter orderID: ");
        int orderID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Order Date (yyyy-MM-dd): ");
        String orderDateStr = scanner.nextLine();
        java.util.Date orderDate = new java.util.Date(orderDateStr);
        System.out.print("Enter Total Amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();

        Customer customer = new Customer(customerId, "", "", "", "", "");
        Order newOrder = new Order(orderID, customer, orderDate, totalAmount);
        try {
            orderDao.addOrder(newOrder);
            System.out.println("Order added successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    private static void viewOrderById(Scanner scanner, OrderDao orderDao) {
        System.out.print("Enter Order ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Order order = orderDao.getOrderById(id);
            if (order != null) {
                System.out.println("Order Details: " + order.toString());
            } else {
                System.out.println("Order not found.");
            }
        } catch (OrderNotFoundException e) {
            System.out.println("Order not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error retrieving order: " + e.getMessage());
        }
    }

    private static void viewAllOrders(OrderDao orderDao) {
        try {
            List<Order> orders = orderDao.getAllOrders();
            System.out.println("\nOrder List:");
            for (Order o : orders) {
                System.out.println(o.toString());
            }
        } catch (DataAccessException e) {
            System.out.println("Error retrieving all orders: " + e.getMessage());
        }
    }

    private static void updateOrder(Scanner scanner, OrderDao orderDao) {
        System.out.print("Enter Order ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Order existingOrder = orderDao.getOrderById(updateId);
            if (existingOrder != null) {
                System.out.print("Enter new Total Amount: ");
                double newTotalAmount = scanner.nextDouble();
                scanner.nextLine();
                existingOrder.setTotalAmount(newTotalAmount);
                orderDao.updateOrder(existingOrder);
                System.out.println("Order updated successfully!");
            } else {
                System.out.println("Order not found.");
            }
        } catch (OrderNotFoundException e) {
            System.out.println("Order not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error updating order: " + e.getMessage());
        }
    }

    private static void deleteOrder(Scanner scanner, OrderDao orderDao) {
        System.out.print("Enter Order ID to delete: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            orderDao.deleteOrder(deleteId);
            System.out.println("Order deleted successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }

 // Order Detail Management
    private static void manageOrderDetails(Scanner scanner, OrderDetailDao orderDetailDao) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Order Detail Management ---");
            System.out.println("1. Add Order Detail");
            System.out.println("2. View Order Detail by ID");
            System.out.println("3. View All Order Details");
            System.out.println("4. Update Order Detail");
            System.out.println("5. Delete Order Detail");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addOrderDetail(scanner, orderDetailDao);
                    break;
                case 2:
                    viewOrderDetailById(scanner, orderDetailDao);
                    break;
                case 3:
                    viewAllOrderDetails(orderDetailDao);
                    break;
                case 4:
                    updateOrderDetail(scanner, orderDetailDao);
                    break;
                case 5:
                    deleteOrderDetail(scanner, orderDetailDao);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addOrderDetail(Scanner scanner, OrderDetailDao orderDetailDao) {
        System.out.print("Enter orderDetailID: ");
        int orderDetailID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Order order = new Order(orderId, null, new Date(), 0.0); // Placeholder for Order object
        Product product = new Product(productId, "", "", 0.0, true); // Placeholder for Product object
        OrderDetail newOrderDetail = new OrderDetail(orderDetailID, order, product, quantity);

        try {
            orderDetailDao.addOrderDetail(newOrderDetail);
            System.out.println("Order Detail added successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error adding order detail: " + e.getMessage());
        }
    }

    private static void viewOrderDetailById(Scanner scanner, OrderDetailDao orderDetailDao) {
        System.out.print("Enter Order Detail ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            OrderDetail orderDetail = orderDetailDao.getOrderDetailById(id);
            if (orderDetail != null) {
                System.out.println("Order Detail Details: " + orderDetail.toString());
            } else {
                System.out.println("Order Detail not found.");
            }
        } catch (OrderDetailNotFoundException e) {
            System.out.println("Order Detail not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error retrieving order detail: " + e.getMessage());
        }
    }

    private static void viewAllOrderDetails(OrderDetailDao orderDetailDao) {
        try {
            List<OrderDetail> orderDetails = orderDetailDao.getAllOrderDetails();
            System.out.println("\nOrder Detail List:");
            for (OrderDetail od : orderDetails) {
                System.out.println(od.toString());
            }
        } catch (DataAccessException e) {
            System.out.println("Error retrieving all order details: " + e.getMessage());
        }
    }

    private static void updateOrderDetail(Scanner scanner, OrderDetailDao orderDetailDao) {
        System.out.print("Enter Order Detail ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            OrderDetail existingOrderDetail = orderDetailDao.getOrderDetailById(updateId);
            if (existingOrderDetail != null) {
                System.out.print("Enter new Quantity: ");
                int newQuantity = scanner.nextInt();
                scanner.nextLine();
                existingOrderDetail.setQuantity(newQuantity);
                orderDetailDao.updateOrderDetail(existingOrderDetail);
                System.out.println("Order Detail updated successfully!");
            } else {
                System.out.println("Order Detail not found.");
            }
        } catch (OrderDetailNotFoundException e) {
            System.out.println("Order Detail not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error updating order detail: " + e.getMessage());
        }
    }

    private static void deleteOrderDetail(Scanner scanner, OrderDetailDao orderDetailDao) {
        System.out.print("Enter Order Detail ID to delete: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            orderDetailDao.deleteOrderDetail(deleteId);
            System.out.println("Order Detail deleted successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error deleting order detail: " + e.getMessage());
        }
    }

    // Inventory Management
    private static void manageInventory(Scanner scanner, InventoryDao inventoryDao) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add Inventory");
            System.out.println("2. View Inventory by ID");
            System.out.println("3. View All Inventory");
            System.out.println("4. Update Inventory");
            System.out.println("5. Delete Inventory");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addInventory(scanner, inventoryDao);
                    break;
                case 2:
                    viewInventoryById(scanner, inventoryDao);
                    break;
                case 3:
                    viewAllInventory(inventoryDao);
                    break;
                case 4:
                    updateInventory(scanner, inventoryDao);
                    break;
                case 5:
                    deleteInventory(scanner, inventoryDao);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addInventory(Scanner scanner, InventoryDao inventoryDao) {
        System.out.print("Enter inventoryID: ");
        int inventoryID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Quantity In Stock: ");
        int quantityInStock = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(productId, "", "", 0.0, true); // Placeholder for Product object
        Inventory newInventory = new Inventory(inventoryID, product, quantityInStock, new Date());

        try {
            inventoryDao.addInventory(newInventory);
            System.out.println("Inventory added successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error adding inventory: " + e.getMessage());
        }
    }

    private static void viewInventoryById(Scanner scanner, InventoryDao inventoryDao) {
        System.out.print("Enter Inventory ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Inventory inventory = inventoryDao.getInventoryById(id);
            if (inventory != null) {
                System.out.println("Inventory Details: " + inventory.toString());
            } else {
                System.out.println("Inventory not found.");
            }
        } catch (InventoryNotFoundException e) {
            System.out.println("Inventory not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error retrieving inventory: " + e.getMessage());
        }
    }

    private static void viewAllInventory(InventoryDao inventoryDao) {
        try {
            List<Inventory> inventories = inventoryDao.getAllInventories();
            System.out.println("\nInventory List:");
            for (Inventory i : inventories) {
                System.out.println(i.toString());
            }
        } catch (DataAccessException e) {
            System.out.println("Error retrieving all inventories: " + e.getMessage());
        }
    }

    private static void updateInventory(Scanner scanner, InventoryDao inventoryDao) {
        System.out.print("Enter Inventory ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Inventory existingInventory = inventoryDao.getInventoryById(updateId);
            if (existingInventory != null) {
                System.out.print("Enter new Quantity In Stock: ");
                int newQuantityInStock = scanner.nextInt();
                scanner.nextLine();
                existingInventory.setQuantityInStock(newQuantityInStock);
                inventoryDao.updateInventory(existingInventory);
                System.out.println("Inventory updated successfully!");
            } else {
                System.out.println("Inventory not found.");
            }
        } catch (InventoryNotFoundException e) {
            System.out.println("Inventory not found: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error updating inventory: " + e.getMessage());
        }
    }

    private static void deleteInventory(Scanner scanner, InventoryDao inventoryDao) {
        System.out.print("Enter Inventory ID to delete: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            inventoryDao.deleteInventory(deleteId);
            System.out.println("Inventory deleted successfully!");
        } catch (DataAccessException e) {
            System.out.println("Error deleting inventory: " + e.getMessage());
        }
    }
}