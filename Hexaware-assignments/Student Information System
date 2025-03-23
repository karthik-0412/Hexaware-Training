-- TASK 1

create database sisdb;

use sisdb;

create table student(studentId int primary key, firstName varchar(30), lastName varchar(30), dateofBirth date, email varchar(30),phoneNumber bigint);
create table teacher(teacherId int primary Key, firstName varchar(30), lastName varchar(30), email varchar(30));
create table courses(courseId int primary key, courseName varchar(30), credits int, teacherId int, Foreign key 
(teacherId) References teacher(teacherId));
create table enrollments(enrolllmentId int primary key, studentId int, courseId int, enrollmentDate date, foreign key (studentId) 
references student(studentId), foreign key (courseId) references courses(courseId));

create table payments(paymentId int primary key, studentId int, amount decimal
(10,2), payment_date date, foreign key (studentId) references student(studentId));

 INSERT INTO student (studentId, firstName, lastName, dateofBirth, email, phoneNumber) VALUES
(1, 'Alice', 'Johnson', '2001-05-15', 'alice@example.com', 9876543210),
(2, 'Bob', 'Smith', '2000-08-22', 'bob@example.com', 9876543211),
(3, 'Charlie', 'Davis', '1999-12-10', 'charlie@example.com', 9876543212),
(4, 'David', 'Williams', '2002-02-18', 'david@example.com', 9876543213),
(5, 'Ella', 'Brown', '2001-09-05', 'ella@example.com', 9876543214),
(6, 'Frank', 'Miller', '2000-06-30', 'frank@example.com', 9876543215),
(7, 'Grace', 'Wilson', '1998-11-25', 'grace@example.com', 9876543216),
(8, 'Hannah', 'Taylor', '2003-04-09', 'hannah@example.com', 9876543217),
(9, 'Ian', 'Moore', '1999-07-20', 'ian@example.com', 9876543218),
(10, 'Julia', 'Anderson', '2002-01-14', 'julia@example.com', 9876543219);

INSERT INTO teacher (teacherId, firstName, lastName, email) VALUES
(1, 'Mark', 'Robinson', 'mark@example.com'),
(2, 'Linda', 'Clark', 'linda@example.com'),
(3, 'James', 'Lewis', 'james@example.com'),
(4, 'Susan', 'Walker', 'susan@example.com'),
(5, 'Robert', 'Hall', 'robert@example.com'),
(6, 'Emily', 'Allen', 'emily@example.com'),
(7, 'Michael', 'Young', 'michael@example.com'),
(8, 'Jessica', 'Harris', 'jessica@example.com'),
(9, 'David', 'White', 'david@example.com'),
(10, 'Sarah', 'King', 'sarah@example.com');

INSERT INTO courses (courseId, courseName, credits, teacherId) VALUES
(1, 'Mathematics', 3, 1),
(2, 'Physics', 4, 2),
(3, 'Chemistry', 4, 3),
(4, 'Biology', 3, 4),
(5, 'English Literature', 3, 5),
(6, 'Computer Science', 4, 6),
(7, 'History', 3, 7),
(8, 'Psychology', 3, 8),
(9, 'Economics', 3, 9),
(10, 'Political Science', 3, 10);

INSERT INTO enrollments (enrolllmentId, studentId, courseId, enrollmentDate) VALUES
(1, 1, 1, '2025-03-01'),
(2, 2, 2, '2025-03-02'),
(3, 3, 3, '2025-03-03'),
(4, 4, 4, '2025-03-04'),
(5, 5, 5, '2025-03-05'),
(6, 6, 6, '2025-03-06'),
(7, 7, 7, '2025-03-07'),
(8, 8, 8, '2025-03-08'),
(9, 9, 9, '2025-03-09'),
(10, 10, 10, '2025-03-10');

INSERT INTO payments (paymentId, studentId, amount, payment_date) VALUES
(1, 1, 500.00, '2025-03-05'),
(2, 2, 600.00, '2025-03-06'),
(3, 3, 700.00, '2025-03-07'),
(4, 4, 450.00, '2025-03-08'),
(5, 5, 300.00, '2025-03-09'),
(6, 6, 750.00, '2025-03-10'),
(7, 7, 400.00, '2025-03-11'),
(8, 8, 500.00, '2025-03-12'),
(9, 9, 600.00, '2025-03-13'),
(10, 10, 550.00, '2025-03-14');

-- TASK 2

insert into student values(11, 'John','Doe', '1995-08-15', 'john.doe@example.com', 1234567890); 

insert into enrollments values(11, 11, 5, '2025-03-20');

update teacher set email = 'james@gmail.com' where teacherId = 3;

select * from enrollments;

delete from enrollments
where studentId = 11 and courseId = 5;

update courses set teacherId = 4 where courseId = 5;

SELECT CONSTRAINT_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'enrollments' AND TABLE_SCHEMA = DATABASE();

DELETE FROM enrollments
WHERE studentId = 3;

ALTER TABLE Enrollments
ADD CONSTRAINT fk_student
FOREIGN KEY (studentid) REFERENCES Student(studentid)
ON DELETE CASCADE;

ALTER TABLE Payments 
ADD CONSTRAINT payments_fk
FOREIGN KEY (studentId) REFERENCES Student(studentId) 
ON DELETE CASCADE;


