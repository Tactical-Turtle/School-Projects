SET LINESIZE 120
SET PAGESIZE 80

/* Drop all tables before creating tables                */
DROP TABLE OrderLine_T 	          CASCADE CONSTRAINTS ;
DROP TABLE Order_T 		            CASCADE CONSTRAINTS ;
DROP TABLE CustomerShipAddress_T  CASCADE CONSTRAINTS ;
DROP TABLE Product_T 		          CASCADE CONSTRAINTS ;
DROP TABLE ProductLine_T 	        CASCADE CONSTRAINTS ;
DROP TABLE WorkCenter_T 	        CASCADE CONSTRAINTS ;
DROP TABLE Employee_T 		        CASCADE CONSTRAINTS ;
DROP TABLE Salesperson_T 	        CASCADE CONSTRAINTS ;
DROP TABLE Territory_T 		        CASCADE CONSTRAINTS ;
DROP TABLE Customer_T 		        CASCADE CONSTRAINTS ;

CREATE TABLE WorkCenter_T
	(WorkCenterID       VARCHAR(12)    NOT NULL,
	 WorkCenterLocation  VARCHAR(30)           ,
	 CONSTRAINT WorkCenter_PK PRIMARY KEY (WorkCenterID));


CREATE TABLE Employee_T
	(EmployeeID         VARCHAR(10)    NOT NULL,
     EmployeeName       VARCHAR(25)    ,
     EmployeeAddress    VARCHAR(30)    ,
     EmployeeCity       VARCHAR(20)    ,
	 EmployeeState      CHAR(2)        ,
     EmployeeZip        VARCHAR(10)    ,
	 EmployeeBirthDate  DATE           ,
     EmployeeDateHired  DATE           ,
	 EmployeeSupervisor VARCHAR(10)    ,
     CONSTRAINT Employee_PK PRIMARY KEY (EmployeeID));

CREATE TABLE Customer_T
	(CustomerID         NUMBER(4)          NOT NULL,
	 CustomerName       VARCHAR(25)    ,
	 CustomerAddress    VARCHAR(30)    ,
     CustomerCity       VARCHAR(20)    ,              
     CustomerState      CHAR(2)        ,
     CustomerPostalCode VARCHAR(10)    ,
     CONSTRAINT Customer_PK PRIMARY KEY (CustomerID));

CREATE TABLE ProductLine_T
	(ProductLineID     NUMBER(4)         NOT NULL,
	 ProductLineName    VARCHAR(50)               ,
     CONSTRAINT ProductLine_PK PRIMARY KEY (ProductLineID));

CREATE TABLE Product_T
	(ProductID            NUMBER(4)         NOT NULL,
     ProductLineID        NUMBER(4)      ,
     ProductDescription   VARCHAR(50)    ,
     ProductFinish        VARCHAR(20)    ,
     ProductStandardPrice NUMBER(6,2)    ,
	 ProductOnHand	      NUMBER(6)      ,
     CONSTRAINT Product_PK PRIMARY KEY (ProductID),
     CONSTRAINT Product_FK1 FOREIGN KEY (ProductLineID) 
	 REFERENCES ProductLine_T(ProductLineID));

CREATE TABLE Territory_T
    (TerritoryID        NUMBER(4)         NOT NULL,
     TerritoryName      VARCHAR(50)    ,
     CONSTRAINT Territory_PK PRIMARY KEY (TerritoryID));

CREATE TABLE Salesperson_T
	(SalespersonID        NUMBER(4)          NOT NULL,              
     SalespersonName       VARCHAR(25)    , /* Fixed */
     SalespersonTelephone VARCHAR(50)    ,
     SalespersonFax       VARCHAR(50)    ,
	 SalespersonAddress   VARCHAR(30)    ,
	 SalespersonCity      VARCHAR(20)    ,
	 SalespersonState     CHAR(2)        ,
	 SalespersonZip       VARCHAR(20)    ,
     SalesTerritoryID     NUMBER(4)      ,
     CONSTRAINT Salesperson_PK PRIMARY KEY (SalespersonID),
     CONSTRAINT Salesperson_FK1 FOREIGN KEY (SalesTerritoryID) /*Fixed*/
	 REFERENCES Territory_T(TerritoryID));
	
