	-- create database carrentaldb
    create database if not exists carrentaldb;
    -- set database as carrentaldb 
    use carrentaldb;
    
    -- create table vehicle if not exists 
    create table if not exists vehicle(
    vehicleid int primary key auto_increment,
    make varchar(50),
    model varchar(50),
    year int,
    dailyrate decimal(10,2),
    status enum('available','notAvailable'),
    passengercapacity int,
    enginecapacity int);
    
     -- create table customer if not exists 
    create table if not exists customer(
    customerid int primary key auto_increment,
    firstname varchar(30),
    lastname varchar(30),
    email varchar(100) unique,
    phoneNumber varchar(20) unique
    );
    
     -- create table lease if not exists 
    create table if not exists lease(
    leaseid int primary key auto_increment,
    vehicleid int,
    customerid int,
    startDate date,
    enddate date,
    leasetype enum('Daily','Monthly'),
    foreign key (customerid) references customer(customerid) on delete cascade,
    foreign key (vehicleid) references vehicle(vehicleid) on delete cascade);
    
     -- create table payment if not exists 
    create table if not exists payment(
    paymentid int primary key auto_increment,
    leaseid int,
    paymentdate date,
    amount decimal(10,2),
    foreign key (leaseid) references lease(leaseid) on delete cascade);
-- insert the values in vehicle
insert into Vehicle values
(1, 'Toyota', 'Camry', 2022, 50.00, 'available', 4, 1450),
(2, 'Honda', 'Civic', 2023, 45.00, 'available', 7, 1500),
(3, 'Ford', 'Focus', 2022, 48.00, 'notAvailable', 4, 1400),
(4, 'Nissan', 'Altima', 2023, 52.00, 'available', 7, 1200),
(5, 'Chevrolet', 'Malibu', 2022, 47.00, 'available', 4, 1800),
(6, 'Hyundai', 'Sonata', 2023, 49.00, 'notAvailable', 7, 1400),
(7, 'BMW', '3 Series', 2023, 60.00, 'available', 7, 2499),
(8, 'Mercedes', 'C-Class', 2022, 58.00, 'available', 8, 2599),
(9, 'Audi', 'A4', 2022, 55.00, 'notAvailable', 4, 2500),
(10, 'Lexus', 'ES', 2023, 54.00, 'available', 4, 2500);

-- insert the values in customer
insert into Customer values
(1, 'John', 'Doe', 'johndoe@example.com', '555-555-5555'),
(2, 'Jane', 'Smith', 'janesmith@example.com', '555-123-4567'),
(3, 'Robert', 'Johnson', 'robert@example.com', '555-789-1234'),
(4, 'Sarah', 'Brown', 'sarah@example.com', '555-456-7890'),
(5, 'David', 'Lee', 'david@example.com', '555-987-6543'),
(6, 'Laura', 'Hall', 'laura@example.com', '555-234-5678'),
(7, 'Michael', 'Davis', 'michael@example.com', '555-876-5432'),
(8, 'Emma', 'Wilson', 'emma@example.com', '555-432-1098'),
(9, 'William', 'Taylor', 'william@example.com', '555-321-6547'),
(10, 'Olivia', 'Adams', 'olivia@example.com', '555-765-4321');

-- insert the values in lease
insert into lease values
(1, 1, 1, '2023-01-01', '2023-01-05', 'Daily'),
(2, 2, 2, '2023-02-15', '2023-02-28', 'Monthly'),
(3, 3, 3, '2023-03-10', '2023-03-15', 'Daily'),
(4, 4, 4, '2023-04-20', '2023-04-30', 'Monthly'),
(5, 5, 5, '2023-05-05', '2023-05-10', 'Daily'),
(6, 4, 3, '2023-06-15', '2023-06-30', 'Monthly'),
(7, 7, 7, '2023-07-01', '2023-07-10', 'Daily'),
(8, 8, 8, '2023-08-12', '2023-08-15', 'Monthly'),
(9, 3, 3, '2023-09-07', '2023-09-10', 'Daily'),
(10, 10, 10, '2023-10-10', '2023-10-31', 'Monthly');

