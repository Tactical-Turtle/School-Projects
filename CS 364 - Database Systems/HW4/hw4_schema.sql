


DROP TABLE OrderLine_T	 	CASCADE CONSTRAINTS ;
DROP TABLE Product_T 		CASCADE CONSTRAINTS ;
DROP TABLE Order_T 		CASCADE CONSTRAINTS ;
DROP TABLE Employee_T 		CASCADE CONSTRAINTS ;
DROP TABLE Customer_T 		CASCADE CONSTRAINTS ;



CREATE TABLE Customer_T
             (CustomerID          NUMBER(11,0)     NOT NULL,
	      CustomerName        VARCHAR2(25)     NOT NULL,
	      CustomerAddress     VARCHAR2(30)    ,
              CustomerCity        VARCHAR2(20)    ,              
              CustomerState       CHAR(2)         ,
              CustomerPostalCode  VARCHAR2(10)    ,
CONSTRAINT Customer_PK PRIMARY KEY (CustomerID));



CREATE TABLE Employee_T
             (EmployeeID          VARCHAR2(20)    NOT NULL,
              EmployeeName        VARCHAR2(25)    ,
              EmployeeAddress     VARCHAR2(30)    ,
              EmployeeBirthDate   DATE            ,
              EmployeeCity        VARCHAR2(20)    ,
              EmployeeState       CHAR(2)         ,
              EmployeeZipCode     VARCHAR2(10)    ,
              EmployeeDateHired   DATE            ,
              EmployeeSupervisor  VARCHAR2(10)    ,
CONSTRAINT Employee_PK PRIMARY KEY (EmployeeID));



CREATE TABLE Order_T
             (OrderID             NUMBER(11,0)    NOT NULL,
	         CustomerID          NUMBER(11,0)   ,
              OrderDate           DATE DEFAULT SYSDATE          ,
CONSTRAINT Order_PK PRIMARY KEY (OrderID),
CONSTRAINT Order_FK1 FOREIGN KEY (CustomerID) REFERENCES Customer_T(CustomerID));

CREATE TABLE Product_T
             (ProductID           NUMBER(11,0)    NOT NULL,
              ProductLineID       NUMBER(11,0)   ,
              ProductDescription  VARCHAR2(50)    ,
              ProductFinish       VARCHAR2(20)    ,
              ProductStandardPrice DECIMAL(6,2)   ,
CONSTRAINT Product_PK PRIMARY KEY (ProductID));

CREATE TABLE OrderLine_T
	      (OrderID            NUMBER(11,0)   NOT NULL,
              ProductID           NUMBER(11,0)   NOT NULL,
              OrderedQuantity     NUMBER(11,0)  ,
CONSTRAINT OrderLine_PK PRIMARY KEY (OrderID, ProductID),
CONSTRAINT OrderLine_FK1 FOREIGN KEY (OrderID) REFERENCES Order_T(OrderID),
CONSTRAINT OrderLine_FK2 FOREIGN KEY (ProductID) REFERENCES Product_T(ProductID));


