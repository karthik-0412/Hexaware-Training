-- Task 1

create database TechShop;
use TechShop;
create table customer(customerId int primary key, firstName varchar(30),lastName varchar(30),
 email varchar(20), Phone BigInt, Address varchar(255));
create table products(productId int primary key, productName varchar(30), 
description varchar(255), price decimal(10,2));
create table orders(orderId int primary key, customerId int, orderDate Date, TotalAmount 
decimal(10,2), foreign key(customerId) references customer(customerId));
create table orderDetails(orderDetialId int primary key, orderId int, productId int,
Quantity int, foreign key(orderId) references orders(orderId), foreign key(productId) 
references products(productId));
create table Inventory(InventoryId int primary key, productId int, QuantityInStock int, 
LastStockupdate date, foreign key(productId) references products(productId));

INSERT INTO customer (customerId, firstName, lastName, email, Phone, Address) VALUES
(1, 'John', 'Doe', 'john@example.com', 9876543210, '123 Main St'),
(2, 'Jane', 'Smith', 'jane@example.com', 9876543211, '456 Elm St'),
(3, 'Robert', 'Johnson', 'robert@example.com', 9876543212, '789 Oak St'),
(4, 'Emily', 'Davis', 'emily@example.com', 9876543213, '321 Pine St'),
(5, 'Michael', 'Brown', 'michael@example.com', 9876543214, '654 Maple St'),
(6, 'Sarah', 'Wilson', 'sarah@example.com', 9876543215, '987 Birch St'),
(7, 'David', 'Taylor', 'david@example.com', 9876543216, '741 Cedar St'),
(8, 'Laura', 'Anderson', 'laura@example.com', 9876543217, '852 Willow St'),
(9, 'James', 'Thomas', 'james@example.com', 9876543218, '963 Walnut St'),
(10, 'Anna', 'Martinez', 'anna@example.com', 9876543219, '159 Cherry St');

select * from customer;

INSERT INTO products (productId, productName, description, price) VALUES
(1, 'Laptop', 'High-performance laptop', 1200.00),
(2, 'Smartphone', 'Latest model smartphone', 800.00),
(3, 'Tablet', '10-inch display tablet', 450.00),
(4, 'Monitor', '27-inch 4K monitor', 300.00),
(5, 'Keyboard', 'Mechanical keyboard', 120.00),
(6, 'Mouse', 'Wireless ergonomic mouse', 60.00),
(7, 'Headphones', 'Noise-canceling headphones', 200.00),
(8, 'Smartwatch', 'Fitness tracking smartwatch', 150.00),
(9, 'Printer', 'Laserjet wireless printer', 250.00),
(10, 'External HDD', '2TB portable hard drive', 100.00);

INSERT INTO orders (orderId, customerId, orderDate, TotalAmount) VALUES
(1, 1, '2025-03-01', 1200.00),
(2, 2, '2025-03-02', 800.00),
(3, 3, '2025-03-03', 450.00),
(4, 4, '2025-03-04', 300.00),
(5, 5, '2025-03-05', 120.00),
(6, 6, '2025-03-06', 60.00),
(7, 7, '2025-03-07', 200.00),
(8, 8, '2025-03-08', 150.00),
(9, 9, '2025-03-09', 250.00),
(10, 10, '2025-03-10', 100.00);

INSERT INTO orderDetails (orderDetialId, orderId, productId, Quantity) VALUES
(1, 1, 1, 1),
(2, 2, 2, 1),
(3, 3, 3, 1),
(4, 4, 4, 1),
(5, 5, 5, 2),
(6, 6, 6, 3),
(7, 7, 7, 1),
(8, 8, 8, 2),
(9, 9, 9, 1),
(10, 10, 10, 2);

INSERT INTO Inventory (InventoryId, productId, QuantityInStock, LastStockupdate) VALUES
(1, 1, 50, '2025-03-01'),
(2, 2, 100, '2025-03-02'),
(3, 3, 75, '2025-03-03'),
(4, 4, 40, '2025-03-04'),
(5, 5, 150, '2025-03-05'),
(6, 6, 200, '2025-03-06'),
(7, 7, 80, '2025-03-07'),
(8, 8, 60, '2025-03-08'),
(9, 9, 90, '2025-03-09'),
(10, 10, 110, '2025-03-10');


-- Task 2

select concat(firstName," ", lastName) as Names, email from customer;

ALTER TABLE customer RENAME TO customers;

SELECT Orders.OrderID, Orders.OrderDate, Customers.FirstName, Customers.LastName 
FROM Orders
JOIN Customers ON Orders.CustomerID = Customers.CustomerID;

INSERT INTO Customers (CustomerId,FirstName, LastName, Email, Phone, Address) 
VALUES (11,'John', 'Doe', 'johndoe@example.com', '1234567890', '123 Main St, City, Country');

UPDATE Products 
SET Price = Price * 1.10;

DELETE FROM OrderDetails WHERE OrderID = 5;
DELETE FROM Orders WHERE OrderID = 3;

INSERT INTO Orders (orderId,CustomerID, OrderDate, TotalAmount) 
VALUES (11,2, CURRENT_DATE, 12);

UPDATE Customers 
SET Email = 'emily@gmail.com' , Phone = '9876516789', Address = '1234 park avenue' 
WHERE CustomerID = 4;