-- insert the values in payment
insert into payment values
(1, 1, '2023-01-03', 200.00),
(2, 2, '2023-02-20', 1000.00),
(3, 3, '2023-03-12', 75.00),
(4, 4, '2023-04-25', 900.00),
(5, 5, '2023-05-07', 60.00),
(6, 6, '2023-06-18', 1200.00),
(7, 7, '2023-07-03', 40.00),
(8, 8, '2023-08-14', 1100.00),
(9, 9, '2023-09-09', 80.00),
(10, 10, '2023-10-25', 1500.00);

 -- for the type safety we use this command to update ans after updation we set as 1.
 SET SQL_SAFE_UPDATES = 0;

-- 1. Update the daily rate for a Mercedes car to 68.
update vehicle 
set dailyrate = 68
where make = 'Mercedes';

 SET SQL_SAFE_UPDATES = 1;

-- Altering the table to perform the delete cascade command lease and payment table is altered
alter table lease
drop foreign key lease_ibfk_1, 
add constraint fk_lease_vehicle foreign key (vehicleid) references vehicle(vehicleid) on delete cascade,
add constraint fk_lease_customer foreign key (customerid) references customer(customerid) on delete cascade;

alter table payment
drop foreign key payment_ibfk_1,
add constraint fk_payment_lease foreign key (leaseid) references lease(leaseid) on delete cascade;

-- 2. Delete a specific customer and all associated leases and payments.
delete from customer where customerid = 5;

-- 3. Rename the "paymentDate" column in the Payment table to "transactionDate".
alter table Payment
rename column paymentDate to transactionDate;

-- 4. Find a specific customer by email.
select * from Customer
where email = 'laura@example.com';

-- 5. Get active leases for a specific customer.
/* here curdate constriant is there so the date is given as the 2023 so curdate constraint will have a null values*/
select * from lease
where customerID = 4 and enddate >= curdate();

-- 6. Find all payments made by a customer with a specific phone number.
select p.* from payment p
join lease l on p.leaseid = l.leaseid
join customer c on l.customerid = c.customerid
where c.phonenumber = '555-876-5432';

-- 7. Calculate the average daily rate of all available cars.
select avg(dailyrate) as avgdailyrate
from vehicle
where status = 'available';


-- 8. Find the car with the highest daily rate.
select * from vehicle
order by dailyrate desc
limit 1;

-- 9. Retrieve all cars leased by a specific customer.
select v.* from vehicle v
join lease l on v.vehicleid = l.vehicleid
where l.customerid = 3;

-- 10. Find the details of the most recent lease.
select * from lease
order by startdate desc
limit 1;

-- 11. List all payments made in the year 2023.
select * from payment
where year(transactiondate) = 2023;

-- 12. Retrieve customers who have not made any payments.
select * from customer
where customerid not in (select distinct customerid from lease where leaseid in (select distinct leaseid from payment));


-- 13. Retrieve Car Details and Their Total Payments.
select v.*, sum(p.amount) as totalpayments
from vehicle v
join lease l on v.vehicleid = l.vehicleid
join payment p on l.leaseid = p.leaseid
group by v.vehicleid;

-- 14. Calculate Total Payments for Each Customer.
select c.*, sum(p.amount) as totalpayments
from customer c
join lease l on c.customerid = l.customerid
join payment p on l.leaseid = p.leaseid
group by c.customerid;


-- 15. List Car Details for Each Lease.
select l.leaseid, v.*
from lease l
join vehicle v on l.vehicleid = v.vehicleid;

-- 16. Retrieve Details of Active Leases with Customer and Car Information.
/* here curdate constriant is there so the date is given as the 2023 so curdate constraint will have a null values*/
select l.*, c.*, v.*
from lease l
join customer c on l.customerid = c.customerid
join vehicle v on l.vehicleid = v.vehicleid
where l.enddate >= curdate();

-- 17. Find the Customer Who Has Spent the Most on Leases.
select c.*, sum(p.amount) as totalspent
from customer c
join lease l on c.customerid = l.customerid
join payment p on l.leaseid = p.leaseid
group by c.customerid
order by totalspent desc
limit 1;


-- 18. List All Cars with Their Current Lease Information.
/* here curdate constriant is there so the date is given as the 2023 so curdate constraint will have a null values*/
select v.*, l.*
from vehicle v
left join lease l on v.vehicleid = l.vehicleid
where l.enddate is null or l.enddate >= curdate();
