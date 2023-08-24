DB: Mysql 
Dependencies :
  Mysql JDBC Connector
  Jcalendar, etc
  

create database library_ms;

>> table book_details
create table book_details(book_id int not null auto_increment,book_name varchar(100),author varchar(100),quantity int,primary key(book_id));

>> table issue_book_details
create table issue_book_details(ID int not null auto_increment,book_id int,book_name varchar(150),student_id int,student_name varchar(50),issue_date date,due_date date,status varchar(10),primary key(ID));

>>table student_details
create table student_details(StudentID int not null auto_increment,Name varchar(50),Course varchar(30),Branch varchar(30),primary key(StudentID));

>>table user
create table user(id int not null auto_increment,Name varchar(30),Password varchar(30),Email varchar(50),Contact varchar(10),primary key(id));