-- SET SQL_SAFE_UPDATES = 0;
DELETE FROM student WHERE studentId = 5;
select * from enrollments;

select * from student;

UPDATE payments SET amount = 1200.50
WHERE paymentId = 9;

select * from payments;

-- TASK 3

SELECT s.StudentID, s.FirstName, s.LastName, SUM(p.Amount) AS TotalPayments
FROM Student s
JOIN Payments p ON s.StudentID = p.StudentID
WHERE s.StudentID = 7
GROUP BY s.StudentID, s.FirstName, s.LastName;

SELECT c.CourseID, c.CourseName, COUNT(e.StudentID) AS EnrollmentCount
FROM Courses c
LEFT JOIN Enrollments e ON c.CourseID = e.CourseID
GROUP BY c.CourseID, c.CourseName
ORDER BY EnrollmentCount DESC;

SELECT s.StudentID, s.FirstName, s.LastName
FROM Student s
LEFT JOIN Enrollments e ON s.StudentID = e.StudentID
WHERE e.StudentID IS NULL;

SELECT s.FirstName, s.LastName, c.CourseName
FROM Student s
JOIN Enrollments e ON s.StudentID = e.StudentID
JOIN Courses c ON e.CourseID = c.CourseID
ORDER BY s.LastName, s.FirstName;

SELECT t.TeacherID, t.FirstName, t.LastName, c.CourseName
FROM Teacher t
JOIN Courses c ON t.TeacherID = c.TeacherID
ORDER BY t.LastName, t.FirstName;

SELECT s.FirstName, s.LastName, e.EnrollmentDate
FROM Student s
JOIN Enrollments e ON s.StudentID = e.StudentID
JOIN Courses c ON e.CourseID = c.CourseID
WHERE c.CourseID = 4;  

SELECT s.StudentID, s.FirstName, s.LastName
FROM Student s
LEFT JOIN Payments p ON s.StudentID = p.StudentID
WHERE p.StudentID IS NULL;

SELECT c.CourseID, c.CourseName
FROM Courses c
LEFT JOIN Enrollments e ON c.CourseID = e.CourseID
WHERE e.CourseID IS NULL;

SELECT e1.StudentID, s.FirstName, s.LastName, COUNT(DISTINCT e1.CourseID) AS CourseCount
FROM Enrollments e1
JOIN Student s ON e1.StudentID = s.StudentID
GROUP BY e1.StudentID, s.FirstName, s.LastName
HAVING CourseCount > 1;

SELECT t.TeacherID, t.FirstName, t.LastName
FROM Teacher t
LEFT JOIN Courses c ON t.TeacherID = c.TeacherID
WHERE c.TeacherID IS NULL;


-- TASK 4


SELECT AVG(student_count) AS avg_students_per_course
FROM (
    SELECT courseId, COUNT(studentId) AS student_count
    FROM Enrollments
    GROUP BY courseId
) AS course_enrollments;

SELECT studentId, amount
FROM Payments
WHERE amount = (SELECT MAX(amount) FROM Payments);

SELECT courseId, COUNT(studentId) AS enrollment_count
FROM Enrollments
GROUP BY courseId
HAVING COUNT(studentId) = (
    SELECT MAX(course_count)
    FROM (SELECT COUNT(studentId) AS course_count FROM Enrollments GROUP BY courseId) AS max_enrollments
);

SELECT t.teacherId, t.firstname, SUM(p.amount) AS total_payments
FROM Teacher t
JOIN Courses c ON t.teacherId = c.teacherId
JOIN Enrollments e ON c.courseId = e.courseId
JOIN Payments p ON e.studentId = p.studentId
GROUP BY t.teacherId, t.firstname;

SELECT studentId 
FROM Enrollments 
GROUP BY studentId
HAVING COUNT(DISTINCT courseId) = (SELECT COUNT(*) FROM Courses);

SELECT teacherId, firstname
FROM Teacher
WHERE teacherId NOT IN (SELECT DISTINCT teacherId FROM Courses);

SELECT AVG(DATEDIFF(CURDATE(), dateOfBirth) / 365) AS average_age
FROM Student;

SELECT courseId, coursename
FROM Courses
WHERE courseId NOT IN (SELECT DISTINCT courseId FROM Enrollments);

SELECT e.studentId, e.courseId, SUM(p.amount) AS total_payment
FROM Enrollments e
JOIN Payments p ON e.studentId = p.studentId
GROUP BY e.studentId, e.courseId;

SELECT studentId, COUNT(*) AS payment_count
FROM Payments
GROUP BY studentId
HAVING COUNT(*) > 1;

SELECT s.studentId, s.firstname, SUM(p.amount) AS total_payments
FROM Student s
LEFT JOIN Payments p ON s.studentId = p.studentId
GROUP BY s.studentId, s.firstname;

SELECT c.coursename AS course_name, COUNT(e.studentId) AS student_count
FROM Courses c
LEFT JOIN Enrollments e ON c.courseId = e.courseId
GROUP BY c.courseId, c.coursename;

SELECT s.studentId, s.firstname, AVG(p.amount) AS average_payment
FROM Student s
JOIN Payments p ON s.studentId = p.studentId
GROUP BY s.studentId, s.firstname;