CREATE TABLE CustomerShipAddress_T
	(ShipAddressID	NUMBER(4)	NOT NULL,
	 CustomerID	NUMBER(4)	NOT NULL,
	 TerritoryID	NUMBER(4)	NOT NULL,
	 ShipAddress	VARCHAR(30)	,
	 ShipCity	VARCHAR(20)	,
	 ShipState	CHAR(2)     	,
	 ShipZip	VARCHAR(10)	,
	 ShipDirections	VARCHAR(100)	,
     CONSTRAINT CSA_PK PRIMARY KEY (ShipAddressID),
     CONSTRAINT CSA_FK1 FOREIGN KEY (CustomerID) 
	 REFERENCES Customer_T(CustomerID),
     CONSTRAINT CSA_FK2 FOREIGN KEY (TerritoryID)
	 REFERENCES Territory_T(TerritoryID));

	
CREATE TABLE Order_T
	(OrderID            NUMBER(5)        NOT NULL,
	 CustomerID         NUMBER(4)   ,
     OrderDate          DATE        ,
	 FulfillmentDate    DATE	,
	 SalespersonID	    NUMBER(4)	,
	 ShipAdrsID	        NUMBER(4)	,
	 CONSTRAINT Order_PK PRIMARY KEY (OrderID),
	 CONSTRAINT Order_FK1 FOREIGN KEY (CustomerID) 
	 REFERENCES Customer_T(CustomerID),
	 CONSTRAINT Order_FK2 FOREIGN KEY (SalespersonID)
	 REFERENCES Salesperson_T(SalespersonID),
	 CONSTRAINT Order_FK3 FOREIGN KEY (ShipAdrsID)
	 REFERENCES CustomerShipAddress_T(ShipAddressID));
	
CREATE TABLE OrderLine_T
	(OrderLineID	    NUMBER(4)	     NOT NULL,
	 OrderID            NUMBER(5)        NOT NULL,
     ProductID          NUMBER(4)        NOT NULL,
     OrderedQuantity    NUMBER(10)         ,
     CONSTRAINT OrderLine_PK PRIMARY KEY (OrderLineID),
	 CONSTRAINT OrderLine_FK1 FOREIGN KEY (OrderID) 
	 REFERENCES Order_T(OrderID),
	 CONSTRAINT OrderLine_FK2 FOREIGN KEY (ProductID) 
	 REFERENCES Product_T(ProductID));

	 
	 
INSERT INTO WorkCenter_T  (WorkCenterID, WorkCenterLocation)
VALUES  ('SM1', 'Main Saw Mill');

INSERT INTO WorkCenter_T  (WorkCenterID, WorkCenterLocation)
VALUES  ('WR1', 'Warehouse and Receiving');

INSERT INTO WorkCenter_T  (WorkCenterID, WorkCenterLocation)
VALUES  ('Tampa1', 'Tampa Warehouse');