UPDATE Orders 
SET TotalAmount = (
    SELECT SUM(od.Quantity * p.Price) 
    FROM OrderDetails od
    JOIN Products p ON od.ProductID = p.ProductID
    WHERE od.OrderID = Orders.OrderID
)
WHERE EXISTS (
    SELECT 1 
    FROM OrderDetails 
    WHERE OrderDetails.OrderID = Orders.OrderID
);

DELETE FROM OrderDetails 
WHERE OrderID IN (SELECT OrderID FROM Orders WHERE CustomerID = 3);

DELETE FROM Orders WHERE CustomerID = 5;

INSERT INTO Products (productId,ProductName, Description, Price) 
VALUES (11,'Smartphone X', 'Latest model with advanced features', 799.99);

ALTER TABLE Orders ADD COLUMN Status VARCHAR(50); -- (Only needed if Status column does not exist)

UPDATE Orders 
SET Status = 'In progress' 
WHERE OrderID = 7;

ALTER TABLE Customers ADD COLUMN OrderCount INT DEFAULT 0; -- (Only needed if OrderCount column does not exist)

SET SQL_SAFE_UPDATES = 0;
UPDATE Customers 
SET OrderCount = (
    SELECT COUNT(*) 
    FROM Orders 
    WHERE Orders.CustomerID = Customers.CustomerID
);

-- Task 3

SELECT o.OrderID, o.OrderDate, o.TotalAmount, 
       c.FirstName, c.LastName, c.Email, c.Phone, c.Address 
FROM Orders o
JOIN Customers c ON o.CustomerID = c.CustomerID;

SELECT p.ProductName, SUM(od.Quantity * p.Price) AS TotalRevenue
FROM OrderDetails od
JOIN Products p ON od.ProductID = p.ProductID
WHERE p.Description LIKE '%Laptop%' -- Assuming electronic gadgets are identified in the description
GROUP BY p.ProductName
ORDER BY TotalRevenue DESC;

SELECT DISTINCT c.CustomerID, c.FirstName, c.LastName, c.Email, c.Phone, c.Address
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID;

SELECT p.ProductName, SUM(od.Quantity) AS TotalQuantityOrdered
FROM OrderDetails od
JOIN Products p ON od.ProductID = p.ProductID
WHERE p.Description LIKE '%Smart%'
GROUP BY p.ProductName
ORDER BY TotalQuantityOrdered DESC
LIMIT 1;

SELECT p.ProductName, p.Description 
FROM Products p
WHERE p.Description LIKE '%Phone%';

SELECT c.CustomerID, c.FirstName, c.LastName, AVG(o.TotalAmount) AS AvgOrderValue
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.FirstName, c.LastName
ORDER BY AvgOrderValue DESC;

SELECT o.OrderID, o.TotalAmount, 
       c.FirstName, c.LastName, c.Email, c.Phone, c.Address
FROM Orders o
JOIN Customers c ON o.CustomerID = c.CustomerID
ORDER BY o.TotalAmount DESC
LIMIT 1;

SELECT p.ProductName, COUNT(od.OrderID) AS OrderCount
FROM OrderDetails od
JOIN Products p ON od.ProductID = p.ProductID
WHERE p.Description LIKE '%ph%'
GROUP BY p.ProductName
ORDER BY OrderCount DESC;

SELECT DISTINCT c.CustomerID, c.FirstName, c.LastName, c.Email, c.Phone, c.Address
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
JOIN OrderDetails od ON o.OrderID = od.OrderID
JOIN Products p ON od.ProductID = p.ProductID
WHERE p.ProductName = 'Laptop';  

SELECT SUM(TotalAmount) AS TotalRevenue
FROM Orders
WHERE OrderDate BETWEEN '2025-03-02' AND '2025-03-05';  

-- Task 4

use techshop;

SELECT c.CustomerID, c.FirstName, c.LastName, c.Email
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID
WHERE o.OrderID IS NULL;

SELECT COUNT(*) AS total_products
FROM Products;

SELECT SUM(o.TotalAmount) AS total_revenue
FROM Orders o;

select * from products;

SELECT AVG(od.Quantity) AS avg_quantity_ordered
FROM OrderDetails od
JOIN Products p ON od.ProductID = p.ProductID
WHERE p.Description = 'High-performance laptop';

SELECT c.CustomerID, c.FirstName, c.LastName, SUM(o.TotalAmount) AS total_spent
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
WHERE c.CustomerID = 6
GROUP BY c.CustomerID, c.FirstName, c.LastName;

SELECT c.CustomerID, c.FirstName, c.LastName, COUNT(o.OrderID) AS order_count
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.FirstName, c.LastName
ORDER BY order_count DESC
LIMIT 1;

SELECT p.Description AS category, SUM(od.Quantity) AS total_quantity_ordered
FROM OrderDetails od
JOIN Products p ON od.ProductID = p.ProductID
GROUP BY p.Description
ORDER BY total_quantity_ordered DESC
LIMIT 1;

select * from products;

SELECT c.CustomerID, c.FirstName, c.LastName, SUM(od.Quantity * p.Price) AS total_spent
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
JOIN OrderDetails od ON o.OrderID = od.OrderID
JOIN Products p ON od.ProductID = p.ProductID
WHERE p.Description = 'Latest model smartphone'
GROUP BY c.CustomerID, c.FirstName, c.LastName
ORDER BY total_spent DESC
LIMIT 1;

SELECT AVG(o.TotalAmount) AS avg_order_value
FROM Orders o;

SELECT c.CustomerID, c.FirstName, c.LastName, COUNT(o.OrderID) AS total_orders
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.FirstName, c.LastName;