Insert into Employee_T (EmployeeID,EmployeeName,EmployeeAddress,EmployeeCity,EmployeeState,EmployeeZip,EmployeeDateHired,EmployeeBirthDate,EmployeeSupervisor) values ('123-44-345','Phil Morris','2134 Hilltop Rd','Knoxville','TN',null,to_timestamp('12-JUN-04','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('05-JAN-57','DD-MON-RR HH.MI.SSXFF AM'),'454-56-768');
Insert into Employee_T (EmployeeID,EmployeeName,EmployeeAddress,EmployeeCity,EmployeeState,EmployeeZip,EmployeeDateHired,EmployeeBirthDate,EmployeeSupervisor) values ('332445667','Lawrence Haley','5970 Spring Crest Rd','Nashville','TN','54545',to_timestamp('05-JAN-04','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('15-AUG-63','DD-MON-RR HH.MI.SSXFF AM'),'454-56-768');
Insert into Employee_T (EmployeeID,EmployeeName,EmployeeAddress,EmployeeCity,EmployeeState,EmployeeZip,EmployeeDateHired,EmployeeBirthDate,EmployeeSupervisor) values ('454-56-768','Robert Lewis','17834 Deerfield Ln','Knoxville','TN','55555',to_timestamp('01-JAN-03','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('25-AUG-64','DD-MON-RR HH.MI.SSXFF AM'),'123-44-345');
Insert into Employee_T (EmployeeID,EmployeeName,EmployeeAddress,EmployeeCity,EmployeeState,EmployeeZip,EmployeeDateHired,EmployeeBirthDate,EmployeeSupervisor) values ('555955585','Mary Smith','75 Jane Lane','Clearwater','FL','33879',to_timestamp('15-AUG-05','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('06-MAY-69','DD-MON-RR HH.MI.SSXFF AM'),'332445667');
Insert into Employee_T (EmployeeID,EmployeeName,EmployeeAddress,EmployeeCity,EmployeeState,EmployeeZip,EmployeeDateHired,EmployeeBirthDate,EmployeeSupervisor) values ('Laura','Laura Ellenburg','5342 Picklied Trout Lane','Nashville','TN','38010',to_timestamp('22-FEB-05','DD-MON-RR HH.MI.SSXFF AM'),null,'454-56-768');

Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (1,'Contemporary Casuals','1355 S Hines Blvd','Gainesville','FL','32601-2871');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (2,'Value Furnitures','15145 S.W. 17th St.','Plano','TX','75094-7743');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (3,'Home Furnishings','1900 Allard Ave','Albany','NY','12209-1125');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (4,'Eastern Furniture','1925 Beltline Rd.','Carteret','NJ','07008-3188');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (5,'Impressions','5585 Westcott Ct.','Sacramento','CA','94206-4056');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (6,'Furniture Gallery','325 Flatiron Dr.','Boulder','CO','80514-4432');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (7,'New Furniture','Palace Ave','Farmington','NM',null);
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (8,'Dunkins Furniture','7700 Main St','Syracuse','NY','31590');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (9,'A Carpet','434 Abe Dr','Rome','NY','13440');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (12,'Flanigan Furniture','Snow Flake Rd','Ft Walton Beach','FL','32548');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (13,'Ikards','1011 S. Main St','Las Cruces','NM','88001');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (14,'Wild Bills','Four Horse Rd','Oak Brook','Il','60522');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (15,'Janet''s Collection','Janet Lane','Virginia Beach','VA','10012');
Insert into Customer_T (CustomerID,CustomerName,CustomerAddress,CustomerCity,CustomerState,CustomerPostalCode) values (16,'ABC Furniture Co.','152 Geramino Drive','Rome','NY','13440');

INSERT INTO ProductLine_T  (ProductLineID, ProductLineName)
VALUES  (1, 'Basic');
INSERT INTO ProductLine_T  (ProductLineID, ProductLineName)
VALUES  (2, 'Antique');
INSERT INTO ProductLine_T  (ProductLineID, ProductLineName)
VALUES  (3, 'Modern');
INSERT INTO ProductLine_T  (ProductLineID, ProductLineName)
VALUES  (4, 'Classical');
INSERT INTO ProductLine_T  (ProductLineID, ProductLineName)
VALUES  (5, 'Rellville');
INSERT INTO ProductLine_T  (ProductLineID, ProductLineName)
VALUES  (6, 'Spanish Style');
INSERT INTO ProductLine_T  (ProductLineID, ProductLineName)
VALUES  (7, 'Gothic');

	
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (1,'Cherry End Table','Cherry',175.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (2,'Birch Coffee Tables','Birch',200.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (3,'Oak Computer Desk','Oak',750.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (4,'Entertainment Center','Cherry',1650.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (5,'Writer''s Desk','Oak',325.00,0,2);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (6,'8-Drawer Dresser','Birch',750.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (7,'48 Bookcase','Walnut',150.00,0,3);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (8,'48 Bookcase','Oak',175.00,0,3);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (9,'96 Bookcase','Walnut',225.00,0,3);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (10,'96 Bookcase','Oak',200.00,0,3);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (11,'4-Drawer Dresser','Oak',500.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (12,'8-Drawer Dresser','Oak',800.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (13,'Nightstand','Cherry',150.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (14,'Writer''s Desk','Birch',300.00,0,2);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (17,'High Back Leather Chair','Leather',362.00,0,3);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (18,'6'' Grandfather Clock','Oak',890.00,0,4);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (19,'7'' Grandfather Clock','Oak',1100.00,0,4);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (20,'Amoire','Walnut',1200.00,0,2);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (21,'Pine End Table','Pine',256.00,0,1);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (24,null,null,0.00,0,5);
Insert into Product_T (ProductID,ProductDescription,ProductFinish,ProductStandardPrice,ProductOnHand,ProductLineID) 
values (25,null,null,0.00,0,2);


INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (1, 'SouthEast');
INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (2, 'SouthWest');
INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (3, 'NorthEast');
INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (4, 'NorthWest');
INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (5, 'Central');
INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (6, 'Alaska');
INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (12, 'Hawaii');
INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (13, 'Colorado');
INSERT INTO Territory_T  (TerritoryID, TerritoryName)
	VALUES  (15, 'Arizona');
	
	
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (1,'Doug Henny','8134445555',null,2,null,null,null,null);
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (2,'Robert Lewis','8139264006',null,13,'124 Deerfield','Lutz','FL','33549');
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (3,'William Strong','3153821212',null,3,'787 Syracuse Lane','Syracuse','NY','33240');
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (4,'Julie Dawson','4355346677',null,4,null,null,null,null);
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (5,'Jacob Winslow','2238973498',null,5,null,null,null,null);
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (6,'Pepe Lepue',null,null,13,null,'Platsburg','NY',null);
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (8,'Fred Flinstone',null,null,2,'1 Rock Lane','Bedrock','Ca','99999');
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (9,'Mary James','3035555454',null,4,'9 Red Line','Denver','CO','55555');
Insert into Salesperson_T (SalespersonID,SalespersonName,SalespersonTelephone,SalespersonFax,SalesTerritoryID,SalespersonAddress,SalespersonCity,SalespersonState,SalespersonZip) 
values (10,'Mary Smithson','4075555555',null,15,'4585 Maple Dr','Orlando','FL','32826');

Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (1,4,13,'35456 Trifly Road','Lutz','FL','33549',null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (2,4,13,'1925 Beltline Rd.','Carteret','NJ',null,null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (3,1,6,'321 Butterfly Terr','Columbus','OH',null,null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (4,1,6,'7744 121 Street','Colubus','OH',null,null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (9,8,15,'US Embassy','Mexico City','Me',null,null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (10,16,4,'1256 One Lane','San Diego','CA','55555-',null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (11,9,1,'17832 Rt 92','Mobil','AL','39889-',null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (13,14,5,'303 Drakes Landing','Manhattan','KS','66502-',null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (14,13,2,'3400 Amador Ave','Las Cruces','NM','88001-',null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (15,4,6,'657 10th st','Kodiak','AK','99878-',null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (17,9,5,'100 E. Fletch','Pocahatas','OH','39877-',null);
Insert into CustomerShipAddress_T (ShipAddressID,CustomerID,TerritoryID,ShipAddress,ShipCity,ShipState,ShipZip,ShipDirections) values (19,4,13,'655 Rocky Point','Denver','CO',null,null);


INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (1, to_Date('08/Sep/14', 'DD/MON/RR'), 4, to_date('25/Nov/14', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (2, to_date('04/Oct/14', 'DD/MON/RR'), 3, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (3, to_date('19/Jul/14', 'DD/MON/RR'), 1, to_date('', 'DD/MON/RR'), 2, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (4, to_date('01/Nov/14', 'DD/MON/RR'), 6, to_date('', 'DD/MON/RR'), 5, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (5, to_date('28/Jul/14', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (6, to_Date('27/Aug/14', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (7, to_date('16/Sep/14', 'DD/MON/RR'), 1, to_date('', 'DD/MON/RR'), 2, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (8, to_date('16/Sep/14', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (9, to_date('16/Sep/14', 'DD/MON/RR'), 6, to_date('', 'DD/MON/RR'), 5, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (19, to_date('05/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (20, to_date('06/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (21, to_date('06/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (22, to_date('06/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (23, to_date('06/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (24, to_date('10/Mar/15', 'DD/MON/RR'), 1, to_date('', 'DD/MON/RR'), 2, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (25, to_date('10/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (26, to_date('10/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (27, to_date('10/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (28, to_date('10/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (29, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (30, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, 4);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (31, to_date('11/Mar/15', 'DD/MON/RR'), 15, to_date('', 'DD/MON/RR'), 4, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (32, to_date('11/Mar/15', 'DD/MON/RR'), 15, to_date('', 'DD/MON/RR'), 4, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (34, to_date('11/Mar/15', 'DD/MON/RR'), 15, to_date('', 'DD/MON/RR'), 4, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (35, to_date('11/Mar/15', 'DD/MON/RR'), 8, to_date('', 'DD/MON/RR'), 5, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (36, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, 1);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (37, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (38, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, 1);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (39, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, 1);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (40, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (41, to_date('11/Mar/15', 'DD/MON/RR'), 12, to_date('', 'DD/MON/RR'), 6, NULL);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (42, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, NULL);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (43, to_date('11/Mar/15', 'DD/MON/RR'), 12, to_date('', 'DD/MON/RR'), 6, NULL);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (44, to_date('11/Mar/15', 'DD/MON/RR'), 6, to_date('', 'DD/MON/RR'), 9, NULL);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (45, to_date('11/Mar/15', 'DD/MON/RR'), 12, to_date('', 'DD/MON/RR'), 6, NULL);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (46, to_date('11/Mar/15', 'DD/MON/RR'), 1, to_date('', 'DD/MON/RR'), 2, NULL);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (47, to_date('11/Mar/15', 'DD/MON/RR'), 12, to_date('', 'DD/MON/RR'), 6, NULL);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (48, to_date('11/Mar/15', 'DD/MON/RR'), 1, to_date('', 'DD/MON/RR'), 2, 3);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (49, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 3, 2);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (50, to_date('11/Mar/15', 'DD/MON/RR'), 8, to_date('', 'DD/MON/RR'), null, 9);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (51, to_date('11/Mar/15', 'DD/MON/RR'), 16, to_date('', 'DD/MON/RR'), null, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (52, to_date('11/Mar/15', 'DD/MON/RR'), 16, to_date('', 'DD/MON/RR'), null, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (53, to_date('11/Mar/15', 'DD/MON/RR'), 16, to_date('', 'DD/MON/RR'), null, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (54, to_date('11/Mar/15', 'DD/MON/RR'), 16, to_date('', 'DD/MON/RR'), 9, 10);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (55, to_date('11/Mar/15', 'DD/MON/RR'), 16, to_date('', 'DD/MON/RR'), null, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (56, to_date('11/Mar/15', 'DD/MON/RR'), 9, to_date('', 'DD/MON/RR'), 2, 11);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (57, to_date('11/Mar/15', 'DD/MON/RR'), 9, to_date('', 'DD/MON/RR'), null, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (58, to_date('11/Mar/15', 'DD/MON/RR'), 14, to_date('', 'DD/MON/RR'), 5, 13);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (59, to_date('11/Mar/15', 'DD/MON/RR'), 13, to_date('', 'DD/MON/RR'), 8, 14);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (63, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 6, 15);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (64, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 6, 2);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (65, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 6, 1);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (66, to_date('11/Mar/15', 'DD/MON/RR'), 9, to_date('', 'DD/MON/RR'), 5, 17);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (69, to_date('11/Mar/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), 2, 2);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (71, to_date('08/Sep/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), null, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (73, to_date('08/Sep/15', 'DD/MON/RR'), 12, to_date('', 'DD/MON/RR'), null, null);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (75, to_date('08/Sep/15', 'DD/MON/RR'), 1, to_date('', 'DD/MON/RR'), null, 3);

INSERT INTO Order_T (OrderID, OrderDate, CustomerID, FulfillmentDate, SalespersonID, ShipAdrsID) 
VALUES (76, to_date('15/Sep/15', 'DD/MON/RR'), 4, to_date('', 'DD/MON/RR'), null, null);




REM INSERTING into OrderLine_T
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (1,1,2,18);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (31,1,6,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (2,1,10,9);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (3,2,3,12);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (4,2,8,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (5,2,14,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (17,3,2,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (7,4,3,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (15,4,4,0);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (8,4,5,3);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (16,4,6,3);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (29,5,1,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (9,5,6,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (37,24,1,0);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (39,25,2,5);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (40,26,1,5);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (41,28,1,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (42,32,5,10);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (43,32,14,10);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (46,39,2,3);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (51,48,17,5);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (54,49,1,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (53,50,20,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (55,51,3,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (56,51,4,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (57,52,1,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (58,52,4,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (59,54,2,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (60,54,3,3);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (61,55,1,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (62,55,4,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (63,56,4,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (64,58,3,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (65,59,13,2);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (66,63,3,5);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (67,65,4,0);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (68,66,4,1);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (69,69,7,4);
Insert into OrderLine_T (OrderLineID,OrderID,ProductID,OrderedQuantity) values (70,71,3,0);


commit; 

